package method;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import show.*;
import window.*;
/*
 * 信息录入界面
 */

public class revert extends JFrame implements ActionListener {

    JTextField field1,field2,field3,field4,field5,field6;
    Box box1,box2,box3,box4,box5,box6,box7,baseBox;
    JButton buttonOfQueDing,buttonOfReset,buttonOfQuXIAO;
    connect conn=new connect();

    public revert()
    {

        init();
        setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(550, 200, 550, 280);
        setTitle("车辆归还录入界面");
    }

    void init()
    {
        JLabel label1 = new JLabel("车辆编号: ");
        JLabel label2 = new JLabel("维修价格: ");


        field1 = new JTextField();
        field2 = new JTextField();


        buttonOfQueDing = new JButton("提交");
        buttonOfReset = new JButton("重置");
        buttonOfQuXIAO = new JButton("取消");
        buttonOfQueDing.addActionListener(this);
        buttonOfQuXIAO.addActionListener(this);
        buttonOfReset.addActionListener(this);


        box1 = Box.createHorizontalBox();
        box1.add(box1.createHorizontalStrut(5));
        box1.add(label1);
        box1.add(box1.createHorizontalStrut(5));
        box1.add(field1);
        box1.add(box1.createHorizontalStrut(5));

        box2 = Box.createHorizontalBox();
        box2.add(box2.createHorizontalStrut(5));
        box2.add(label2);
        box2.add(box2.createHorizontalStrut(5));
        box2.add(field2);
        box2.add(box2.createHorizontalStrut(5));



        box7 = Box.createHorizontalBox();
        box7.add(box7.createHorizontalStrut(10));
        box7.add(buttonOfQueDing);
        box7.add(box7.createHorizontalStrut(10));
        box7.add(buttonOfQuXIAO);
        box7.add(box7.createHorizontalStrut(5));
        box7.add(buttonOfReset);
        box7.add(box7.createHorizontalStrut(5));

        baseBox = Box.createVerticalBox();
        baseBox.add(Box.createVerticalStrut(5));
        baseBox.add(box1);
        baseBox.add(Box.createVerticalStrut(5));
        baseBox.add(box2);
        baseBox.add(Box.createVerticalStrut(5));
        baseBox.add(box7);
        baseBox.add(Box.createVerticalStrut(5));

        add(baseBox);

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
        String number = field1.getText();
        String price = field2.getText() ;

//		if(field6.getText().equals("是")&&field6.getText().equals("已经")&&field6.getText().equals("已租用")&&field6.getText().equals("已"))
//		{
//			hire = "1";
//		}
//		else
//		{
//			hire = "0";
//		}


        if(source == buttonOfQueDing)
        {
            if(number.equals("")||price.equals(""))
            {
                JOptionPane.showMessageDialog(null, "请填写完整！");
            }
            else if(!isNumeric(number))
            {
                JOptionPane.showMessageDialog(null, "序号 请输入整数！");
            }
            else
            {
                conn.connDB();
                try{
                    int numberint2 = Integer.parseInt(number);
                    conn.stmt = conn.con.createStatement();
                    String str2="select carnumber from carlist where carhire='repairing'";

                    conn.rs = conn.stmt.executeQuery(str2);
                    int flag2=0;
                    while(conn.rs.next()) {
                        String carall = conn.rs.getString("carnumber");
                        if(field1.getText().equals(carall))
                        {flag2=1;}

                    }
                    System.out.println(flag2);





                    if(flag2==1)
                    {
                        String str3 = "update carlist set carhire='no lend'where carnumber="+field1.getText()+"";
                        conn.stmt.executeUpdate(str3);
                        String addtime ="update carlist set Times_of_repair=Times_of_repair+1 where carnumber="+field1.getText()+"";
                        conn.stmt.executeUpdate(addtime);
                        String addprice ="update carlist set Pay_for_repair=Pay_for_repair+"+field2.getText()+" where carnumber="+field1.getText()+"";
                        conn.stmt.executeUpdate(addprice);
                        JOptionPane.showMessageDialog(null, "返还成功！");
                        conn.closeDB();
                    }
                    else {

                        JOptionPane.showMessageDialog(null, "返还失败！车辆未在维修状态");
                    }
                    //this.dispose();
                    //new WindowBoxLayout2();
                } catch (SQLException e1) {
//				e1.printStackTrace();

                    JOptionPane.showMessageDialog(null, "此编号已经被使用，请换一个编号！");
                }


            }

        }
        else if(source == buttonOfQuXIAO)
        {
            this.dispose();

        }
        else if(source == buttonOfReset)
        {
            //field1.setText("");
            field2.setText("");
            field3.setText("");
            field4.setText("");
            field5.setText("");
            field6.setText("");


        }


    }


}