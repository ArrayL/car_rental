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


public class checkcar extends JFrame implements ActionListener{
    JTable table;
    JLabel label1,label2,label3;
    Object a[][];
    Object name[] = {"车辆编号","车辆名称","车辆颜色","车辆类型","车辆计价","借出状态"};
    JTextField field,field1,field2,field3,field4,field5,field6;
    JButton buttonOfXinxiluru,buttonOfXinxiliulan,buttonOfDelete,button_back;
    Box box1,box2,baseBox;
    JButton buttonOfQueDing,buttonOfReset,buttonOfQuXIAO;

    String number;
    connect conn=new connect();
    JPanel jPanel4,jPanel5;
    public checkcar()
    {

        init();
        setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(700, 200, 800, 500);
        setTitle("车辆信息管理");
        backpictuer();
        xinXiLiuLan();
    }
    void backpictuer() {
        ImageIcon bg=new ImageIcon("background3.jpg");//设置背景图片
        JLabel label =new JLabel(bg);
        label.setBounds(0,0,0,0);
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        JPanel jp=(JPanel)this.getContentPane();
        jp.setOpaque(false);
        JPanel panel =new JPanel();
        panel.setOpaque(false);
        panel.setLayout(null);
        this.add(panel);

    }
    public void xinXiLiuLan()//信息浏览的方法，因为删除数据后会刷新一下，自动调用此函数。
    {
        int i=0;
        while(i<50)
        {
            a[i][0]=" ";
            a[i][1]=" ";
            a[i][2]=" ";
            a[i][3]=" ";
            a[i][4]=" ";
            a[i][5]=" ";

            i++;
        }
        i=0;


        conn.connDB();
        try {
            conn.stmt = conn.con.createStatement();
            String sql= "select * from carlist";
            conn.rs = conn.stmt.executeQuery(sql);
            while(conn.rs.next())
            {
                String number = conn.rs.getString("carnumber");
                String carname = conn.rs.getString("carname");
                String carcolor = conn.rs.getString("carcolor");
                String carbrand = conn.rs.getString("carbrand");
                String price = conn.rs.getString("carprice");
                String  hire= conn.rs.getString("carhire");

                a[i][0]=number;
                a[i][1]=carname;
                a[i][2]=carcolor;
                a[i][3]=carbrand;
                a[i][4]=price;
                a[i][5]=hire;

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



    void init() {
        label1 = new JLabel("汽车租赁信息管理系统");
        buttonOfXinxiluru = new JButton("   录入      ");
        buttonOfXinxiluru.addActionListener(this);
        buttonOfXinxiliulan = new JButton("   刷新      ");
        buttonOfXinxiliulan.addActionListener(this);
        buttonOfDelete = new JButton("   删除      ");
        buttonOfDelete.addActionListener(this);
        button_back = new JButton("   返回      ");
        button_back.addActionListener(this);


        label2 = new JLabel("待删除信息编号：");

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
        box1.add(buttonOfXinxiluru);
        box1.add(Box.createVerticalStrut(10));
        box1.add(buttonOfXinxiliulan);
        box1.add(Box.createVerticalStrut(15));
        box1.add(label2);
        box1.add(Box.createVerticalStrut(5));
        box1.add(field);
        box1.add(Box.createVerticalStrut(5));
        box1.add(buttonOfDelete);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object source = e.getSource();
        if(source == buttonOfXinxiluru)//点击信息修改按钮
        {

            new Luru();
        }
        else if(source == buttonOfXinxiliulan)//点击信息浏览按钮
        {
            xinXiLiuLan();

        }



        else if(source == buttonOfDelete)//点击删除按钮
        {
            if(field.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "请输入删除车辆的编号！");
            }
            else
            {
                conn.connDB();
                String sql;
                try {
                    conn.stmt = conn.con.createStatement();
                    sql = "select * from carlist  where carnumber='"+field.getText()+"'";//表里找到需要删除的车信息
                    conn.rs = conn.stmt.executeQuery(sql);
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
                try {
                    if(conn.rs.next())//判断是否有 输入编号的 车辆
                    {

                        int n = JOptionPane.showConfirmDialog(this, "确定删除此车辆信息？","确认对话框",JOptionPane.YES_NO_OPTION);//确认文本框
                        if(n == JOptionPane.YES_OPTION)
                        {
                            String hire2 = conn.rs.getString("carhire");
                            if(hire2.equals("repairing")||hire2.equals("lended"))
                            {
                                int m = JOptionPane.showConfirmDialog(this, "此车辆正在被租用或维修，是否删除？","确认对话框",JOptionPane.YES_NO_OPTION);//确认文本框
                                if(m == JOptionPane.YES_OPTION)
                                {
                                    try
                                    {
                                        conn.stmt = conn.con.createStatement();
                                        String sql2 = "delete from carlist where carnumber='"+field.getText()+"';";
                                        conn.stmt.executeUpdate(sql2);
                                    }
                                    catch (SQLException e1)
                                    {
                                        e1.printStackTrace();
                                    }
                                    conn.closeDB();
                                    repaint();
                                    field.setText("");
                                    JOptionPane.showMessageDialog(null,"删除成功！");

                                }



                            }
                            else{
                                try
                                {
                                    conn.stmt = conn.con.createStatement();
                                    String sql2 = "delete from carlist where carnumber='"+field.getText()+"';";
                                    conn.stmt.executeUpdate(sql2);
                                }
                                catch (SQLException e1)
                                {
                                    e1.printStackTrace();
                                }
                                conn.closeDB();
                                repaint();
                                field.setText("");
                                JOptionPane.showMessageDialog(null,"删除成功！");
                            }
                        }

                    }


                    else
                    {
                        JOptionPane.showMessageDialog(null, "没有此编号的车辆信息！");
                    }
                } catch (HeadlessException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }




            }

        }
        else if(source == button_back){
            this.dispose();
            new WindowBoxLayout2();
        }

    }

}
