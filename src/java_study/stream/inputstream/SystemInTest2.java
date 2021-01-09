package java_study.stream.inputstream;

import java.io.IOException;
import java.io.InputStreamReader;


public class SystemInTest2 {
    public static void main(String[] args) {

        System.out.print("입력 후 '끝' 이라고 쓰세요 : ");

        try {
            int i;
            InputStreamReader isr = new InputStreamReader(System.in); // 읽어준 애를 문자로 반환해준다. 항상 다른 스트림을 파라미터로 받아야한다.
            while((i = isr.read()) != '끝') {
                System.out.print((char)i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
