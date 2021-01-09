package java_study.stream.decorator;

import java.io.*;
import java.net.Socket;

public class FileCopy {
    public static void main(String[] args) throws IOException {

        long milliseconds = 0;

        try (FileInputStream fis = new FileInputStream("a.zip");
             FileOutputStream fos = new FileOutputStream("copy.zip");
             BufferedInputStream bis = new BufferedInputStream(fis);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            milliseconds = System.currentTimeMillis(); // 현재시간

            int i;
            while ((i = bis.read()) != -1) {
                bos.write(i);
            }

            milliseconds = System.currentTimeMillis() - milliseconds; // 얼마나 시간이 걸리는지 체크하기 위해

        } catch (IOException e) {
            System.out.println(e);
        }

        Socket socket = new Socket();

        // 소켓에서 바이트단위로 읽는 것을 문자 단위로 읽을 수 있다. 읽을 때, 버퍼링 기능까지 가져올 수 있다.
        // 읽을 때, socket.getinputStream한 상태에서 Reader로 감싸서 문자로 읽게하고, 다시 버퍼링으로 해서 더 빠르게 읽을 수 있게
        // 이렇게 기능이 계속 추가되고 있는 것이 데코레이터 패턴, 이것을 상속으로 구현하면 엄청 복잡해진다.
        BufferedReader isr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        isr.readLine(); // BufferedReader에만 있는 메서드, 딱 한 줄씩만 읽는다

        System.out.println("시간 : " + milliseconds);
    }
}
