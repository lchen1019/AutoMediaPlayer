import com.formdev.flatlaf.FlatLightLaf;
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;

import javax.swing.*;

// 程序入口
public class Application {


    public static void main(String[] args) {
        FlatLightLaf.setup();
        new NativeDiscovery().discover();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Media("D:\\software\\Microsoft_office\\OneDrive\\桌面\\my.mp4");
            }
        });
    }
}
