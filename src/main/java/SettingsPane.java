import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


public class SettingsPane extends JFrame {

    private JFrame mainFrame;
    private DefaultSettings ds;
    public SettingsPane(JFrame mainFrame) {
        DefaultSettings origin = Initialization.getSettings();
        try {
            ds = (DefaultSettings) origin.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Image image= Toolkit.getDefaultToolkit().getImage(HistoryPane.class.getClassLoader().getResource(ConfigValue.app_icon));
        this.setIconImage(image);
        this.mainFrame = mainFrame;
        setLocationRelativeTo(mainFrame);
        initComponents();
        initStatus();
        setTitle("Media Player");
        setResizable(false);
//        setBounds(new Rectangle(500,500));
        this.setSize(500,320);
        setLocation(mainFrame.getX()+mainFrame.getWidth()/2-getWidth()/2,mainFrame.getY()+mainFrame.getHeight()/2-getHeight()/2);
        setVisible(true);
    }


    // 修改默认显示
    private void initStatus() {
        DefaultSettings ds = Initialization.getSettings();
        volume.setText(ds.getVolume() + "");
        sinceYes.setSelected(ds.isSinceLastPlay());
        sinceNo.setSelected(!ds.isSinceLastPlay());
        fullYes.setSelected(ds.isFullScreen());
        fullNo.setSelected(!ds.isFullScreen());
    }

    // 监听修改事件
    private void addListener() {
        // 保存修改
        save.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int v = ds.getVolume();
                if (v < 0 || v > 100) {
                    JOptionPane.showMessageDialog(SettingsPane.this,"音量越界，请输入0-100的数字");
                    return;
                }
                Initialization.updateSettings(ds);
                JOptionPane.showMessageDialog(SettingsPane.this,"修改成功");
                SettingsPane.this.setVisible(false);
            }
        });
        // 获取自动播放状态的Radio选中状态
        sinceYes.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                ds.setSinceLastPlay(!ds.isSinceLastPlay());
            }
        });
        // 获取是否全屏的Radio
        fullYes.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                ds.setFullScreen(!ds.isFullScreen());
            }
        });
        // 获取音量大小
        volume.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                // 更新其中内容
                System.out.println();
                try {
                    int v = Integer.parseInt(volume.getText().trim());
                    ds.setVolume(v);

                } catch (NumberFormatException ex) {
                    // 弹出对话框，提示数字
                    JOptionPane.showMessageDialog(SettingsPane.this,"音量格式不对");
                }
            }
        });

    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        save = new JButton();
        label2 = new JLabel();
        sinceYes = new JRadioButton();
        sinceNo = new JRadioButton();
        label3 = new JLabel();
        fullYes = new JRadioButton();
        fullNo = new JRadioButton();
        label4 = new JLabel();
        volume = new JTextField();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(label1);
        label1.setBounds(440, 295, 50, 20);

        //---- save ----
        save.setText("\u4fdd\u5b58");
        contentPane.add(save);
        save.setBounds(new Rectangle(new Point(230, 245), save.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u81ea\u52a8\u4ece\u4e0a\u4e00\u6b21\u7ed3\u675f\u5904\u64ad\u653e");
        contentPane.add(label2);
        label2.setBounds(90, 110, 135, 35);

        //---- sinceYes ----
        sinceYes.setText("\u662f");
        contentPane.add(sinceYes);
        sinceYes.setBounds(new Rectangle(new Point(265, 120), sinceYes.getPreferredSize()));

        //---- sinceNo ----
        sinceNo.setText("\u5426");
        contentPane.add(sinceNo);
        sinceNo.setBounds(335, 120, 45, 25);

        //---- label3 ----
        label3.setText("\u9ed8\u8ba4\u5168\u5c4f");
        contentPane.add(label3);
        label3.setBounds(170, 165, 55, 30);

        //---- fullYes ----
        fullYes.setText("\u662f");
        contentPane.add(fullYes);
        fullYes.setBounds(265, 170, 50, 21);

        //---- fullNo ----
        fullNo.setText("\u5426");
        contentPane.add(fullNo);
        fullNo.setBounds(335, 170, 45, 21);

        //---- label4 ----
        label4.setText("\u9ed8\u8ba4\u97f3\u91cf");
        contentPane.add(label4);
        label4.setBounds(175, 70, 75, 25);

        //---- volume ----
        contentPane.add(volume);
        volume.setBounds(260, 65, 105, 30);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        sinceGroup = new ButtonGroup();
        fullGroup = new ButtonGroup();
        sinceGroup.add(sinceYes);
        sinceGroup.add(sinceNo);
        fullGroup.add(fullYes);
        fullGroup.add(fullNo);
        addListener();

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JButton save;
    private JLabel label2;
    private JRadioButton sinceYes;
    private JRadioButton sinceNo;
    private JLabel label3;
    private JRadioButton fullYes;
    private JRadioButton fullNo;
    private JLabel label4;
    private JTextField volume;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private ButtonGroup sinceGroup;
    private ButtonGroup fullGroup;
}
