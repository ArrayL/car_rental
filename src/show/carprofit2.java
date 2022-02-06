package show;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import show.*;
import window.*;

public class carprofit2 extends JFrame implements ActionListener {

    connect conn =new connect();
    Object a[][];
    Object name[]= {"车辆编号","车辆名称","车辆颜色","总收入","总支出","总利润"};
    JTable table;
    JPanel jPanel;
    public carprofit2()
    {
        init();
        setVisible(true);

        setBounds(700,200,500,500);
        setTitle("利润分析总览表");
        xinxiliulan();
    }

    public void xinxiliulan()
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
        try
        {
            conn.stmt=conn.con.createStatement();
            String sql="select * from carlist";
            conn.rs=conn.stmt.executeQuery(sql);
            while(conn.rs.next())
            {
                String number=conn.rs.getString("carnumber");
                String carname=conn.rs.getString("carname");
                String carcolor=conn.rs.getString("carcolor");
                String total_of_profit=conn.rs.getString("total_of_profit");
                String Time_of_lease=conn.rs.getString("Time_of_lease");
                String carprice=conn.rs.getString("carprice");
                String Profit_of_lease=conn.rs.getString("Profit_of_lease");
                String Times_of_repair=conn.rs.getString("Times_of_repair");
                String Pay_for_repair=conn.rs.getString("Pay_for_repair");


                a[i][0]=number;
                a[i][1]=carname;
                a[i][2]=carcolor;
                a[i][3]=Integer.parseInt(carprice)*Integer.parseInt(Time_of_lease);
                a[i][4]=Integer.parseInt(Pay_for_repair);
                a[i][5]=(Integer.parseInt(carprice)*Integer.parseInt(Time_of_lease))-(Integer.parseInt(Pay_for_repair));
                i++;
            }
            a[i][0]="总计";
            a[i][3]=0;
            for(int j=i-1;j>=0;j--){
                a[i][3]=(int )a[j][3]+(int )a[i][3];
            }
            a[i][4]=0;
            for(int j=i-1;j>=0;j--){
                a[i][4]=(int )a[j][4]+(int )a[i][4];
            }
            a[i][5]=0;
            for(int j=i-1;j>=0;j--){
                a[i][5]=(int )a[j][5]+(int )a[i][5];
            }
            conn.closeDB();
            repaint();
        }
        catch(SQLException e1) {
            e1.printStackTrace();
            conn.closeDB();
        }
    }


    void init()
    {

        a=new Object[50][6];
        table=new JTable(a,name);
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane=new JScrollPane(table);

        jPanel=new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(scrollPane,BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        add(jPanel,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }

}