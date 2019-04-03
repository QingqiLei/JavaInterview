package concurrent.consumerproducer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo1 {
    public static void main(String[] args){
        storage storage = new storage(100);
        new Thread(new Runnable(){

            @Override
            public void run() {
                storage.produce(70);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                storage.produce(9);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                storage.produce(1);
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                storage.consume(80);
            }
        }).start();
    }
}

class storage {
    private int MAX_SIZE;
    private LinkedList<Object> list = new LinkedList<>();

    private Lock lock = new ReentrantLock();
    private Condition produceCondition = lock.newCondition();
    private Condition consumeCondition = lock.newCondition();

    public storage(int MAX_SIZE) {
        this.MAX_SIZE = MAX_SIZE;
    }

    public void produce(int num) {
        lock.lock();
        try {
            while ((list.size() + num) >= MAX_SIZE) {
                System.out.println("要生产: " + num + " 个" + ", 库存: " + list.size() + ", 剩余空间: " + (MAX_SIZE - list.size()) + ", 无法生产");
                try {
                    produceCondition.await(); // 会让调用此produce方法的线程阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < num; i++) {

                list.addFirst(new Object());
            }


            System.out.println("生产了" + num + ", 现在库存" + list.size());
            consumeCondition.signalAll();


        } finally {
            lock.unlock();
        }
    }


    public void consume(int num) {
        lock.lock();
        try {
            while (list.size() < num) {
                System.out.println("要消费:" + num + ", 库存:" + list.size() + "无法消费");
                try {
                    consumeCondition.await(); // 会让调用此consume方法的线程阻塞

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < num; i++)
                list.removeLast();

            System.out.println("消费了"+num+" 剩余"+list.size());

            produceCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}

