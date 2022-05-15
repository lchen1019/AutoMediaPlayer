import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import utils.FileAnalyze;

import java.awt.event.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BaseMultiResolutionImage;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Objects;
import java.util.Vector;
import javax.swing.*;


public class MainPane extends JFrame {

    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

    private JPanel panel;
    private History history;
    private String nowPlayFile;
    private long time=0;
    public void setHistory(History history) {
        this.history = history;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public MainPane() {
        Image image= Toolkit.getDefaultToolkit()
                .getImage(StartPane.class.getClassLoader().getResource(ConfigValue.app_icon));
        this.setIconImage(image);
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Media Player");
        history=new History();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);
        ImageIcon image1=new ImageIcon(StartPane.class.getClassLoader().getResource(ConfigValue.start_icon));
        JLabel label=new JLabel(image1);
        panel.add(label, BorderLayout.CENTER);


      //  System.out.println(StartPane.class.getClassLoader().getResource(ConfigValue.bg_start).getPath().substring(1));
      //  mediaPlayerComponent.mediaPlayer().media().start(StartPane.class.getClassLoader().getResource(ConfigValue.bg_start).getPath());
        //mediaPlayerComponent.mediaPlayer().media().start("E:\\作业\\AutoMediaPlayer\\src\\resource\\bg_start.jpg");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                if(nowPlayFile!=null)
                history.addHistory(nowPlayFile,time);
                history.flushToFile();
                System.exit(0);
            }
        });
        MediaPlayer mediaPlayer = mediaPlayerComponent.mediaPlayer();
        mediaPlayer.controls().pause();
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();

        this.setLocation(0,0);

        setVisible(true);
    }

    private void openFileActionPerformed(ActionEvent e) {
        // 新建一个文件选择框
        String path=FileAnalyze.getFileFromChoose(this);
        System.out.println(path);
        play(path);
    }
   private void play(String path){
       Media media = null;
       if(path==null)
           return;
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

       Vector<String> vector=history.getHistory();
       long temp=0;
       for (String str:
            vector) {
           if(str.indexOf(path)!=-1)
           {
           //    System.out.println(str);
               str.substring(str.indexOf("*")+1,str.length()-1);
               temp=Long.parseLong(str.substring(str.indexOf("*")+1,str.length()-1));
               System.out.println("time"+temp);
               break;
           }
       }
       nowPlayFile=path;
       mediaPlayerComponent.mediaPlayer().controls().nextFrame();
    //   System.out.println("time1:"+time);
       media.play();
     //  System.out.println("setTime"+temp);
       time=temp;
       mediaPlayerComponent.mediaPlayer().controls().setTime(time);
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
                System.out.println(StartPane.class.getClassLoader().getResource(ConfigValue.file_icon));
                menu1.setIcon(new ImageIcon(StartPane.class.getClassLoader().getResource(ConfigValue.file_icon)));
                //---- openFile ----
                openFile.setText("\u6253\u5f00");
                openFile.addActionListener(e -> openFileActionPerformed(e));
                menu1.add(openFile);
                openFile.setIcon(new ImageIcon(StartPane.class.getClassLoader().getResource(ConfigValue.file_icon)));
                //---- settings ----
                settings.setText("\u8bbe\u7f6e");
                settings.setIcon(new ImageIcon(StartPane.class.getClassLoader().getResource(ConfigValue.setting_icon)));
                menu1.add(settings);
            }
            menuBar1.add(menu1);
        }

        menuBar1.setPreferredSize(new Dimension( getWidth(),30));
        add(menuBar1,BorderLayout.NORTH);
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
