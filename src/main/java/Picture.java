import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;


public class Picture extends Media {


    public Picture(JPanel panel, EmbeddedMediaPlayerComponent mediaPlayerComponent, String path) {
        this.mediaPlayer = mediaPlayerComponent.mediaPlayer();
        this.location = path;
     //   System.out.println("pic:"+path);
        this.panel = panel;
        panel.removeAll();
        panel.add(mediaPlayerComponent, BorderLayout.CENTER);
        panel.updateUI();
    }

    @Override
    public void play() {
        this.mediaPlayer.media().start(location);
        this.mediaPlayer.controls().pause();
    }
}
