package concurrent.basic.block;

import concurrent.basic.MyThread;

public class setDaemonTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnableDaemon());
        for (int i = 0; i < 100; i++) {
            System.out.println("main thread i = " + i);
            if (i == 20) {

                thread.setDaemon(true);
                thread.start();
            }
        }

    }
}

class MyRunnableDaemon implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("i= " + i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
