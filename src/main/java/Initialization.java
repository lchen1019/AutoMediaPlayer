import com.google.gson.Gson;

import java.io.*;

/**
 * 初始化播放设置
 * Chen Lin
 * 2022/5/15 9:34
 */

public class Initialization {

    private static DefaultSettings ds;
    private static Gson gson;

    public Initialization() {
        gson = new Gson();
        // 构造函数中解析文件成对象
        StringBuilder json = new StringBuilder();
        try {
            BufferedReader bw = new BufferedReader(new FileReader(new File("D:\\software\\Microsoft_office\\OneDrive\\桌面\\AutoMediaPlayer\\src\\main\\settings.json")));
            String temp = null;
            System.out.println("asdsd" + temp);
            while ((temp = bw.readLine()) != null) {
                json.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        ds = (DefaultSettings) gson.fromJson(json.toString(), DefaultSettings.class);
        System.out.println(ds.toString());
     }

    // 获取默认的配置信息
    public static DefaultSettings getSettings() {
        if (ds == null) {
            System.out.println("未初始化");
            throw new NullPointerException();
        }
        return ds;
    }

    // 存储修改之后的配置
    public static void updateSettings(DefaultSettings ds) {
        String str = gson.toJson(ds);
        System.out.println(str);
        // 写入配置文件
        try {
            FileWriter fw = new FileWriter("D:\\software\\Microsoft_office\\OneDrive\\桌面\\AutoMediaPlayer\\src\\main\\settings.json");
            fw.write(str);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 更新全局的ds
        Initialization.ds.setFullScreen(ds.isFullScreen());
        Initialization.ds.setSinceLastPlay(ds.isSinceLastPlay());
        Initialization.ds.setVolume(ds.getVolume());
    }

    public static void main(String[] args) {
        Initialization initialization = new Initialization();
    }

}
