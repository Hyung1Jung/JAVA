package thread;

import java.util.ArrayList;

class AnyangLibrary {
    // 책들이 있다.
    public ArrayList<String> books = new ArrayList<String>();

    public AnyangLibrary() {
        books.add("자바의 정석 1");
        books.add("자바의 정석 2");
        books.add("자바의 정석 3");

    }

    public synchronized String lendBook() throws InterruptedException {

        Thread t = Thread.currentThread();
        while(books.size() == 0) {                              // 못빌린 애들은 다시 wait() 상태로 빠뜨린다.
            System.out.println(t.getName() + " waiting start"); // 어떤 thread가 기다리는지
            wait();  // Thread.currentThread();를 기다리게 한다
            System.out.println(t.getName() + " waiting end"); // wait()이 끝나고 어느게 기다렸다가 끝났는지
        }
        String title = books.remove(0);
        System.out.println(t.getName() + ": " + title + " lend");
        return title;
    }

    public synchronized void returnBook(String title) {
        Thread t = Thread.currentThread();
        books.add(title);
        notify(); // 책이 왔다.
        System.out.println(t.getName() + ": " + title + " return");
    }
}

class Student extends Thread {

    public void run() {
        try {
            String title = LibraryMain.library.lendBook();
            if(title == null) {
                return;
            }
            sleep(5000); // 빌려오면 5초동안 책을 읽는다.
            LibraryMain.library.returnBook(title); // 반납을 한다.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class LibraryMain {
    public static AnyangLibrary library = new AnyangLibrary();
    public static void main(String[] args) {

        Student std1 = new Student();
        Student std2 = new Student();
        Student std3 = new Student();
        Student std4 = new Student();
        Student std5 = new Student();
        Student std6 = new Student();

        std1.start();
        std2.start();
        std3.start();
        std4.start();
        std5.start();
        std6.start();

    }
}
