import javax.swing.*;
import java.awt.*;
import java.awt.FlowLayout;
public class japp extends JApplet
{
    public void init()
    {
        //super(str);
        //setSize(200,200);
        JButton b1=new JButton("one");
        JButton b2=new JButton("two");
        JButton b3=new JButton("three");
        Container con1;
        //if(a==1){
            con1=getContentPane();
            con1.setLayout(new FlowLayout());
            con1.add(b1);
            con1.add(b2);
            con1.add(b3);
        /*}
        else if(a==2){
            Container con2=getContentPane();
            con2.add(b1);
        }
    }
    static void main(int a)
    {
        JFrame jf=new jpack("three musketeers",a);
        jf.setVisible(true);*/
    }
}