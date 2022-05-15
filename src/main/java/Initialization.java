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
            String enconding = "UTF-8";

            String path=new String(HistoryPane.class.getClassLoader().getResource(ConfigValue.setting).getPath().getBytes(),enconding);

            File file=new File(path);

            System.out.println(file.exists());
            BufferedReader bw = new BufferedReader(new FileReader("E:\\作业\\AutoMediaPlayer\\src\\resource\\settings.json"));
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
            FileWriter fw = new FileWriter("E:\\作业\\AutoMediaPlayer\\src\\resource\\settings.json");
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



}
