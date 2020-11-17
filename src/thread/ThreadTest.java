package thread;

import java.util.concurrent.TransferQueue;

class MyThread implements Runnable{

    public void run() { // 쓰레드가 start되면 run() 실행이 된다.
        int i;

        for (i = 0; i <= 200; i++) {
            System.out.print(i + "\t");

            try {
                Thread.sleep(100);  // Thread 클래스의 static method
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadTest {

    public static void main(String[] args) {

        System.out.println("start");
//        MyThread th1 = new MyThread();
//        MyThread th2 = new MyThread();
//
//        th1.start();
//        th2.start();

//        MyThread runner1 = new MyThread();
//        Thread th1 = new Thread(runner1);
//        th1.start();
//
//        MyThread runner2 = new MyThread();
//        Thread th2 = new Thread(runner2);
//        th2.start();

        Thread t = Thread.currentThread(); // static method, 현재 main이 돌고 있는 쓰레드를 가지고 올 수 있다.
        System.out.println(t);

        System.out.println("end");
    }
}

/*
이 클래스에서 쓰레드는 총 3개가 돌아간다. main, th1, th2
main이 하는일 -> start찍고 쓰레드 2개 만들어서 뛰어놓고 end찍는게 끝
 */
