import java.applet.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.io.*;
import java.util.Date;
//import java.awt.Graphics;
//import java.awt.event.*;
public class tic extends Applet
{    
    //data d=new data();
    int a=0;
    static boolean net()throws IOException
    {
        try{
            URL my=new URL("https://www.google.co.in/?gfe_rd=cr&ei=XGsSVo7rKrPG8AfBqYzQDQ&gws_rd=ssl");
            String proto=my.getProtocol();
            int griport=my.getPort(); 
            Object ob=my.getContent(); 
            InputStream inps=my.openStream();
            return true;
        }
        catch(Exception e){
            System.out.println("\nno net connection");
            return false;
        }
    }
    public void paint(Graphics g)//throws IOException
    {
        //while(true)
        {            
            try{
                g.setColor(Color.black);
                //if(d.net()){
                if(net()){
                    a=2;
                    g.drawString("true",30,30);
                }
                else{
                    a=1;
                    g.drawString("false",30,30);
                }
                /*if(a%2==0){
                    g.setColor(Color.green);
                    g.fillRect(0,0,500,500);
                }
                else{
                    g.setColor(Color.red);
                    g.fillRect(0,0,500,500);
                }  */
            }
            catch(Exception e){}
            /*try
            {
                Thread.sleep(5000);   
            } catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }*/
        }
    }
}