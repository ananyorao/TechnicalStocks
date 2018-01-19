import java.io.*;
class patterns extends data
{
    static void main()throws IOException
    {
        input();
        marubozu();
        doji();
        spinning_top();
        rsi();
        engulfing();
        bb();
    }
    static void candlestick()throws IOException
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
    }
    static void marubozu()throws IOException //bullish marubozu
    {       
        /**http://www.stock-trading-infocentre.com/marubozu.html*/
        /*2 out of 3 days on dip and then marubozu=buy*/
        System.out.println("\n\ncompanies undergoing marubozu:");
        for(int comp=0;comp<companies;comp++)
        {
            if(data[comp][0][3]>data[comp][0][2]){           
                double gap=data[comp][0][1]-data[comp][0][0];
                if((data[comp][0][2]-data[comp][0][0])<(gap/10.0))
                {
                    if(gap>(1*data[comp][0][0]/100)){
                        if((data[comp][0][1]-data[comp][0][3])<(gap/10.0))
                        {
                            int dips=0;//num of low days before marubozu
                            for(int b=1;b<4;b++)if(data[comp][b][2]>data[comp][b][3])dips++;
                            if(dips>=2) 
                                System.out.println(company[comp]);
                        }
                    }
                }
            }
        }
    }
    static void doji()throws IOException
    {
        /**http://www.stock-trading-infocentre.com/doji.html*/
        System.out.println("\n\ncompanies undergoing doji:");
        for(int comp=0;comp<companies;comp++)
        {        
            double gap=data[comp][0][1]-data[comp][0][0];
            double change=data[comp][0][2]-data[comp][0][3];
            if(change<0)change=-change;
            if(change<(gap/20.0))
                System.out.println(company[comp]);
        }
    }
    static void spinning_top()throws IOException
    {
        System.out.println("\n\ncompanies undergoing spinning top:");
        for(int comp=0;comp<companies;comp++)
        {        
            double gap=data[comp][0][1]-data[comp][0][0];
            if(data[comp][0][2]>data[comp][0][3])//red day
            {
                if((data[comp][0][2]-data[comp][0][3])>0.4*gap)continue;
                double diff=(data[comp][0][1]-data[comp][0][2])/(data[comp][0][3]-data[comp][0][0]);
                if(diff>0.9&&diff<1.1)System.out.println("red --> "+company[comp]);
            }
            else if(data[comp][0][2]<data[comp][0][3])//green day
            {
                if((data[comp][0][3]-data[comp][0][2])>0.4*gap)continue;
                double diff=(data[comp][0][1]-data[comp][0][3])/(data[comp][0][2]-data[comp][0][0]);
                if(diff>0.9&&diff<1.1)System.out.println("green --> "+company[comp]);
            }
        }
    }
    static void rsi()throws IOException
    {
        int days=20;//num of days to follow
        for(int comp=0;comp<companies;comp++)
        {
            if(data[comp][days][3]==0)continue;
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
    static void engulfing()throws IOException
    {
        System.out.println("\n\ncompanies following engulfing pattern");
        for(int comp=0;comp<companies;comp++)
            if(data[comp][0][0]<data[comp][1][0]&&data[comp][0][1]>data[comp][1][1])System.out.println(company[comp]);
    }
    static void bb()throws IOException //bollinger band
    {
        int days=3;
        for(int comp=0;comp<companies;comp++)
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