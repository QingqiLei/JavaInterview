package concurrent.basic;

public class huchitongbu {
    public static void main(String[] args) {
        final Bussiness bussiness = new Bussiness();
//子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    bussiness.subMethod();
                }
            }
        }).start();
//主线程
        for (int i = 0; i < 3; i++) {
            bussiness.mainMethod();
        }
    }
}

class Bussiness{
    private boolean subFlag = true; // 第一次运行sub
    public synchronized void mainMethod(){
        while(subFlag){
            try{
                wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        for(int i = 0; i < 5; i++){
            System.out.println(Thread.currentThread().getName() + " : main thread running loop count --"+i);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        subFlag = true;
        notify();// 唤醒其他线程, 下一个运行sub.
    }

    public synchronized void subMethod(){

        while(!subFlag){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        for(int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " : sub thread running loop count --" + i);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e ){
                e.printStackTrace();
            }
        }
        subFlag = false;
        notify();

    }

}
