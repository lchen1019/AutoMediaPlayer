import javax.swing.*;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Vector;

public class History {
    Vector<String> history;
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;
    private int buff_size=256;
    //一条history的字符串 url+"*"+时间  *表示隔绝 img时为0
    public History(){
        initFromTxt();
        File file = new File(ConfigValue.historyFilePath);
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void addHistory(String url,long time){
        String res=url+"*"+time+"\n";
        boolean is=false;
        int i=0;

        for (String str:
             history) {
            if(str==null) {
                history.remove(i);
                continue;
            }
            if(str!=null&&str.indexOf(url)!=-1)
            {
                history.remove(i);
                break;
            }
            i++;
        }
        history.add(res);
        System.out.println("history:store "+url+"*"+time);
    }
    public void flushToFile(){
        for (String str:
              history) {
            try {
                if (str!=null && !"\n".equals(str))
                fileOutputStream.write(str.getBytes());
                System.out.println(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }System.out.println("history:file");
    }

    public Vector<String> getHistory()
    {
        return history;
    }
    public void clean(){
        try {
            fileOutputStream=new FileOutputStream(ConfigValue.historyFilePath);
            history.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void initFromTxt(){
        history=new Vector<>();
        try {
            File file = new File(ConfigValue.historyFilePath);

            fileInputStream =new FileInputStream(file);

            int i=fileInputStream.read();
            String temp="";
            while(i!=-1)
            {
                char c=(char)i;
                temp+=c;
                i=fileInputStream.read();
            }
            System.out.println("ok1");
            String[] strings= temp.split("\n");
            for (String str:strings
                 ) {
                if(!"".equals(strings))
                {
                    history.add(str+"\n");

                }
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
