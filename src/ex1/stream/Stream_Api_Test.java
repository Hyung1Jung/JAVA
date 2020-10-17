package ex1.stream;

import java.util.Arrays;

public class Stream_Api_Test {
    public static void main(String[] args) {
        int[] array = {2, 4, 6};

        boolean result = Arrays.stream(array)
                .allMatch(a -> a%2 == 0);
        System.out.println("2의 배수? " + result);

        result = Arrays.stream(array)
                .anyMatch(a -> a%3 == 0);
        System.out.println("3의 배수가 하나라도 있나? " + result);

        result = Arrays.stream(array)
                .noneMatch(a -> a%3 == 0);
        System.out.println("3의 배수가 없나? " + result);
    }
}
