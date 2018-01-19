import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class dashboard extends JPanel implements ActionListener
{
    String act=null;
    String sen="",nif="";//get sensex and nifty values
    dashboard()
    {
        this.setLayout(null);
        JButton order=new JButton("ORDER BOOK");
        order.setBounds(50,50,150,30);
        order.addActionListener(this);
        JButton hold=new JButton("HOLDINGS");
        hold.setBounds(50,100,150,30);
        hold.addActionListener(this);
        JButton pro=new JButton("PRO TRADING");
        pro.setBounds(50,150,150,30);
        pro.addActionListener(this);
        JLabel sensex=new JLabel("sensex = "+sen);
        sensex.setFont(new Font("Verdana",1,40));
        JLabel nifty=new JLabel("nifty  = "+nif);
        nifty.setFont(new Font("Verdana",1,40));
        sensex.setBounds(300,70,400,50);
        nifty.setBounds(300,120,400,50);
        add(order);
        add(hold);
        add(pro);
        add(sensex);
        add(nifty);
    }
    public void actionPerformed(ActionEvent ae)
    {
        act=ae.getActionCommand();
        if(act=="ORDER BOOK"){
            call.option=2;
            call.main();
        }
        else if(act=="HOLDINGS"){
            call.option=3;
            call.main();
        }                     
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }
}
