import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class act extends JPanel implements ActionListener
{
    String str=null;
    int count=0;
    act()
    {
        JButton jb1=new JButton("one");
        this.setLayout(null);
        //jb1.setLayout(null);
        jb1.addActionListener(this);
        jb1.setBounds(50,50,100,100);
        //jb1.setBounds(10,10,20,10);
        add(jb1);
    }
    public void actionPerformed(ActionEvent ae)
    {
        str=ae.getActionCommand();
        count++;
        repaint();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(count%2==0)
        {
            g.setColor(Color.black);
            g.drawOval(0,0,50,50);
            //g.drawString("one",200,200);
        }
        else
        {
            g.setColor(Color.blue);
            g.drawOval(0,0,50,50);
        }
    }
}