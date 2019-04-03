package concurrent.multiThread;


class RunnableCusToInc1 implements Runnable {
    private ShareData1 shareData;

    public RunnableCusToInc1(ShareData1 shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++)
            shareData.inc();
    }
}

class RunnableCustoDec implements Runnable {
    private ShareData1 shareData;

    public RunnableCustoDec(ShareData1 shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++)
            shareData.dnc();
    }
}

class ShareData1 {
    private int num = 10;

    public synchronized void inc() {
        num++;
        System.out.println(Thread.currentThread().getName() + ": invoke inc method num = " + num);
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void dnc() {
        num--;
        System.out.println(Thread.currentThread().getName() + " : invoke dnc method num = " + num);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class DifferentAcction {
    public static void main(String[] args) {
        ShareData1 shareData = new ShareData1();
        for (int i = 0; i < 4; i++) {
            if (i % 2 == 0) {
                new Thread(new RunnableCusToInc1(shareData), "Thread " + i).start();
            } else {
                new Thread(new RunnableCustoDec(shareData), "Thread " + i).start();
            }
        }

    }
}