
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class History {
    Vector<String> history;
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;
    private int buff_size = 256;

    //一条history的字符串 url+"*"+时间  *表示隔绝 img时为0
    public History() {
        initFromTxt();
        File file = new File(ConfigValue.historyFilePath);
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addHistory(String url, long time) {
        String res = url + "*" + time + "\n";
        boolean is = false;
        int i = 0;

        for (String str :
                history) {
            if (str == null) {
                history.remove(i);
                continue;
            }
            if (str != null && str.indexOf(url) != -1) {
                history.remove(i);
                break;
            }
            i++;
        }
        history.add(res);
        System.out.println("history:store " + url + "*" + time);
    }

    public void flushToFile() {
        // 写入配置文件
        PrintWriter pw = new PrintWriter(fileOutputStream);
        for (String str : history) {
            if (str != null && !"\n".equals(str))
                pw.write(str);
        }
        pw.flush();
        pw.close();
        System.out.println("history:file");
    }

    public Vector<String> getHistory() {
        return history;
    }

    public void clean() {
        try {
            fileOutputStream = new FileOutputStream(ConfigValue.historyFilePath);
            history.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initFromTxt() {
        history = new Vector<>();
        try {
            File file = new File(ConfigValue.historyFilePath);
            fileInputStream = new FileInputStream(file);
            BufferedReader bw = new BufferedReader(new FileReader(ConfigValue.historyFilePath));
            String temp = null;
            StringBuilder strBuilder = new StringBuilder();
            while ((temp = bw.readLine()) != null) {
                strBuilder.append(temp);
            }
            String[] strings = (strBuilder+"").split("\n");
            for (String str : strings) {
                history.add(str + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
