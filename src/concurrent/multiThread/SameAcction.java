package concurrent.multiThread;

class ShareData { // 相同的数据源,
    private int num = 10;
    public synchronized  void inc (){ // 不同线程同步
        num ++;
        System.out.println(Thread.currentThread().getName()+" : invoke inc method num = "+ num);
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class RunnableCusToInc implements  Runnable{
    private ShareData shareData;
    public RunnableCusToInc(ShareData data){
        this.shareData = data;
    }

    @Override
    public void run() {
        for(int i = 0; i < 5; i++)
            shareData.inc();
    }
}

public class SameAcction{
    public static void main(String[] args){
        ShareData shareData = new ShareData();
        for(int i = 0; i < 4; i++)
            new Thread(new RunnableCusToInc(shareData)).start();
    }
}