
import uk.co.caprica.vlcj.player.base.MediaPlayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ControlPanel extends JPanel {

    private JButton voice;      // 声音
    private Choice speed;      // 倍速

    private JButton start;      // 暂停按钮
    private JButton front;      // 快速前进30s
    private JButton back;       // 快速后退30s

    private JButton minScreen;  // 最小窗模式
    private JButton full;       // 全屏

    private MediaPlayer mediaPlayer;
    private boolean isPlay;
    private MainPane media;
    private VoiceSlider voiceSlider;
    public ControlPanel(MainPane media, MediaPlayer mediaPlayer, VoiceSlider voiceSlider) {
        this.mediaPlayer = mediaPlayer;
        this.isPlay = false;
        this.media = media;
        this.voiceSlider = voiceSlider;
        initComponents();
        addListener();
    }

    private void addListener() {

        start.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.controls().pause();
                isPlay = !isPlay;
                if (isPlay) {
                    start.setText("暂停");
                } else {
                    start.setText("开始");
                }
            }
        });
        speed.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // 修改速度
                String rateStr = String.valueOf(e.getItem());
                float rate = Float.parseFloat(rateStr.substring(0,rateStr.length() - 1));
                mediaPlayer.controls().setRate(rate);
            }
        });
        full.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                media.setExtendedState(JFrame.MAXIMIZED_BOTH);
                media.getGraphicsConfiguration().getDevice()
                        .setFullScreenWindow(media);
                media.setLocationRelativeTo(null);
                media.setUndecorated(true);
                media.setAlwaysOnTop(true);
                media.setResizable(false);
            }
        });
        minScreen.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                media.setBounds(new Rectangle(800, 500));
                media.setLocationRelativeTo(null);
                media.setUndecorated(false);
                media.setResizable(true);
            }
        });
        front.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.controls().skipTime(10000);
            }
        });
        back.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.controls().skipTime(-10000);
            }
        });
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // 创建一个下拉列表框
        speed = new Choice();
        String[] speedRate = { "0.5x", "0.75x", "1.0x", "1.25x", "1.5x", "2.0x" };
        for (String item : speedRate) {
            speed.addItem(item);
        }
        speed.select(2);
        front = new JButton(">>");
        start = new JButton("开始");
        back = new JButton("<<");


        full = new JButton("全屏");
        minScreen = new JButton("最小窗");

        // left
        JPanel leftPanel = new JPanel();
        Box leftBox = Box.createHorizontalBox();
        leftBox.add(new JLabel("音量"));
        voiceSlider.setBounds(new Rectangle(1,1));
        leftBox.add(voiceSlider);
        leftPanel.add(leftBox);

        // center
        JPanel centerPanel = new JPanel();
        Box speedController = Box.createHorizontalBox();
        speedController.add(back);
        speedController.add(Box.createHorizontalStrut(10));
        speedController.add(start);
        speedController.add(Box.createHorizontalStrut(10));
        speedController.add(front);
        speedController.add(Box.createHorizontalGlue());
        centerPanel.add(speedController);

        // right
        JPanel rightPanel = new JPanel();
        Box rightBox = Box.createHorizontalBox();
        rightBox.add(speed);
        rightBox.add(minScreen);
        rightBox.add(full);
        rightPanel.add(rightBox);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
    }
}
