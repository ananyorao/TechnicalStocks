import java.awt.*;
import javax.swing.*;
import java.io.*;
public class call extends JFrame
{
    static int option=1;
    call(String s)
    {
        super(s);
        Container con=getContentPane();
        setSize(1300,1000);
        if(option==1){
            con.add(new dashboard());
        }
        else if(option==2){
            con.add(new orderbook());
        }
        else if(option==3)
            con.add(new holdings());
    }
    static void main()
    {
        call cal=new call("jbuttons");
        cal.setVisible(true);
    }
}