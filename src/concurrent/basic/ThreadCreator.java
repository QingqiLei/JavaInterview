package concurrent.basic;

public class ThreadCreator {
    public static void main(String[] args){
        MyThread myThread = new MyThread();
        myThread.start();
        Thread thread = new Thread(){
            public void run(){
                System.out.println("inner");
            }
        };
        thread.start();
        Thread thread1 = new Thread(new MyRunnable());
        thread1.start();
        System.out.println(thread1.getName());
    }


    public static class MyRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println("MyRunnable running");

        }
    }
}

