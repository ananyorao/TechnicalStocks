import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class holdings extends JPanel implements ActionListener
{
    String act=null;
    String bal="";//get available balance in account for trading
    double hold[][];
    int total=0;
    int present=0;
    holdings()
    {
        //super("holdings");
        hold=new double[total][5];
        this.setLayout(null);       
        /*addWindowListener(new WindowAdaptor(){
            public void windowClosing(WindowEvent e){System.exit(0);}
        });*/
        JButton dash=new JButton("DASHBOARD");
        dash.setBounds(400,20,150,30);
        dash.addActionListener(this);
        add(dash);
        JLabel current=new JLabel("AVAILABLE BALANCE = "+bal);
        current.setBounds(50,20,200,30);
        add(current);
        JLabel now=new JLabel("CURRENT HOLDINGS");
        now.setBounds(50,60,200,30);
        add(now);
        JLabel nam=new JLabel("COMPANY NAME");
        nam.setBounds(50,100,150,30);
        add(nam);
        JLabel cod=new JLabel("COMPANY CODE");
        cod.setBounds(200,100,150,30);
        add(cod);
        JLabel bp=new JLabel("BUY PRICE");
        bp.setBounds(350,100,150,30);
        add(bp);
        JLabel cp=new JLabel("CURRENT PRICE");
        cp.setBounds(500,100,150,30);
        add(cp);
        JLabel num=new JLabel("NUM_OF_SHARES");
        num.setBounds(650,100,150,30);
        add(num);
        int b=0;
        for(int a=0;a<total;a++)
            if(hold[a][3]==0)
            {
                present=(int)hold[a][0];
                b++;
                JLabel name=new JLabel(data.company[(int)hold[a][0]]);
                name.setBounds(50,100+b*30,150,30);
                add(name);
                JLabel code=new JLabel(data.code[(int)hold[a][0]]);
                code.setBounds(200,100+b*30,150,30);
                add(code);
                String disp=""+hold[a][1];
                JLabel bp1=new JLabel(disp);
                bp1.setBounds(350,100+b*30,150,30);
                add(bp1);
                disp=""+data.data[(int)hold[a][0]][0][3];
                JLabel cp1=new JLabel(disp);
                cp1.setBounds(500,100+b*30,150,30);
                add(cp1);
                disp=""+hold[a][2];
                JLabel nos=new JLabel(disp);
                nos.setBounds(650,100+b*30,150,30);
                add(nos);
                JButton sq_off=new JButton("SQ_OFF");
                sq_off.setBounds(800,100+b*30,100,30);
                sq_off.addActionListener(this);
                add(sq_off);
            }
        b++;
        now=new JLabel("IN PROCESS");
        now.setBounds(50,100+b*30,200,30);
        add(now);
        b++;
        nam=new JLabel("COMPANY NAME");
        nam.setBounds(50,100+b*30,150,30);
        add(nam);
        cod=new JLabel("COMPANY CODE");
        cod.setBounds(200,100+b*30,150,30);
        add(cod);
        bp=new JLabel("SET PRICE");
        bp.setBounds(350,100+b*30,150,30);
        add(bp);
        cp=new JLabel("STOP LOSS");
        cp.setBounds(500,100+b*30,150,30);
        add(cp);
        num=new JLabel("NUM_OF_SHARES");
        num.setBounds(650,100+b*30,150,30);
        add(num);
        JLabel op=new JLabel("OPERATION");
        op.setBounds(800,100+b*30,150,30);
        add(op);
        for(int a=0;a<total;a++)
            if(hold[a][3]!=0)
            {                
                present=(int)hold[a][0];
                b++;
                JLabel name=new JLabel(data.company[(int)hold[a][0]]);
                name.setBounds(50,100+b*30,150,30);
                add(name);
                JLabel code=new JLabel(data.code[(int)hold[a][0]]);
                code.setBounds(200,100+b*30,150,30);
                add(code);
                String disp=""+data.data[(int)hold[a][0]][0][3];
                JLabel bp1=new JLabel(disp);
                bp1.setBounds(350,100+b*30,150,30);
                add(bp1);
                disp=""+hold[a][4];
                JLabel cp1=new JLabel(disp);
                cp1.setBounds(500,100+b*30,150,30);
                add(cp1);
                disp=""+hold[a][2];
                JLabel nos=new JLabel(disp);
                nos.setBounds(650,100+b*30,150,30);
                add(nos);
                JLabel opp=new JLabel();
                if((int)hold[a][3]==1)opp.setText("BUY");
                else if((int)hold[a][3]==2)opp.setText("SELL");
                opp.setBounds(800,100+b*30,150,30);
                add(opp);
                JButton cancel=new JButton("CANCEL");
                cancel.setBounds(900,100+b*30,100,30);
                cancel.addActionListener(this);
                add(cancel);
            }
    }
    public void actionPerformed(ActionEvent ae)
    {
        act=ae.getActionCommand();
        if(act=="DASHBOARD"){
            call.option=1;
            call.main();
        }
        repaint();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }
}
