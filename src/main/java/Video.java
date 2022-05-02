import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;

public class Video extends DynamicMedia {


    public Video(MainPane mainPane, JPanel panel, EmbeddedMediaPlayerComponent mediaPlayerComponent, String path) {
        this.panel = panel;
        panel.removeAll();
        panel.add(mediaPlayerComponent, BorderLayout.CENTER);
        this.mediaPlayer = mediaPlayerComponent.mediaPlayer();
        this.location = path;
        mediaPlayer.controls().setRepeat(true);
        this.mediaPlayer.media().start(location);
        this.voiceSlider = new VoiceSlider(mediaPlayer);
        this.videoSlider = new VideoSlider(mediaPlayer);
        this.mainPane = mainPane;
        this.controlPanel = new ControlPanel(mainPane, mediaPlayer , voiceSlider);
    }

    @Override
    public void play() {


        videoSlider.init();
        voiceSlider.init();
        videoSlider.refresh();

        // 添加控制设置
        Box controlBox = Box.createVerticalBox();
        Box upperBox = Box.createHorizontalBox();
        upperBox.add(videoSlider);
        upperBox.add(new JLabel(calcTime(videoSlider.getLength())));
        controlBox.add(upperBox);
        controlBox.add(Box.createVerticalStrut(10));
        controlBox.add(controlPanel);
        controlBox.add(Box.createVerticalStrut(5));

        panel.add(controlBox, BorderLayout.SOUTH);
        panel.updateUI();
        panel.setVisible(true);
        System.out.println(location);
        this.mainPane.repaint();
    }
}
