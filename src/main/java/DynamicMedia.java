import uk.co.caprica.vlcj.player.base.MediaPlayer;

import javax.swing.*;

public abstract class DynamicMedia extends Media {

    // 可播放的视频，含有额外的控制部件
    public ControlPanel controlPanel = null;
    public VoiceSlider voiceSlider = null;
    public VideoSlider videoSlider = null;
    public MainPane mainPane = null;

}
