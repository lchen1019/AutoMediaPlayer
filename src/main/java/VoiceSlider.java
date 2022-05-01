import uk.co.caprica.vlcj.player.base.MediaPlayer;

import javax.swing.*;
import java.awt.*;

// 声音的进度条
public class VoiceSlider extends Slider{


    public VoiceSlider(MediaPlayer mediaPlayer) {
        super(mediaPlayer);
        exp = 100;
        setMaximum(exp);
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public void doResponse(int value) {
        mediaPlayer.audio().setVolume(value);
    }

    @Override
    public void init() {
        this.setValue(50);
    }

}
