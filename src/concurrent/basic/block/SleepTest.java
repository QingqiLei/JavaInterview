package concurrent.basic.block;

public class SleepTest {


    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnableSleep());
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread() + " " + i);
            if (i == 30) {
                thread.start();
                try {
                    Thread.sleep(10); // 让thread 在for 循环31次前开始执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class MyRunnableSleep implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}