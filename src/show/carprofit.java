//package show;
//
//import java.awt.BorderLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.swing.Box;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//
//import window.WindowBoxLayout2;
//import window.connect;
//
//public class carprofit  extends JFrame implements ActionListener{
//    JTextField field1,field2,field3,field4,field5;
//    JPanel jPanel4,jPanel5;
//    Box box1,box2,box3,box4,box5,box6,box7,baseBox;
//    JButton buttonOfQueDing,buttonOfReset,buttonOfQuXIAO;
//    JTable table;
//    connect conn=new connect();
//    Object a[][];
//    Object name[] = {"车辆编号","车辆名称","车辆颜色","车辆类型","车辆计价"};
//    private JButton buttonOfXinxiliulan;
//    private JButton buttonOfzuche;
//    private JLabel label1;
//    private JLabel label2;
//    private JTextField field;
//    private JLabel label3;
//    public carprofit()
//    {
//
//        init();
//        setVisible(true);
////		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(700, 200, 800, 500);
//        setTitle("可租车辆");
//        xinXiLiuLan();
//    }
//    public void xinXiLiuLan()//信息浏览的方法，因为删除数据后会刷新一下，自动调用此函数。
//    {
//        int i=0;
//        while(i<40)
//        {
//            a[i][0]=" ";
//            a[i][1]=" ";
//            a[i][2]=" ";
//            a[i][3]=" ";
//            a[i][4]=" ";
//
//
//            i++;
//        }
//        i=0;
//
//
//        conn.connDB();
//        try {
//            conn.stmt = conn.con.createStatement();
//            String sql= "select * from carlist  where carhire='no lend'";
//            conn.rs = conn.stmt.executeQuery(sql);
//            while(conn.rs.next())
//            {
//                String number = conn.rs.getString("carnumber");
//                String carname = conn.rs.getString("carname");
//                String carcolor = conn.rs.getString("carcolor");
//                String carbrand = conn.rs.getString("carbrand");
//                String price = conn.rs.getString("carprice");
//
//
//                a[i][0]=number;
//                a[i][1]=carname;
//                a[i][2]=carcolor;
//                a[i][3]=carbrand;
//                a[i][4]=price;
//
//
//                i++;
//
//            }
//            conn.closeDB();
//            repaint();
//        } catch (SQLException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//        conn.closeDB();
//    }
//
//    void init()
//    {
//
//        label1 = new JLabel("汽车租赁信息管理系统");
//
//        buttonOfXinxiliulan = new JButton("  汽车信息浏览  ");
//        buttonOfXinxiliulan.addActionListener(this);
//        buttonOfzuche = new JButton("   计算价格      ");
//        buttonOfzuche.addActionListener(this);
//
//
//        label2 = new JLabel("租用车辆编号：");
//        label3 = new JLabel("租用天数：");
//        field = new JTextField();
//        field2 = new JTextField();
//        field3 = new JTextField();
//
//
//        a = new Object[50][6];
//        table = new JTable(a, name);//组件的创建
//        table.setEnabled(false);
//        JScrollPane scrollPane = new JScrollPane(table);
//        baseBox =Box.createHorizontalBox();
//        box1 = Box.createVerticalBox();
//        box1.add(Box.createVerticalStrut(20));
//
//        box1.add(buttonOfXinxiliulan);
//        box1.add(Box.createVerticalStrut(15));
//        box1.add(label2);
//        box1.add(Box.createVerticalStrut(5));
//        box1.add(field);
//        box1.add(label3);
//        box1.add(Box.createVerticalStrut(5));
//        box1.add(field2);
//
//        box1.add(Box.createVerticalStrut(5));
//        box1.add(buttonOfzuche);
//
//
//
//
//
//
//
//        box2 = Box.createHorizontalBox();
//        box2.add(Box.createHorizontalStrut(10));
//        box2.add(box1);   //左边的按钮部分用 box布局
//
//        jPanel4 = new JPanel();
//        jPanel5 = new JPanel();
//        jPanel4.setLayout(new BorderLayout());
//        jPanel4.add(box2,BorderLayout.NORTH);//把左边的按钮部分放到jpanel4中。
//
//
//        jPanel5.setLayout(new BorderLayout());
//        jPanel5.add(label1,BorderLayout.NORTH);
//        jPanel5.add(scrollPane,BorderLayout.CENTER);//把表格 放jpanel5里
//
//        this.setLayout(new BorderLayout());
//        add(jPanel5,BorderLayout.EAST);
//        add(jPanel4,BorderLayout.WEST);//把两个大的panel放到窗口里面
//
//
//
//    }
//
//
//
//
//    public static boolean isNumeric(String str){
//        for (int i = str.length();--i>=0;){
//            if (!Character.isDigit(str.charAt(i))){
//                return false;
//            }
//        }
//        return true;
//    }//判断 编号输入的是不是整数！
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object source = e.getSource();
//        String price=null;
//        if(source==buttonOfXinxiliulan) {
//            xinXiLiuLan();
//        }
//
//
//
//        else if(source == buttonOfzuche)
//        {
//            if(field.getText().equals("")||field2.getText().equals(""))
//            {
//                JOptionPane.showMessageDialog(null, "请填写完整！");
//            }
//            else if(!isNumeric(field.getText())||!isNumeric(field2.getText()))
//            {
//                JOptionPane.showMessageDialog(null, "序号 请输入整数！");
//            }
//            else
//            {
//                conn.connDB();
//                try{
//
//                    int numberint = Integer.parseInt(field.getText());
//                    int numberint2 = Integer.parseInt(field2.getText());
//                    conn.stmt = conn.con.createStatement();
//
//                    String sql = "select * from carlist where carnumber="+field.getText()+"";
//
//                    conn.rs = conn.stmt.executeQuery(sql);
//
//                    //把字符串转换成数字
//                    while(conn.rs.next())
//                    {
//
//                        price = conn.rs.getString("carprice");
//
//                    }
//
//                    int price2=Integer.parseInt(price)*numberint2;
//                    JOptionPane.showConfirmDialog(null,"总价为"+price2, "总价格",JOptionPane.WARNING_MESSAGE);
//                    conn.closeDB();
//                    this.dispose();
//                    new WindowBoxLayout2();
//                }
//                catch (SQLException e1) {
//
//
//                    JOptionPane.showMessageDialog(null, "此编号已经被租用，请换一个编号！");
//                }
//
//
//            }
//
//        }
//        else if(source == buttonOfQuXIAO)
//        {
//            this.dispose();
//            new WindowBoxLayout2();
//
//        }
//        else if(source == buttonOfReset)
//        {
//            //field1.setText("");
//            field2.setText("");
//            field3.setText("");
//            field4.setText("");
//            field5.setText("");
//
//
//
//        }
//
//
//    }
//
//}

package show;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import window.WindowBoxLayout2;
import window.connect;

public class carprofit  extends JFrame implements ActionListener{
    JTextField field1,field2,field3,field4,field5;
    JPanel jPanel4,jPanel5;
    Box box1,box2,box3,box4,box5,box6,box7,baseBox;
    JButton buttonOfQueDing,buttonOfReset,buttonOfQuXIAO;
    JTable table;
    connect conn=new connect();
    Object a[][];
    Object name[] = {"车辆编号","车辆名称","车辆颜色","车辆类型","车辆日租金","出租时长(天)","维修次数","单次维修价格"};
    private JButton buttonOfXinxiliulan;
    private JButton buttonOfzuche;
    private JButton buttonOffanhui;
    private JLabel label1;
    private JLabel label2;
    private JTextField field;
    private JLabel label3;
    public carprofit()
    {

        init();
        setVisible(true);

        setBounds(700, 200, 900, 500);
        setTitle("可租车辆");
        xinXiLiuLan();
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
            a[i][6]=" ";
            a[i][7]=" ";

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
                String Time_of_lease=conn.rs.getString("Time_of_lease");
                String	Times_of_repair=conn.rs.getString("Times_of_repair");
                String Pay_for_repair=conn.rs.getString("Pay_for_repair");


                a[i][0]=number;
                a[i][1]=carname;
                a[i][2]=carcolor;
                a[i][3]=carbrand;
                a[i][4]=price;
                a[i][5]=Time_of_lease;
                a[i][6]=Times_of_repair;
                a[i][7]=Pay_for_repair;

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

        label1 = new JLabel("汽车租赁信息管理系统");

        buttonOfXinxiliulan = new JButton("  刷新  ");
        buttonOfXinxiliulan.addActionListener(this);

        buttonOfzuche = new JButton(" 查询   ");
        buttonOfzuche.addActionListener(this);

        buttonOffanhui=new JButton("  返回  ");
        buttonOffanhui.addActionListener(this);


        label2 = new JLabel("租用车辆编号：");
        label3 = new JLabel("租用天数：");
        field = new JTextField();
        field2 = new JTextField();
        field3 = new JTextField();


        a = new Object[100][8];
        table = new JTable(a, name);//组件的创建
        table.setEnabled(false);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(table);
        baseBox =Box.createHorizontalBox();
        box1 = Box.createVerticalBox();
        box1.add(Box.createVerticalStrut(20));

        box1.add(buttonOfXinxiliulan);
        //box1.add(Box.createVerticalStrut(15));
        //box1.add(label2);
        //box1.add(Box.createVerticalStrut(5));
        //box1.add(field);
        //box1.add(label3);
        //box1.add(Box.createVerticalStrut(5));
        //box1.add(field2);

        box1.add(Box.createVerticalStrut(5));
        box1.add(buttonOfzuche);

        box1.add(Box.createVerticalStrut(5));
        box1.add(buttonOffanhui);






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
        add(jPanel5,BorderLayout.CENTER);
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
        String price=null;
        if(source==buttonOfXinxiliulan) {
            xinXiLiuLan();
        }



        else if(source == buttonOfzuche)
        {
			/*if(field.getText().equals("")||field2.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "请填写完整！");
			}
			else if(!isNumeric(field.getText())||!isNumeric(field2.getText()))
			{
				JOptionPane.showMessageDialog(null, "序号 请输入整数！");
			}
			else
			{
				conn.connDB();
				try{

					int numberint = Integer.parseInt(field.getText());
					int numberint2 = Integer.parseInt(field2.getText());
					conn.stmt = conn.con.createStatement();

					String sql = "select * from carlist where carnumber="+field.getText()+"";

				conn.rs = conn.stmt.executeQuery(sql);

					  //把字符串转换成数字
					while(conn.rs.next())
					{

					  price = conn.rs.getString("carprice");

					 }

				 int price2=Integer.parseInt(price)*numberint2;
					 JOptionPane.showConfirmDialog(null,"总价为"+price2, "总价格",JOptionPane.WARNING_MESSAGE);
					conn.closeDB();
					this.dispose();
					new WindowBoxLayout2();
				}
				catch (SQLException e1) {


				JOptionPane.showMessageDialog(null, "此编号已经被租用，请换一个编号！");
				}


			}*/

            new carprofit2();

        }
        else if(source == buttonOffanhui)
        {
            this.dispose();
            new WindowBoxLayout2();

        }



    }

}