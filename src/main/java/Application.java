import com.formdev.flatlaf.FlatLightLaf;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.RuntimeUtil;
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;

import javax.swing.*;

// 程序入口
public class Application {


    public static void main(String[] args) {
        FlatLightLaf.setup();
        new NativeDiscovery().discover();
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),ConfigValue.VLCPath);
        // 初始化默认设置
        Initialization initialization = new Initialization();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainPane();
            }
        });
    }
}
