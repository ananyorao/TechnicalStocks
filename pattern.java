import java.io.*;
class pattern extends data
{
    /*static int companies=1;
    static String company[]={"ratna"};
    static float data[][][]={{{100,111,106,110},{95,105,100,100},{95,105,100,100},{95,105,105,100},{95,105,105,100}}};*/
    static void main()throws IOException
    {
        input();
        disp();
        buy1();
        buy2();
        buy3();
        /*marubozu();
        doji();
        spinning_top();
        rsi();
        engulfing();
        bb();*/
    }
    static void disp()throws IOException//disp company's day info
    {
        System.out.println("OPEN\tHIGH\tLOW\tCLOSE\tCOMPANY_NAME");
        for(int a=0;a<companies;a++)
            System.out.println(data[a][0][2]+"\t"+data[a][0][1]+"\t"+data[a][0][0]+"\t"+data[a][0][3]+"\t"+company[a]);
    }
    static void buy2()throws IOException//crossover in ema9 and ema26
    {
        System.out.println("buys under buy2:");
        for(int comp=0;comp<companies;comp++)
        {
            if(data[comp][0][7]>data[comp][0][6]&&data[comp][1][7]<data[comp][1][6])//crossover signal
            {
                System.out.println(company[comp]);
            }
        }
    }
    static void buy1()throws IOException//(day) spnning_tops/doij + 1 green marubozu
    {
        /**if downtrend for >=4 days and then g marubou with 2/3 days having spinning tops/doji, then buy */
        int day=3;
        System.out.println("buys under buy1:");
        for(int comp=0;comp<companies;comp++)
        {
            if(marubozu(comp,0,0))
            {
                byte fg=0;
                for(int a=1;a<=day;a++)
                    if(doji(comp,a)||spinning_top(comp,a,1)||marubozu(comp,a,1))fg++;
                if(fg>=2)
                    if(data[comp][4][3]>data[comp][3][3]&&data[comp][3][3]>data[comp][2][3]&&data[comp][2][3]>data[comp][1][3])
                        System.out.println(company[comp]);
            }
        }
    }
    static void buy3()throws IOException//(day) downtrend + 1 green marubozu
    {
        System.out.println("\n\ndata for buy3\n");
        FileInputStream fis=new FileInputStream("buy3.dat");
        DataInputStream ddis=new DataInputStream(fis);
        double invest=0,total=0,avail=0,profits=0;
        int num=0;
        double[][]info=new double[4][5];
        invest=ddis.readDouble();System.out.println("invest = "+invest);
        total=ddis.readDouble();System.out.println("total = "+total);
        avail=ddis.readDouble();System.out.println("avail = "+avail);
        profits=ddis.readDouble();System.out.println("profits = "+profits);
        num=ddis.readInt();System.out.println("num = "+num);
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
        /**if downtrend for >=4 days and then g marubou with 3 days having downtrend, then buy */
        FileOutputStream fos1=new FileOutputStream("ledger.dat",true);
        DataOutputStream dos1=new DataOutputStream(fos1);
        int day=3;
        int n=0;
        System.out.println("buys under buy3:");
        for(int comp=0;comp<companies;comp++)
        {
            if(marubozu(comp,0,0))
            {
                byte fg=0;
                for(int a=1;a<=day;a++)
                    if(data[comp][a+1][3]>data[comp][a][3])fg++;
                if(fg==day)
                    if(data[comp][4][3]>data[comp][3][3]&&data[comp][3][3]>data[comp][2][3]&&data[comp][2][3]>data[comp][1][3])
                    {
                        System.out.println(company[comp]);
                        if(time(dat)&&net()&&num<5){info[0][num++]=comp;n++;}                        
                    }
            }
        }
        double each=0;
        byte fg=0;
        if(n!=0)each=avail/n;//balance for each share
        
        for(int a=0;a<5&&time(dat)&&net();a++)
        {            
            if(info[0][a]!=-1)info[3][a]++;//increment days
            if(info[0][a]!=0&&info[3][a]==1&&n!=0){//take stats for new company
                info[1][a]=data[(int)info[0][a]][0][2];
                info[2][a]=Math.rint(each/info[1][a]);
                avail-=info[2][a]*info[1][a];
                String details="3 - "+company[(int)info[0][a]]+" - "+info[1][a]+" - "+info[2][a]+" - "+date;
                byte[]by=details.getBytes();
                dos1.write(by);
            }
            if(info[3][a]==3)System.out.println("sell "+company[(int)info[0][a]]);
            if(info[3][a]==4||((data[(int)info[0][a]][0][1]-info[1][a])*100/info[1][a])>5.0){//sell company
                avail+=data[(int)info[0][a]][0][2]*info[2][a];
                profits=(data[(int)info[0][a]][0][2]-info[1][a])*info[2][a];
                info[0][a]=-1;info[1][a]=0;info[2][a]=0;info[3][a]=0;
                num--;fg++;
            }
        }
        fos1.close();
        dos1.close();
        for(int a=0;a<5;a++)
        {
            if(info[0][a]==-1&&fg--<0)
                for(int b=a;b<5-1;b++)
                {
                    info[0][b]=info[0][b+1];
                    info[1][b]=info[1][b+1];
                    info[2][b]=info[2][b+1];
                    info[3][b]=info[3][b+1];
                }
        }
        FileOutputStream fos=new FileOutputStream("buy3.dat",false);
        DataOutputStream dos=new DataOutputStream(fos);
        dos.writeDouble(invest);System.out.println("invest = "+invest);
        dos.writeDouble(total);System.out.println("total = "+total);
        dos.writeDouble(avail);System.out.println("avail = "+avail);
        dos.writeDouble(profits);System.out.println("profits = "+profits);
        dos.writeInt(num);System.out.println("num = "+num);
        for(int a=0;a<5;a++)
        {
            dos.writeDouble(info[0][a]);
            dos.writeDouble(info[1][a]);
            dos.writeDouble(info[2][a]);
            dos.writeDouble(info[2][a]);
            if(info[0][a]==-1) System.out.println(info[0][a]+"\t"+info[1][a]+"\t"+info[2][a]+"\t"+info[3][a]);
            else System.out.println(company[(int)info[0][a]]+"\t"+info[1][a]+"\t"+info[2][a]+"\t"+info[3][a]);            
        }
        fos.close();
        dos.close();
    }
    static void snr(int comp)throws IOException//support and resistance
    {
        if(data[comp][0][0]==0)return;
        int days=60;//num of days to supervise
        int[]tops=new int[5];//store peaks days index
        int[]bottom=new int[5];//store valley day index
        double[]line=new double[4];//store support and resistance limits
        for(int a=0;a<tops.length;a++)tops[a]=0;
        for(int a=1;a<days;a++)
        {
            for(int b=0;b<tops.length;b++)
                if(data[comp][a][1]>data[comp][tops[b]][1]&&data[comp][a-1][1]<data[comp][a][1]
                &&data[comp][a+1][1]<data[comp][a][1]){//calc peaks & not highs
                    for(int c=tops.length-1;c>b;c--)
                        tops[c]=tops[c-1];
                    tops[b]=a;
                    break;
                }
        }
        int co=0;
        for(int a=1;a<days;a++)
        {
                if(data[comp][a-1][1]<data[comp][a][1]&&data[comp][a+1][1]<data[comp][a][1]){//calc peaks & not highs
                    tops[co++]=a;
                }
        }
        for(int a=0;a<tops.length;a++)
            if(tops[a]==0)break;
            else line[0]=tops[a];
        line[1]=tops[0];
        for(int a=0;a<tops.length&&tops[a]!=0;a++){
            if(a==0||line[0]>data[comp][tops[a]][1])line[0]=data[comp][tops[a]][1];
            if(a==0||line[1]<data[comp][tops[a]][1])line[1]=data[comp][tops[a]][1];
        }       
            
        for(int a=0;a<bottom.length;a++)bottom[a]=tops[0];        
        for(int a=1;a<days;a++)
        {
            for(int b=0;b<bottom.length;b++)
                if(data[comp][a][0]<data[comp][bottom[b]][0]&&data[comp][a-1][0]>data[comp][a][0]
                &&data[comp][a+1][0]>data[comp][a][0]){//calc valleys & not lows
                    for(int c=bottom.length-1;c>b;c--)
                        bottom[c]=bottom[c-1];
                    bottom[b]=a;
                    break;
                }
        } 
        for(int a=0;a<bottom.length&&bottom[a]!=tops[0];a++){
            if(a==0||line[2]>data[comp][bottom[a]][0])line[2]=data[comp][bottom[a]][0];
            if(a==0||line[3]<data[comp][bottom[a]][0])line[3]=data[comp][bottom[a]][0];
        }   
        
        for(int a=0;a<line.length;a++)
            System.out.println(line[a]);
        /*System.out.println();
        for(int a=0;a<bottom.length;a++)
            if(bottom[a]!=tops[0])System.out.println(data[comp][bottom[a]]+"  ");*/
    }
    /*static void peak_valley()throws IOException
    {
        input();
        for(int a=0;a<companies;a++)
        {
            if(data[a][4][1]>data[a][3][1]&&data[a][3][1]>data[a][2][1]&&   //4>3>2>1<0 ->valley
               data[a][2][1]>data[a][1][1]&&data[a][1][1]<data[a][0][1]){
                   if(data[a][0][1]>data[a][4][1])
                       System.out.println(company[a]);
                }
            else if(data[a][5][1]>data[a][4][1]&&data[a][4][1]>data[a][3][1]&&data[a][3][1]>data[a][2][1]&&   //5>4>3>2<1<0 -> peak
               data[a][2][1]<data[a][1][1]&&data[a][1][1]<data[a][0][1]){
                   if(data[a][0][1]>data[a][5][1])
                       System.out.println(company[a]);
                }
        }
    }*/
    static boolean marubozu(int comp,int day,int op)throws IOException //bullish marubozu
    {       
        /**http://www.stock-trading-infocentre.com/marubozu.html*/
        /*2 out of 3 days on dip and then marubozu=buy*/
        //System.out.println("\n\ncompanies undergoing marubozu:");
        //for(int comp=0;comp<companies;comp++)
        {
            if(data[comp][day][3]>data[comp][day][2]&&op==0){   //green        
                double gap=data[comp][day][1]-data[comp][day][0];
                if((data[comp][day][2]-data[comp][day][0])<(gap/10.0))
                {
                    if(gap>(1*data[comp][day][0]/100)){
                        if((data[comp][day][1]-data[comp][day][3])<(gap/10.0)) return true;
                    }
                }
            }
            if(data[comp][day][3]<data[comp][day][2]&&op==1){     //red 
                double gap=data[comp][day][1]-data[comp][day][0];
                if((data[comp][day][3]-data[comp][day][0])<(gap/10.0))
                {
                    if(gap>(1*data[comp][day][0]/100)){
                        if((data[comp][day][1]-data[comp][day][2])<(gap/10.0))return true;
                    }
                }
            }
        }
        return false;
    }
    static boolean doji(int comp,int day)throws IOException
    {
        /**http://www.stock-trading-infocentre.com/doji.html*/
        //System.out.println("\n\ncompanies undergoing doji:");
        //for(int comp=0;comp<companies;comp++)
        {        
            double gap=data[comp][day][1]-data[comp][day][0];
            double change=data[comp][day][2]-data[comp][day][3];
            if(change<0)change=-change;
            if(change<(gap/20.0))
                return true;
        }
        return false;
    }
    static boolean spinning_top(int comp,int day,int op)throws IOException
    {
        //System.out.println("\n\ncompanies undergoing spinning top:");
        //for(int comp=0;comp<companies;comp++)
        {        
            double gap=data[comp][day][1]-data[comp][day][0];
            if(data[comp][day][2]>data[comp][day][3]&&op==1)//red day
            {
                if((data[comp][day][2]-data[comp][day][3])>0.4*gap)return false;
                double diff=(data[comp][day][1]-data[comp][day][2])/(data[comp][day][3]-data[comp][day][0]);
                if(diff>0.9&&diff<1.1)return true;
            }
            else if(data[comp][day][2]<data[comp][day][3]&&op==0)//green day
            {
                if((data[comp][day][3]-data[comp][day][2])>0.4*gap)return false;
                double diff=(data[comp][day][1]-data[comp][day][3])/(data[comp][day][2]-data[comp][day][0]);
                if(diff>0.9&&diff<1.1)return true;
            }
        }
        return false;
    }
    static void rsi(int comp)throws IOException
    {
        int days=20;//num of days to follow
        //for(int comp=0;comp<companies;comp++)
        {
            if(data[comp][days][3]==0)return;
            double rs=0;
            double gain=0;
            double loss=0;
            for(int day=0;day<days;day++)
                if(data[comp][day][3]>data[comp][day+1][3])gain+=data[comp][day][3]-data[comp][day+1][3];
                else if(data[comp][day][3]<data[comp][day+1][3])loss+=data[comp][day+1][3]-data[comp][day][3];
            rs=gain/loss;
            double rsi=100-(100/(1+rs));
            System.out.println(company[comp]+" rsi = "+rsi);
        }
    }
    static boolean engulfing(int comp,int day)throws IOException
    {
        //System.out.println("\n\ncompanies following engulfing pattern");
        //for(int comp=0;comp<companies;comp++)
            if(data[comp][day][0]<data[comp][1][0]&&data[comp][day][1]>data[comp][1][1])return true;
        return false;
    }
    static void bb(int comp)throws IOException //bollinger band
    {
        int days=3;
        //for(int comp=0;comp<companies;comp++)
        {
            double mbb=0,ubb=0,lbb=0;//mbb->middle bollinger band
            for(int day=0;day<days;day++)
                mbb+=data[comp][day][3]/days;
            double sd=0;//standard deviation
            for(int day=0;day<days;day++)
                sd+=(data[comp][day][3]-mbb)*(data[comp][day][3]-mbb);
            sd/=days;
            sd=Math.sqrt(sd);
            ubb=mbb+2*sd;
            lbb=mbb-2*sd;
            //System.out.println(mbb+"\t"+ubb+"\t"+lbb);
        }
    }
}