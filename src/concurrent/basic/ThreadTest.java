package concurrent.basic;

public class ThreadTest {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+" "+i);

            if(i == 30) thread.start();
            if(i == 20) myRunnable.stopThread();
        }
    }
}

class MyRunnable implements Runnable {
    private boolean stop;

    @Override
    public void run() {
        for (int i = 0; i < 100 && !stop; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        stop = false;
        notify();
    }

    public void stopThread() {
        this.stop = true;
    }
}