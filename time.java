public class time
{    
    public static void delay()
    {
        // This program demonstrate how to add a delay in our application.
        try
        {
            // Using Thread.sleep() we can add delay in our application in
            // a millisecond time. For the example below the program will 
            // take a deep breath for one second before continue to print
            // the next value of the loop.
            Thread.sleep(5000);                
            // The Thread.sleep() need to be executed inside a try-catch
            // block and we need to catch the InterruptedException.
        } catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }
    static void main()
    {
        int a=0;
        while(true){
            delay();
            System.out.println(a++);
        }
    }
}