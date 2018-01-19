import java.io.*;
class file_reader
{
    static void main()throws IOException
    {
        FileInputStream fis=new FileInputStream("virtual.dat");
        DataInputStream ddis=new DataInputStream(fis);
        double invest=0,total=0,avail=0,profits=0;
        int num=0;
        double[][]info=new double[4][5];
        invest=ddis.readDouble();System.out.println(invest);
        total=ddis.readDouble();System.out.println(total);
        avail=ddis.readDouble();System.out.println(avail);
        profits=ddis.readDouble();System.out.println(profits);
        num=ddis.readInt();System.out.println(num);
        for(int a=0;a<5;a++)
        {
            info[0][a]=ddis.readDouble();
            info[1][a]=ddis.readDouble();
            info[2][a]=ddis.readDouble();
            info[3][a]=ddis.readDouble();
            System.out.println(info[0][a]+"\t"+info[1][a]+"\t"+info[2][a]+"\t"+info[3][a]);
        }
        fis.close();
        ddis.close();
    }
}