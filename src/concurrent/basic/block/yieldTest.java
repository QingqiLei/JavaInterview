package concurrent.basic.block;

public class yieldTest {
    public static void main(String[] args){
        Thread thread1 = new Thread(new RunnableYield());
        Thread thread2 = new Thread(new RunnableYield());
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        for(int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName()+" "+ i);
            if(i==2){
                thread1.start();
                thread2.start();
                Thread.yield();
                Thread.yield();
            }
        }
    }
}

class RunnableYield implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i + " "+ Thread.currentThread().getPriority()); // 费时间
        }
    }
}
