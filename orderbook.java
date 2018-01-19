import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
public class orderbook extends JPanel implements ActionListener
{
    String act=null;
    String bal="";//get sensex and nifty values
    String open1="",close1="",high1="",low1="";
    JTextField code;
    orderbook()
    {
        this.setLayout(null);
        JButton dash=new JButton("DASHBOARD");
        dash.setBounds(400,20,150,30);
        dash.addActionListener(this);
        add(dash);
        JLabel current=new JLabel("AVAILABLE BALANCE = "+bal);
        current.setBounds(50,20,200,30);
        add(current);
        JButton buy=new JButton("BUY");
        buy.setBounds(400,120,70,30);
        add(buy);
        JButton sell=new JButton("SELL");
        sell.setBounds(400,170,70,30);
        add(sell);
        JLabel comp=new JLabel("COMPANY CODE");
        comp.setBounds(70,120,150,30);
        add(comp);
        JButton check=new JButton("CHECK");
        check.setBounds(250,150,80,30);
        add(check);
        code=new JTextField();
        code.setBounds(70,150,150,30);
        add(code);
        JLabel num=new JLabel("NUM OF SHARES");
        num.setBounds(70,210,150,30);
        add(num);
        JTextField nos=new JTextField();
        nos.setBounds(70,240,150,30);
        add(nos);
        JLabel stop=new JLabel("STOP LOSS");
        stop.setBounds(230,210,150,30);
        add(stop);
        JTextField sl=new JTextField();
        sl.setBounds(230,240,150,30);
        add(sl);
        JLabel high=new JLabel("HIGH  = "+high1);
        high.setBounds(100,300,150,30);
        add(high);
        JLabel low=new JLabel("LOW   = "+low1);
        low.setBounds(100,330,150,30);
        add(low);
        JLabel open=new JLabel("OPEN  = "+open1);
        open.setBounds(100,360,150,30);
        add(open);
        JLabel close=new JLabel("CLOSE = "+close1);
        close.setBounds(100,390,150,30);
        add(close);
    }
    public void actionPerformed(ActionEvent ae)//throws IOException
    {
        act=ae.getActionCommand();
        if(act=="DASHBOARD"){
            call.option=1;
            call.main();
        }
        if(act=="CHECK")repaint();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        String code1=code.getText();
        byte fg=0;
        for(int a=0;a<data.companies;a++) 
            if(code1.equalsIgnoreCase(data.code[a]))
            {
                g.drawString("VALID",250,180);
                String str=""+data.data[a][0][1];
                g.drawString(str,200,300);
                str=""+data.data[a][0][0];
                g.drawString(str,200,330);
                str=""+data.data[a][0][2];
                g.drawString(str,200,360);
                str=""+data.data[a][0][3];
                g.drawString(str,200,390);
                fg++;
                break;
            }
        if(fg==0)g.drawString("INVALID",250,180);
    }
}
