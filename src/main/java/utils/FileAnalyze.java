package utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileAnalyze {

    public static final int TYPE_VIDEO = 0;
    public static final int TYPE_VOICE = 1;
    public static final int TYPE_PICTURE = 2;
    public static final int UNKNOWN = 3;

    private static final String[] videoSuffix = {"mp4"};
    private static final String[] voiceSuffix = {"rar", "mp3"};
    private static final String[] pictureSuffix = {"png", "jpg", "jpeg"};

    // 分析文件类型
    public static int analyzeFileType(String location) {
        int len = location.length();
        int i = len - 1;
        for (; i >= 0; i--) {
            if (location.charAt(i) == '.') {
                break;
            }
        }
        String suffix = location.substring(i + 1);
        System.out.println(suffix);
        for (String item : videoSuffix) {
            if (item.equals(suffix)) {
                return TYPE_VIDEO;
            }
        }
        for (String item : voiceSuffix) {
            if (item.equals(suffix)) {
                return TYPE_VOICE;
            }
        }
        for (String item : pictureSuffix) {
            if (item.equals(suffix)) {
                return TYPE_PICTURE;
            }
        }
        return UNKNOWN;
    }
    /*
    * 测试发现路径有中文会有大问题 已修复
    * pic无法播放 可以正常播放
    * */
    public  static  String getFileFromChoose(JFrame parent){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setCurrentDirectory(new File("."));


        String pic="",video="",voice="";
        for (String item : pictureSuffix) {
            pic+=item+",";
            }
        for (String item : videoSuffix) {
            video+=item+",";
        }
        for(String item :voiceSuffix){
           voice+=item+",";
        }

        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("video("+video.substring(0,video.length()-1)+")" ,videoSuffix));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("image("+pic.substring(0,pic.length()-1)+")" ,pictureSuffix));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("voice("+voice.substring(0,voice.length()-1)+")" ,voiceSuffix));
        //如果用户没做选择返回null

        if( 1==chooser.showOpenDialog(parent))
        {   JOptionPane.showMessageDialog(parent,
                "未选择");
            return null; }
        String path = chooser.getSelectedFile().getAbsolutePath();
        return path;
    }
}
