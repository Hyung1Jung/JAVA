package ex1.lambda;

import java.sql.SQLOutput;

public class TestStringConcat {

    public static void main(String[] args) {

        StringConImpl imp1 = new StringConImpl();
        imp1.makeString("hello", "world");

        StringConcat concat = (s, v) -> System.out.println(s + "," + v);
        concat.makeString("hello", "world");
    }
}
