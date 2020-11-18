package thread;

class Bank {
    private int money = 10000;

    public void saveMoney(int save) {
        synchronized (this) {
            int m = this.getMoney();

            try {
                Thread.sleep(3000);      // 저축이 되는데 3초의 시간이 걸린다.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            setMoney(m + save);
        }
    }

    public synchronized void minusMoney(int minus) {   // 돈이 줄어드는데 0.2초의 시간이 걸린다.
        int m = this.getMoney();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setMoney(m - minus);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}

// 한 사람은 적금을 하고 한사람은 출금
class Jung extends Thread {
    public void run() {
        System.out.println("start save");
        SyncTest.myBank.saveMoney(3000);
        System.out.println("save money: " + SyncTest.myBank.getMoney());
    }
}

class JungWife extends Thread{
    public void run() {
        System.out.println("start minus");
        SyncTest.myBank.minusMoney(1000);
        System.out.println("minus money: " + SyncTest.myBank.getMoney());
    }
}


public class SyncTest {
    // 한 사람은 적금을 하고 한사람은 출금
    public static Bank myBank = new Bank();

    public static void main(String[] args) throws InterruptedException{
        // Thread 호출
        Jung j = new Jung();
        j.start();

        Thread.sleep(200);
        JungWife jw = new JungWife();
        jw.start();
    }
}
