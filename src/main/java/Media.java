// 超类

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.swing.*;

public abstract class Media {

    public String location = null;
    public EmbeddedMediaPlayer mediaPlayer = null;
    public JPanel panel = null;

    public abstract void play();
}
