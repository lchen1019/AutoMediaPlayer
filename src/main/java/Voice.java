import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;

public class Voice extends DynamicMedia {


    public Voice(MainPane mainPane, JPanel panel, EmbeddedMediaPlayerComponent mediaPlayerComponent, String path) {
        this.curTimeLabel = new JLabel("00:00");
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
        class HistoryThread extends Thread{
            @Override
            public synchronized void run() {
                while(true) {
                    try {
                        mainPane.setTime(videoSlider.getCurTime());
                        curTimeLabel.setText(calcTime(videoSlider.getCurTime()));
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }}
        new HistoryThread().start();
        panel.removeAll();

        ImageIcon imageIcon = new ImageIcon(HistoryPane.class.getClassLoader().getResource(ConfigValue.music_icon));
        JLabel label = new JLabel(imageIcon);
        label.setTransferHandler(mainPane.getPullFiler());
        panel.add(label, BorderLayout.CENTER);

        // 添加控制设置
        Box controlBox = Box.createVerticalBox();
        Box upperBox = Box.createHorizontalBox();
        upperBox.add(videoSlider);
        upperBox.add(curTimeLabel);
        upperBox.add(Box.createHorizontalStrut(1));
        upperBox.add(new JLabel(calcTime(videoSlider.getLength())));
        upperBox.add(Box.createHorizontalStrut(1));
        controlBox.add(upperBox);
        controlBox.add(Box.createVerticalStrut(5));
        controlBox.add(controlPanel);
        panel.add(controlBox, BorderLayout.SOUTH);
        panel.updateUI();
        panel.setVisible(true);
        this.mainPane.repaint();
    }
}
