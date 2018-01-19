import java.net.*;
import java.io.*;
import java.util.Date;
class data
{       
    static final int days=90;//total days for which data is sotred
    static char[]ar=new char[100000];   //to read data from html
    static int companies=0;//total number of companies in data sheet
    static String[]ipar;//company url
    static String[]company;//company name
    static String[]code;//company code    
    static double[]trans=new double[20]; //data to be stored in the file with that day info
    static double[][][]data;
    static String dat="";//store date from file data.dat
    static byte fg=0;//repeated data from prev day during next morning
    static void input()throws IOException//executable file of the program
    {
        BufferedReader rao=new BufferedReader(new InputStreamReader(System.in));
        c_read();
        //f_store();
        f_read();        
        //System.out.println("\n"+net());
        //while(true)
        {
            //c_add();
            if(time(dat)&&net())
            {      
                System.out.println("\nin");
                f_store();            
                f_read();
                System.out.println("\n\nneed backup??y/n");
                String bu=rao.readLine();
                if(bu.equalsIgnoreCase("y"))backup();
            }
            //delay();
        }
        //while(time(date)){}
    }

    static void c_add()throws IOException//add companies
    {
        String fn="company.dat";
        FileOutputStream fos=new FileOutputStream(fn,true);
        DataOutputStream dos=new DataOutputStream(fos);
        BufferedReader rao=new BufferedReader(new InputStreamReader(System.in));        
        System.out.println("\nenter the company\n");
        String a=rao.readLine();
        char ch='\n';        
        System.out.println("\nenter the URL\n"+companies+"\n");
        String a1=rao.readLine();
        for(int c=0;c<companies;c++)
        {
            //System.out.println(ipar[c]);
            if(a.contains(company[c])){
                System.out.println("company already in list");
                return;
            }
        }
        byte[]b=a.getBytes();
        dos.write(b);
        dos.writeChar(ch);
        byte[]b1=a1.getBytes();
        dos.write(b1);        
        dos.writeChar(ch);
        fos.close();
        dos.close();
    }
    static void c_read()throws IOException//read companies
    {
        companies=0;
        String fn="company.dat";
        FileInputStream fis=new FileInputStream(fn);
        DataInputStream ddis=new DataInputStream(fis);
        try{
            while(true){
               String str=ddis.readLine();
               if(str.equals("null"))break;
               companies++;
               ddis.readLine();
            }
        }
        catch(Exception d){}//System.out.println("not works");}   
        ipar=new String[companies];
        company=new String[companies];
        data=new double[companies][days][20];
        for(int comp=0;comp<companies;comp++)
                for(int a=0;a<90-1;a++)
                    for(int b=0;b<trans.length;b++)data[comp][a][b]=0.0;
        fis=new FileInputStream(fn);
        ddis=new DataInputStream(fis);
        try{
            for(int a=0;a<companies;a++){
                String str=ddis.readLine();
                if(str.equals("null"))break;
                company[a]=str;                
                ipar[a]=ddis.readLine();
                System.out.println((a+1)+"  "+company[a]);
            }
        }
        catch(Exception d){System.out.println(d+"1");}
    }
    
    static void delay()//set delay for repeated program execution
    {
        try
        {
            int time=1800;//minutes to delay
            Thread.sleep(time*1000);   
        } catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }
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
    static void asign(int num)throws IOException//get company[num] day trading prices into trans[]-->a_low, a_high, d_low, d_high, close, 
    {
        for(int a=0;a<trans.length;a++)trans[a]=0;
        double rot=0;        
        String gri=ipar[num];
        URL my=null;
        InputStream inps;
        try
        {
            my=new URL(gri);
            String proto=my.getProtocol();
            int griport=my.getPort();            
            Object ob=my.getContent();            
            inps=my.openStream();            
            int c,co=0;     
            while(((c=inps.read())!=-1)&&(co<100000))
            {
                ar[co]=(char)c;co++;                     
            }    
            
            //disp html of the file
            /*BufferedReader br = new BufferedReader(new InputStreamReader(my.openStream()));
            String inputLine;
            while ((inputLine = br.readLine()) != null)
                System.out.println(inputLine);
            br.close();*/
            
            co=0;
            inps.close();
            String disp=new String(ar);
            char[]ar1=new char[10];
            char[]ar2=new char[10];
            int in=disp.indexOf("span id=",10000);            
            for(int a=in,b=0;b<7;a++)
            {
                if(ar[a]=='<')break;                
                if(co!=0){if(ar[a]==',')continue;else ar1[b]=ar[a];b++;}
                if(ar[a]=='>')co++;
            }
            String show=new String(ar1);            
            rot=Double.parseDouble(show);//day close rate
            trans[4]=rot;
            show="";
            in=disp.indexOf("vol_and_avg");
            int z=0;
            for(z=in+40;ar[z]!='M'&&ar[z]!='\n';z++)//day volume trade
            {
                if(ar[z]==',')continue;
                show+=ar[z];
            }
            if(ar[z]=='.');
            else if(ar[z]=='M')show+="e6";
            rot=Double.parseDouble(show);
            trans[6]=rot;
            
            show="";
            in=disp.indexOf("market_cap");
            for(z=in+42;ar[z]!='B'&&ar[z]!='T';z++)//total market capital
                show+=ar[z];
            if(ar[z]=='B') show+="e9";
            else if(ar[z]=='T')show+="e12";
            rot=Double.parseDouble(show);
            trans[7]=rot;
            
            int use=disp.lastIndexOf("Range");            
            in=disp.lastIndexOf("range",use+200);co=0;            
            for(int a=in+40,b=0;b<20;a++)  //annual range
            {
                if(ar[a]=='<')break;   
                if(ar[a]=='-'){co++;b=0;continue;}
                if(co==1){if(ar[a]==',')continue;else ar1[b]=ar[a];b++;}
                if(co==2){if(ar[a]==',')continue;else ar2[b]=ar[a];b++;}
                if(ar[a]=='>')co++;
            }
            show=new String(ar1);
            trans[0]=Double.parseDouble(show.trim());    
            show=new String(ar2);
            trans[1]=Double.parseDouble(show.trim());
            co=0;
            for(int a=use+20,b=0;b<7;a++)
            {
                if(ar[a]==' ')break;                
                if(co!=0){if(ar[a]==',')continue;else ar1[b]=ar[a];b++;}
                if(ar[a]=='>')co++;
            }
            String sho=new String(ar1);
            try
            {
                trans[2]=Double.parseDouble(sho);co=0;//day low                
                for(int a=use+20,b=0;b<7;a++)
                {
                    if(ar[a]=='<')break;                
                    if(co==1){if(ar[a]==',')continue;else ar1[b]=ar[a];b++;}
                    if(ar[a]=='-')co++;
                }
                String sh=new String(ar1);            
                trans[3]=Double.parseDouble(sh.trim());    //day high
                use=disp.indexOf(">Open");
                ar1=new char[10];
                for(int a=use+28,b=0;b<20;a++)  //day open
                {
                    if(ar[a]=='\n'||ar[a]==' ')break;   
                    if(ar[a]==',')continue;
                    else ar1[b]=ar[a];b++;
                }
                show=new String(ar1);
                trans[5]=Double.parseDouble(show.trim());   
                
                //for(int a=0;a<trans.length;a++)System.out.println(trans[a]);
            }
            catch(Exception e1)
            {                
                System.out.println(e1+"2");
            }
        }
        catch(Exception e)
        {
            System.out.println(e+"3");
        }
    }
    
    static void ema(int comp)//calc & store ema and macd
    {        
        double multi12=(2/(12.0+1.0));
        double multi26=(2/(26.0+1.0));
        double multi9=(2/(9.0+1.0));
        //System.out.println(multi12+"\t"+multi26+"\t"+multi9);
        if(data[comp][1][5]==0)data[comp][1][5]=data[comp][1][3];//EMA-12
        if(data[comp][1][6]==0)data[comp][1][6]=data[comp][1][3];//EMA-26
        if(data[comp][1][7]==0)data[comp][1][7]=data[comp][1][3];//EMA-9
        data[comp][0][5]=((data[comp][0][3]-data[comp][1][5])*multi12)+data[comp][1][5];
        data[comp][0][6]=((data[comp][0][3]-data[comp][1][6])*multi26)+data[comp][1][6];
        data[comp][0][7]=((data[comp][0][3]-data[comp][1][7])*multi9)+data[comp][1][7];
        data[comp][0][8]=data[comp][0][5]-data[comp][0][6];
    } 
    
    static void f_store()throws IOException //store day info from data[][][] into file
    {
        if(!net())return;
        boolean bob=time("");
        String fn="data.dat";     
        for(int comp=0;comp<companies;comp++)
        {
            if(fg==0)
            {
                asign(comp);
                if((data[comp][0][0]==trans[2]&&data[comp][0][1]==trans[3]&&data[comp][0][2]==trans[5]&&data[comp][0][3]==trans[4])||
                trans[2]==0){if(trans[2]!=0)fg++;continue;}
                for(int a=89;a>0;a--)
                    for(int b=0;b<trans.length;b++)data[comp][a][b]=data[comp][a-1][b];
                data[comp][0][0]=trans[2];
                data[comp][0][1]=trans[3];
                data[comp][0][2]=trans[5];
                data[comp][0][3]=trans[4];
                data[comp][0][4]=trans[6];
                ema(comp);
            }
        }
        FileOutputStream fos=new FileOutputStream(fn,false);
        DataOutputStream dos=new DataOutputStream(fos);
        date+='\n';
        byte[]by=date.getBytes();
        dos.write(by);
        for(int com=0;com<companies;com++)
            for(int a=0;a<90-1;a++)
                for(int b=0;b<trans.length;b++) 
                    dos.writeDouble(data[com][a][b]);
        fos.close();
        dos.close();
    }    
    static void f_read()throws IOException//get data from file into data[][][]
    {     
        String fn="data.dat";
        FileInputStream fis=new FileInputStream(fn);
        DataInputStream ddis=new DataInputStream(fis);
        dat=ddis.readLine();        
        try{
            //System.out.println(dat);            
            for(int comp=0;comp<companies;comp++)
            {
                //System.out.println();
                for(int a=0;a<90-1;a++)
                    for(int b=0;b<trans.length;b++){
                        data[comp][a][b]=ddis.readDouble();
                        if(data[comp][a][b]<0.01d)data[comp][a][b]=0.0d;
                        //System.out.print(data[comp][a][b]+"  ");
                    }
            }
        }
        catch(Exception d){System.out.println(d+"4");}
    }     
    static void backup()throws IOException//take data backup similar to data.dat
    {
        String fn="data_backup.dat";
        FileOutputStream fos=new FileOutputStream(fn,false);
        DataOutputStream dos=new DataOutputStream(fos);
        date+='\n';
        byte[]by=date.getBytes();
        dos.write(by);
        for(int com=0;com<companies;com++)
            for(int a=0;a<90-1;a++)
                for(int b=0;b<trans.length;b++) 
                    dos.writeDouble(data[com][a][b]);
        fos.close();
        dos.close();
    }
   
   static String date="";
   static boolean time(String d)//get time in int time and date in string date
   {
       date="";
       // Instantiate a Date object
       Date dat = new Date();
       String st=dat.toString();
       //System.out.println("\n\n"+st+"\n"+d+"\n\n");
       int a=0,b=0;
       for(;a<st.length()&&b<3;a++)
       {
           date+=st.charAt(a);
           if(st.charAt(a)==' ')b++;
       }
       String tim="";
       while(st.charAt(a)!=' '){
           if(st.charAt(a)==':'){a++;continue;} 
           tim+=st.charAt(a);
           a++;
       }
       int time=Integer.parseInt(tim);  
       //System.out.println("\n"+st);
       //date+='.';  //day miss count
       if(time<90000){
           date+='.';
           if(date.equals(d))return false;
           return true;
        }
       if(date.equals(d)||time<160000)return false;
       else return true;
   }
}