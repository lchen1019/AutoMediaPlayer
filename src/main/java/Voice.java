import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;

public class Voice extends DynamicMedia {


    public Voice(MainPane mainPane, JPanel panel, EmbeddedMediaPlayerComponent mediaPlayerComponent, String path) {
        this.panel = panel;
        panel.removeAll();
        panel.add(mediaPlayerComponent, BorderLayout.CENTER);
        this.mediaPlayer = mediaPlayerComponent.mediaPlayer();
        this.location = path;
        this.mediaPlayer.media().start(location);
        mediaPlayer.controls().setRepeat(true);
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
        panel.removeAll();
        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon("D:\\software\\Microsoft_office\\OneDrive\\桌面\\my.jpg");
        label.setIcon(imageIcon);
        panel.add(label, BorderLayout.CENTER);

        // 添加控制设置
        Box controlBox = Box.createVerticalBox();
        controlBox.add(videoSlider);
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
