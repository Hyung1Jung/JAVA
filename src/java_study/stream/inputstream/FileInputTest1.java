package java_study.stream.inputstream;

import java.io.FileInputStream;
import java.io.IOException;


public class FileInputTest1 {
    public static void main(String[] args) {

        FileInputStream fis = null;

        try {
            fis = new FileInputStream("input.txt");
            int i;

//            // 한 바이트씩 3번 읽어보자.
//            i = fis.read();
//            System.out.print((char) i);
//            // read()에 빨간줄로 에러가 뜰때 try~catch로 또 감싸주어도 되지만, 아래에 FileNotFoundException이 있기 때문에
//            // 그냥 IOException로 바꿔주면 된다. FileNotFoundException은 IOException에 속하기 때문
//            i = fis.read();
//            System.out.print((char) i);
//            i = fis.read();
//            System.out.print((char) i);

            // 끝까지 읽어보자자, 파일에 끝에 도달하면 -1이 반한됨, -1이 아닐때까지 계속
            while ((i = fis.read()) != -1) {
                System.out.print((char) i);
            }

        } catch (IOException e) {
            e.printStackTrace(); // 어디에서 에러가 발생하였는지 추적할 수 있게
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("end");
    }
//        * try with resources *
//        try (FileInputStream fis = new FileInputStream("input.txt")) {
//            int i;
//            while ((i = fis.read()) != -1) {
//                System.out.println((char) i);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("end");
//    }
}
