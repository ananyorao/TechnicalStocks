import java.io.*;
class line extends data
{
    //static int[]data[comp]={25,16,18,20,21,19,17,18,22,19,15,20,18};
    static void sar()throws IOException//support and resistance
    {
        input();
        for(int comp=0;comp<companies;comp++){
            if(data[comp][0][0]==0)continue;
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
    }
}            