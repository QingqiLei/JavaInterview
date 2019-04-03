package concurrent.example1;

public class unsafeTest {
    public static void main(String[] args) {
        Account account = new Account("12345", 1000);
        DrawMoneyRunnable drawMoneyRunnable = new DrawMoneyRunnable(account, 700);
        Thread thread1 = new Thread(drawMoneyRunnable);
        Thread thread2 = new Thread(drawMoneyRunnable);
        thread1.start();
        thread2.start();
    }

}

class DrawMoneyRunnable implements Runnable {
    private Account account;
    private double drawAmount;

    public DrawMoneyRunnable(Account account, double drawAmount) {
        super();
        this.account = account;
        this.drawAmount = drawAmount;
    }


    public void run() {
        System.out.println("现在有: "+account.getBalance());
        if (account.getBalance() >= drawAmount) {  //1

            System.out.println("取钱成功， 取出钱数为：" + drawAmount);
            double balance = account.getBalance() - drawAmount;
            account.setBalance(balance);
            System.out.println("余额为：" + balance);
        }
    }


//    @Override // 或者使用这段代码, 可能会出现更加奇特的事情(两个人取钱, 结果只扣费一次)
//    public void run() { // 两个线程并发
//        double current = account.getBalance();
//        if(current >= drawAmount){
//            System.out.println("现在有: "+current);
//            System.out.println(" 成功, 取钱: "+ drawAmount);
//            double balance = current - drawAmount;
//            account.setBalance(balance);
//            System.out.println("余额: "+balance);
//        }
//    }



}


class Account {

    private String accountNo;
    private double balance;

    public Account() {

    }

    public Account(String accountNo, double balance) {
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

}