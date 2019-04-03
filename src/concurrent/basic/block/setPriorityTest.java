package concurrent.basic.block;


public class setPriorityTest {
    public static void main(String[] args) {
        Thread myThread = new Thread(new RunnablePriority());
        for (int i = 0; i < 100; i++) {
            System.out.println("main thread i = " + i);
            if (i == 20) {
                myThread.setPriority(Thread.MAX_PRIORITY);
                myThread.start();
            }
        }
    }
}
class RunnablePriority implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 100; i++)
            System.out.println("i = ");
//        System.out.println("i = "+i); // 这个费时间,
    }
}