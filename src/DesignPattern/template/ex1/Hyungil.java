package DesignPattern.template.ex1;

import java.sql.SQLOutput;

public abstract class Hyungil { // 필자 이름이 '형일' 이라서..

    public abstract void running();
    public abstract void stop();

    public void startHyungil() {
        System.out.println("형일로봇의 시동을 켠다.");
    }
    public void runOff() {
        System.out.println("시동을 끈다.");
    }

    public void washHyungil() {} // 구현의 확장을 할 수있는 메서드

    // 템플릿 메서드
   final public void run() {
        startHyungil();
        running();;
        stop();
        runOff();
        washHyungil();
    }
}

