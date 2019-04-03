package concurrent.basic.block;

public class JoinTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnablejoin());
        for(int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName()+" "+i);
            if(i == 30) thread.start();
            try{
                thread.join(); // main 线程要等待thread 线程执行完, 才能继续执行
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

 class MyRunnablejoin implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++)
            System.out.println(Thread.currentThread().getName() + " " + i);
    }
}
