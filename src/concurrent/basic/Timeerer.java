package concurrent.basic;

import java.util.Timer;
import java.util.TimerTask;

public class Timeerer {

    private static volatile int count = 0;
    static class  TimerTastCus extends TimerTask{

        @Override
        public void run() {
            count  = (count + 1) % 2;
            System.out.println("boom boom");
            new Timer().schedule(new TimerTastCus(),1000+2000*count);
        }
    }
    public static void main(String[] args){
        new Timer().schedule(new TimerTastCus(),2000+2000*count);
    }

}
