package ex1.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArrayListTest {

    public static void main(String[] args) {
        List<String> sList = new ArrayList<>();
        sList.add("tomas");
        sList.add("edward");
        sList.add("jack");

        Stream<String> stream = sList.stream();
        stream.forEach(s -> System.out.print(s + " "));
        System.out.println();

        sList.stream().sorted().forEach((s -> System.out.print(s + " ")));
        System.out.println();

        sList.stream().map( s -> s.length()).forEach(n -> System.out.print(n + " "));


    }
}
