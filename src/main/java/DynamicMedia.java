import uk.co.caprica.vlcj.player.base.MediaPlayer;

import javax.swing.*;

public abstract class DynamicMedia extends Media {

    // 可播放的视频，含有额外的控制部件
    protected ControlPanel controlPanel = null;
    protected VoiceSlider voiceSlider = null;
    protected VideoSlider videoSlider = null;
    protected MainPane mainPane = null;
    protected JLabel curTimeLabel;

    protected String calcTime(long length) {
        String minutes = (length / 1000) / 60 + "";
        String second = (length / 1000) % 60 + "";
        minutes = minutes.length() < 2 ? "0" + minutes : minutes;
        second = second.length() < 2 ? "0" + second : second;
        return minutes + ":" + second;
    }

}
