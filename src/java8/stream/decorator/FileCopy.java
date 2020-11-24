package java8.stream.decorator;

import java.io.*;

public class FileCopy {
    public static void main(String[] args) {

        long milliseconds = 0;

        try (FileInputStream fis = new FileInputStream("a.zip");
             FileOutputStream fos = new FileOutputStream("copy.zip");
             BufferedInputStream bis = new BufferedInputStream(fis);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            milliseconds = System.currentTimeMillis(); // 현재시간

            int i;
            while((i = bis.read()) != -1) {
                bos.write(i);
            }

            milliseconds = System.currentTimeMillis() - milliseconds; // 얼마나 시간이 걸리는지 체크하기 위해

        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("시간 : " + milliseconds);
    }
}
