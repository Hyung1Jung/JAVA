package java8.stream.inputstream;

import java.io.IOException;

public class SystemInTest {
    public static void main(String[] args) {

        System.out.print("입력 : ");

        try {
            int i = System.in.read();
            System.out.println(i);
            System.out.println((char)i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
