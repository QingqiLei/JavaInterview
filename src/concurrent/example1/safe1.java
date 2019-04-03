package concurrent.example1;
import java.util.Date;

public class safe1 {
    public static void main(String[] args){
        SafeAccount  safeAccount = new SafeAccount("123456",6);

        Thread drawMoneyThread  = new Thread(new SafeDrawMoneyRunnable(safeAccount, 700));


        Thread depositemoneyThraed = new Thread(new SafedepositeMoneyRunnable(safeAccount, 700));



        drawMoneyThread.start();
        depositemoneyThraed.start();

    }
}


class SafeDrawMoneyRunnable implements Runnable {
    private SafeAccount safeAccount;
    private double amount;

    public SafeDrawMoneyRunnable(SafeAccount safeAccount, double amount) {
        this.safeAccount = safeAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i++){

                safeAccount.draw(700,i);
        }
    }
}

class SafedepositeMoneyRunnable implements Runnable {
    private SafeAccount safeAccount;
    private double amount;

    public SafedepositeMoneyRunnable(SafeAccount safeAccount, double amount) {
        this.safeAccount = safeAccount;
        this.amount = amount;
    }

    public void run() {
        for (int i = 0; i < 100; i++) // 只是模拟, 存一百次
            safeAccount.deposite(amount, i);
    }
}


class SafeAccount {
    private String accountNo;
    private double balance;
    // 标识账户中是否已有存款
    private volatile boolean  flag = false;

    public SafeAccount() {

    }

    public SafeAccount(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public synchronized void deposite(double depositeAmount, int i) {

        if (flag) {
            try {
                System.out.println(Thread.currentThread().getName() + " 开始执行wait 操作" + " i = " + i + " "+new Date(System.currentTimeMillis()));
                wait();
                System.out.println(Thread.currentThread().getName() + " wait操作 " + i + " 结束了"+ " "+new Date(System.currentTimeMillis()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // start to deposite
            System.out.println(Thread.currentThread().getName() + " 存款:" + depositeAmount + " -- i=" + i+ " "+new Date(System.currentTimeMillis()));
            setBalance(balance + depositeAmount);
            flag = true;
            System.out.println(Thread.currentThread().getName() + "-- 存钱 -- 执行完毕" + " -- i=" + i+ " "+new Date(System.currentTimeMillis()));
            notifyAll();
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public synchronized void draw(double drawAmount, int i) {
        if (!flag) {
            try {
                System.out.println(Thread.currentThread().getName() + " 没有存款,开始要执行wait操作" + " 执行了wait操作" + " -- i=" + i+ " "+new Date(System.currentTimeMillis()));
                wait();
                System.out.println(Thread.currentThread().getName() + " 执行了wait操作" + " 执行了wait操作" + " -- i=" + i+ " "+new Date(System.currentTimeMillis()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // 开始取钱
            System.out.println(Thread.currentThread().getName() + " 取钱：" + drawAmount + " -- i=" + i+ " "+new Date(System.currentTimeMillis()));
            setBalance(getBalance() - drawAmount);
            flag = false;
            notifyAll();
            System.out.println(Thread.currentThread().getName() + "-- 取钱 -- 执行完毕" + " -- i=" + i+ " "+new Date(System.currentTimeMillis()));
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }


    }
}
