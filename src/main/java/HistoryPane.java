
import utils.FileAnalyze;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class HistoryPane extends JFrame {
    History history;
    String filePath;
    private MainPane mainFrame;
    HistoryPane(MainPane mainFrame,History history_o){
        HistoryPane self=this;
        this.mainFrame = mainFrame;

        //history处理
        history=history_o;
        Vector<String> vector_his=history.getHistory();
        Vector<String> file_his=new Vector<>();
        for (String str:
             vector_his) {
            String res="";
            if(str.indexOf("*")!=-1)
            {
                res=str.substring(0,str.indexOf("*"));
            }
            else{
                res=str;
            }
            file_his.add(res);
        }
        JScrollPane jScrollPane=new JScrollPane();
        jScrollPane.setPreferredSize(new Dimension(200,200));

        JLabel jL1=new JLabel("历史记录");
        JLabel jL2=new JLabel();
        JList jList=new JList(file_his);
        JPanel jPanel=new JPanel();
        JButton btn1=new JButton("清除历史记录");
        //JButton btn2=new JButton("选择文件");
        JButton btn3=new JButton("开始播放");
        btn3.setIcon(new ImageIcon(HistoryPane.class.getClassLoader().getResource(ConfigValue.start1_icon)));
        jList.setLocation(20,50);
        jList.setSize(200,200);
        jScrollPane.setSize(200,200);
        jScrollPane.setLocation(20,50);
        jPanel.setLocation(20,50);
        jPanel.setSize(200,250);
        jL1.setLocation(20,20);
        jL1.setSize(100,30);
        jL2.setLocation(250,80);
        jL2.setSize(210,150);
        btn1.setLocation(20,280);
        btn1.setSize(200,30);
   //     btn2.setSize(200,30);
   //     btn2.setLocation(250,50);
        btn3.setLocation(250,250);
        btn3.setSize(200,40);


        jList.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!jList.getValueIsAdjusting()&&jList.getSelectedValue()!=null){	//设置只有释放鼠标时才触发
                    System.out.println(jList.getSelectedValue());


                    filePath=(jList.getSelectedValue()).toString();
                    jL2.setText("<html><body>您当前选择:<br/><div color=#00AEEC>"+filePath.substring(0,filePath.lastIndexOf("\\")+1)+
                            "<br/>"  +filePath.substring(filePath.lastIndexOf("\\")+1)+"</div></body></html>");
                    System.out.println(filePath);
                }
            }
        });
        btn1.setBackground(new Color(80,160,240));
        btn1.setForeground(Color.white);
        btn1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                history.clean();
                file_his.clear();
                jList.setListData(file_his);
            }
        });
        btn3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainFrame.play(filePath);
                self.dispose();
            }
        });
//        btn2.setBackground(new Color(80,160,240));
//        btn2.setForeground(Color.white);
//        btn2.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//               String string=FileAnalyze.getFileFromChoose(self);
//               if(string!=null)
//               {
//                   jL2.setText("<html><body>您当前选择:<br/><div color=#00AEEC>"+string.substring(0,string.lastIndexOf("\\")+1)+
//                         "<br/>"  +string.substring(string.lastIndexOf("\\")+1)+"</div></body></html>");
//               }
//            }
//        });
        jScrollPane.setViewportView(jList);
        jPanel.add(jScrollPane);

        btn3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        add(btn3);
      //  add(btn2);
        add(jL1);
        add(jL2);
        add(btn1);
        getContentPane().add(jPanel);
        this.setLayout(null);
        this.setTitle("开始界面");

        Image image= Toolkit.getDefaultToolkit()
                .getImage(HistoryPane.class.getClassLoader().getResource(ConfigValue.app_icon));
        this.setIconImage(image);
        this.setResizable(false);
        this.setSize(470,400);
        setLocation(mainFrame.getX()+mainFrame.getWidth()/2-getWidth()/2,mainFrame.getY()+mainFrame.getHeight()/2-getHeight()/2);
        this.setVisible(true);

    }

    @Override
    public void paint(Graphics g) {
        ((Graphics2D)g).setPaint(Color.white);
        g.drawRoundRect(0,0,getWidth(),getHeight(),50,50);
        super.paint(g);
    }
}
