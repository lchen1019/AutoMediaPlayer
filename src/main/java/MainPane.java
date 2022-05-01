import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import utils.FileAnalyze;

import java.awt.event.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;


public class MainPane extends JFrame {

    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

    private JPanel panel;

    public MainPane(String location) {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Media Player");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);
        panel.add(mediaPlayerComponent, BorderLayout.CENTER);
        mediaPlayerComponent.mediaPlayer().media().start("D:\\software\\Microsoft_office\\OneDrive\\桌面\\my.jpg");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });
        MediaPlayer mediaPlayer = mediaPlayerComponent.mediaPlayer();
        mediaPlayer.controls().pause();


        setVisible(true);
    }

    private void openFileActionPerformed(ActionEvent e) {
        // 新建一个文件选择框
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(this);
        String path = chooser.getSelectedFile().getAbsolutePath();
        System.out.println(path);
        Media media = null;
        if (FileAnalyze.analyzeFileType(path) == FileAnalyze.TYPE_VIDEO) {
            media = new Video(MainPane.this, panel, mediaPlayerComponent, path);
        } else if (FileAnalyze.analyzeFileType(path) == FileAnalyze.TYPE_VOICE) {
            media = new Voice(MainPane.this, panel, mediaPlayerComponent, path);
        } else if (FileAnalyze.analyzeFileType(path) == FileAnalyze.TYPE_PICTURE){
            media = new Picture(panel, mediaPlayerComponent, path);
        }
        if (media == null) {
            JOptionPane.showMessageDialog(this,
                    "格式不支持");
            System.out.println("格式不支持");
            return;
        }
        mediaPlayerComponent.mediaPlayer().controls().nextFrame();
        media.play();
    }


    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        openFile = new JMenuItem();
        settings = new JMenuItem();

        //======== this ========
        setMinimumSize(new Dimension(800, 500));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u6587\u4ef6");

                //---- openFile ----
                openFile.setText("\u6253\u5f00");
                openFile.addActionListener(e -> openFileActionPerformed(e));
                menu1.add(openFile);

                //---- settings ----
                settings.setText("\u8bbe\u7f6e");
                menu1.add(settings);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem openFile;
    private JMenuItem settings;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}