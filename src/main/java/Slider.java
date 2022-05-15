import uk.co.caprica.vlcj.player.base.MediaPlayer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// 进度条抽象类
public abstract class Slider extends JSlider {

    protected int initialValue;
    protected MediaPlayer mediaPlayer;
    protected int exp;
    public Slider(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        initComponent();
        addListener();
    }


    private void addListener() {
        // 拖动播放时间条
        JSlider that = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                doResponse(that.getValue());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                doResponse(that.getValue());
            }
        });
    }

    public abstract void doResponse(int value);
    public abstract void init();


    private void initComponent() {
    }

}
