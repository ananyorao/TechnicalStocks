import java.net.*;
import java.io.*;
import java.util.Date;
class inflation//cpi, exchange rates, crude oil, gold, interest rates, gdp, nifty, sensex, inflation rate
{
    static char[]ar=new char[100000]; 
    static void assign()
    {
        double trans[]=new double[10];
        double rot=0;        
        String gri="http://www.tradingeconomics.com/india/indicators";
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
            char[]ar1=new char[15];
            int in=disp.indexOf("GDP Annual Growth Rate");            
            for(int a=in+55,b=0;ar[a]!='<';a++)
            {
                if(ar[a]=='<')break;                
                if(co!=0){if(ar[a]==',')continue;else ar1[b]=ar[a];b++;}
                if(ar[a]=='>')co++;
            }
            String show=new String(ar1);            
            rot=Double.parseDouble(show);
            trans[0]=rot;            
            ar1=new char[15];co=0;
            in=disp.indexOf("Inflation Rate</a></td");            
            for(int a=in+50,b=0;ar[a]!='<';a++)
            {
                if(ar[a]=='<')break;                
                if(co!=0){if(ar[a]==',')continue;else ar1[b]=ar[a];b++;}
                if(ar[a]=='>')co++;
            }
            show=new String(ar1);            
            rot=Double.parseDouble(show);
            trans[1]=rot;            
            ar1=new char[15];co=0;
            in=disp.indexOf("Currency</a></td");            
            for(int a=in+45,b=0;ar[a]!='<';a++)
            {
                if(ar[a]=='<')break;                
                if(co!=0){if(ar[a]==',')continue;else ar1[b]=ar[a];b++;}
                if(ar[a]=='>')co++;
            }
            show=new String(ar1);            
            rot=Double.parseDouble(show);
            trans[2]=rot;            
            ar1=new char[15];co=0;
            in=disp.indexOf("Interest Rate</a></td");            
            for(int a=in+50,b=0;ar[a]!='<';a++)
            {
                if(ar[a]=='<')break;                
                if(co!=0){if(ar[a]==',')continue;else ar1[b]=ar[a];b++;}
                if(ar[a]=='>')co++;
            }
            show=new String(ar1);            
            rot=Double.parseDouble(show);
            trans[3]=rot;            
            ar1=new char[15];co=0;
            in=disp.indexOf("Consumer Price Index CPI</a></td");            
            for(int a=in+60,b=0;ar[a]!='<';a++)
            {
                if(ar[a]=='<')break;                
                if(co!=0){if(ar[a]==',')continue;else ar1[b]=ar[a];b++;}
                if(ar[a]=='>')co++;
            }
            show=new String(ar1);            
            rot=Double.parseDouble(show);
            trans[4]=rot;
        }
        catch(Exception e)
        {
            System.out.println(e+"1");
        }
        
        gri="http://m.moneycontrol.com/commodity";
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
            br.close(); */            
            co=0;
            inps.close();
            String disp=new String(ar);
            String show="";
            char[]ar1=new char[15];
            int in=disp.indexOf("sensex_value");            
            for(int a=in+10,b=0;ar[a]!='<';a++)
            {
                if(ar[a]=='<')break;                
                if(co!=0){if(ar[a]==',')continue;else show+=ar[a];}
                if(ar[a]=='>')co++;
            }    
            rot=Double.parseDouble(show);
            trans[5]=rot;            
            ar1=new char[15];show="";co=0;          
            in=disp.indexOf("nifty_value");            
            for(int a=in+10,b=0;ar[a]!='<';a++)
            {
                if(ar[a]=='<')break;                
                if(co!=0){if(ar[a]==',')continue;else show+=ar[a];}
                if(ar[a]=='>')co++;
            }
            rot=Double.parseDouble(show);
            trans[6]=rot;            
            ar1=new char[15];show="";co=0;          
            in=disp.indexOf("GOLD </a> <br");            
            for(int a=in+105,b=0;ar[a]!='<';a++)
            {
                if(ar[a]=='<')break;                
                if(co!=0){if(ar[a]==',')continue;else show+=ar[a];}
                if(ar[a]=='>')co++;
            }
            rot=Double.parseDouble(show);
            trans[8]=rot;            
            ar1=new char[15];show="";co=0;            
            in=disp.indexOf("CRUDEOIL </a> <br");            
            for(int a=in+110,b=0;ar[a]!='<';a++)
            {
                if(ar[a]=='<')break;                
                if(co!=0){if(ar[a]==',')continue;else show+=ar[a];}
                if(ar[a]=='>')co++;
            }
            rot=Double.parseDouble(show);
            trans[7]=rot;
        }
        catch(Exception e)
        {
            System.out.println(e+"2");
        }
        System.out.println("GDP%           = "+trans[0]);
        System.out.println("INFLATION RATE = "+trans[1]);
        System.out.println("CURRENCY RATE  = "+trans[2]);
        System.out.println("INTEREST RATES = "+trans[3]);
        System.out.println("CPI            = "+trans[4]);
        System.out.println("SENSEX         = "+trans[5]);
        System.out.println("NIFTY          = "+trans[6]);
        System.out.println("CRUDE OIL      = "+trans[7]);
        System.out.println("GOLD           = "+trans[8]);
    }
}