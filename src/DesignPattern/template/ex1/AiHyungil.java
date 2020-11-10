package DesignPattern.template.ex1;

import java.sql.SQLOutput;

public class AiHyungil extends Hyungil{
    //인공지능 정형일
    @Override
    public void running() {
        System.out.println("형일로봇이 스스로 달린다.");
        System.out.println("형일로봇이 방향을 바꿉니다.");
    }

    @Override
    public void stop() {
        System.out.println("스스로 멈춥니다");
    }

    @Override
    public void washHyungil() { // 기능 확장
        System.out.println("자동 샤워를 합니다.");
    }
}
