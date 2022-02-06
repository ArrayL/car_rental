package show;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import method.*;
import window.*;


public class huanche  extends JFrame implements ActionListener{

    JTextField field1,field2,field3,field4,field5;
    JPanel jPanel4,jPanel5;
    Box box1,box2,box3,box4,box5,box6,box7,baseBox;
    JButton buttonOfQueDing,buttonOfReset,buttonOfQuXIAO;
    JTable table;
    connect conn=new connect();
    Object a[][];
    Object name[] = {"车辆编号","车辆名称","车辆颜色","车辆类型","车辆计价"};
    private JButton buttonOfXinxiliulan;
    private JButton buttonOfzuche;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField field;
    private JButton button_back;
    public huanche()
    {

        init();
        setVisible(true);
//			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(700, 200, 800, 500);
        setTitle("还车信息管理");

        xinXiLiuLan();
    }
    public void xinXiLiuLan()//信息浏览的方法，因为删除数据后会刷新一下，自动调用此函数。
    {
        int i=0;
        while(i<40)
        {
            a[i][0]=" ";
            a[i][1]=" ";
            a[i][2]=" ";
            a[i][3]=" ";
            a[i][4]=" ";


            i++;
        }
        i=0;


        conn.connDB();
        try {
            conn.stmt = conn.con.createStatement();
            String sql= "select * from carlist  where carhire='lended'";
            conn.rs = conn.stmt.executeQuery(sql);
            while(conn.rs.next())
            {
                String number = conn.rs.getString("carnumber");
                String carname = conn.rs.getString("carname");
                String carcolor = conn.rs.getString("carcolor");
                String carbrand = conn.rs.getString("carbrand");
                String price = conn.rs.getString("carprice");


                a[i][0]=number;
                a[i][1]=carname;
                a[i][2]=carcolor;
                a[i][3]=carbrand;
                a[i][4]=price;


                i++;

            }
            conn.closeDB();
            repaint();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        conn.closeDB();
    }

    void init()
    {

        label1 = new JLabel("已租车辆浏览");

        buttonOfXinxiliulan = new JButton("   刷新      ");
        buttonOfXinxiliulan.addActionListener(this);
        buttonOfzuche = new JButton("   还车      ");
        buttonOfzuche.addActionListener(this);
        button_back = new JButton("   返回      ");
        button_back.addActionListener(this);

        label2 = new JLabel("请输入还车编号：");
        label3 = new JLabel("请输入租赁时长（单位：日）");
        field = new JTextField();
        field2 = new JTextField();
        field3 = new JTextField();


        a = new Object[50][6];
        table = new JTable(a, name);//组件的创建
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        baseBox =Box.createHorizontalBox();
        box1 = Box.createVerticalBox();
        box1.add(Box.createVerticalStrut(20));
        box1.add(label2);
        box1.add(Box.createVerticalStrut(15));
        box1.add(field);
        box1.add(Box.createVerticalStrut(15));
        box1.add(label3);
        box1.add(Box.createVerticalStrut(15));
        box1.add(field3);
        box1.add(Box.createVerticalStrut(5));
        box1.add(buttonOfzuche);
        box1.add(Box.createVerticalStrut(5));

        box1.add(buttonOfXinxiliulan);
        box1.add(Box.createVerticalStrut(5));
        box1.add(button_back);







        box2 = Box.createHorizontalBox();
        box2.add(Box.createHorizontalStrut(10));
        box2.add(box1);   //左边的按钮部分用 box布局

        jPanel4 = new JPanel();
        jPanel5 = new JPanel();
        jPanel4.setLayout(new BorderLayout());
        jPanel4.add(box2,BorderLayout.NORTH);//把左边的按钮部分放到jpanel4中。


        jPanel5.setLayout(new BorderLayout());
        jPanel5.add(label1,BorderLayout.NORTH);
        jPanel5.add(scrollPane,BorderLayout.CENTER);//把表格 放jpanel5里

        this.setLayout(new BorderLayout());
        add(jPanel5,BorderLayout.EAST);
        add(jPanel4,BorderLayout.WEST);//把两个大的panel放到窗口里面



    }




    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }//判断 编号输入的是不是整数！

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source==buttonOfXinxiliulan) {
            xinXiLiuLan();
        }



        else if(source == buttonOfzuche){
            conn.connDB();
            try {
                if (field.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请填写完整！");
                } else if (!isNumeric(field.getText())) {
                    JOptionPane.showMessageDialog(null, "序号 请输入整数！");
                } else if (!isNumeric(field3.getText())) {
                    JOptionPane.showMessageDialog(null, "请填写完整！");
                } else if (!isNumeric(field3.getText())) {
                    JOptionPane.showMessageDialog(null, "日期 请输入整数！");
                } else {
                    int numberint = Integer.parseInt(field.getText());
                    conn.stmt = conn.con.createStatement();
                    String str_number_check = "select carnumber from carlist  ";
                    conn.rs = conn.stmt.executeQuery(str_number_check);
                    boolean flag = false;
                    while (conn.rs.next()) {
                        if (conn.rs.getString(1).equals(field.getText())) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag == false) {
                        JOptionPane.showMessageDialog(null, "未检测到该车辆");
                    } else {

                        String str_check = "select * from carlist  where carnumber=" + field.getText();
                        conn.rs = conn.stmt.executeQuery(str_check);
                        conn.rs.next();
                        String statement = conn.rs.getString(6);
                        if (statement.equals("no lend")) {
                            JOptionPane.showMessageDialog(null, "该车辆未出租");
                        } else if (statement.equals("repairing")) {
                            JOptionPane.showMessageDialog(null, "该车辆正在修理");
                        } else {
                            String str = "update carlist set carhire='no lend'where carnumber=" + field.getText() + "";
                            conn.stmt.executeUpdate(str);
                            conn.stmt = conn.con.createStatement();
                            String add ="update carlist set Time_of_lease=Time_of_lease+"+field3.getText()+" where carnumber="+field.getText()+"";
                            System.out.println(add);
                            conn.stmt.executeUpdate(add);
                            JOptionPane.showMessageDialog(null, "还车成功！");
                            conn.closeDB();
                        }
                    }
                }
            }catch(SQLException e1){
//				e1.printStackTrace();

                JOptionPane.showMessageDialog(null, "此编号已经被租用，请换一个编号！");
            }


        }
        else if(source == buttonOfQuXIAO)
        {
            this.dispose();
            new WindowBoxLayout2();

        }
        else if(source == buttonOfReset)
        {
            //field1.setText("");
            field2.setText("");
            field3.setText("");
            field4.setText("");
            field5.setText("");



        }
        else if(source == button_back){
            this.dispose();
            new WindowBoxLayout2();
        }


    }


}
