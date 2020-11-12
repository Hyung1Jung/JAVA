package DesignPattern.singleton.ex1;

import java.util.Calendar;

public class SkhuTest {

    public static void main(String[] args) {

        Skhu skhu1 = Skhu.getInstance();

        Skhu skhu2 = Skhu.getInstance();

        System.out.println(skhu1);
        System.out.println(skhu2);

//        Calendar calendar = Calendar.getInstance() {
//        }
//        // 자바 유틸에 있는 class
//        // 싱글톤 패턴으로 구현되어 있다. new로 생성 불가
//        // .getInstance()로만 가져 올 수 있다. 가져 올 수 있는것이 단 하나이기 때문에


    }
}
