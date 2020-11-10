package java8.stream;

import java.util.Arrays;

public class Stream_Api_Test {
    public static void main(String[] args) {
        int[] array = {2, 4, 6};

        boolean result = Arrays.stream(array)
                .allMatch(a -> a * 2 < 20);
        System.out.println("2의 배수들이 20보다 작나? " + result);

        result = Arrays.stream(array)
                .anyMatch(a -> a%3 == 0);
        System.out.println("3의 배수가 하나라도 있나? " + result);

        result = Arrays.stream(array)
                .noneMatch(a -> a%6 == 0);
        System.out.println("6의 배수가 없나? " + result);
    }
}
