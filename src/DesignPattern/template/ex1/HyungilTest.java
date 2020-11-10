package DesignPattern.template.ex1;

import java.sql.SQLOutput;

public class HyungilTest {

    public static void main(String[] args) {

        Hyungil aiHyungil = new AiHyungil();
        aiHyungil.run();

        System.out.println("======================");

        Hyungil mannualHyungil = new ManualHyungil();
        mannualHyungil.run();
    }
}
