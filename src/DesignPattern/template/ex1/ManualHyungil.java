package DesignPattern.template.ex1;

public class ManualHyungil extends Hyungil{

    @Override
    public void running() {
        System.out.println("리모컨으로 형일로봇을 달리게 합니다.");
        System.out.println("리모컨으로 형일로봇의 방향을 조작합니다.");
    }

    @Override
    public void stop() {
        System.out.println("리모컨이 형일로봇을 멈춥니다");
    }
}
