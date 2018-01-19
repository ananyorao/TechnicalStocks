import java.io.*;
class file_writer
{
    static void main()throws IOException
    {
        FileOutputStream fos=new FileOutputStream("virtual.dat",false);
        DataOutputStream dos=new DataOutputStream(fos);
        double invest=10000,profits=0,total=invest+profits,avail=0;
        int num=0;
        double[][]info=new double[4][5];
        dos.writeDouble(invest);
        dos.writeDouble(total);
        dos.writeDouble(avail);
        dos.writeDouble(profits);
        dos.writeInt(num);
        for(int a=0;a<5;a++)
        {
            info[0][a]=-1;dos.writeDouble(info[0][a]);
            info[1][a]=0;dos.writeDouble(info[1][a]);
            info[2][a]=0;dos.writeDouble(info[2][a]);
            info[3][a]=-1;dos.writeDouble(info[2][a]);
        }
        fos.close();
        dos.close();
    }
}