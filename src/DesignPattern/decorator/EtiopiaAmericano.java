package DesignPattern.decorator;

public class EtiopiaAmericano extends Coffee{

    // 오리지널 컴포넌트
    @Override
    public void brewing() {
        System.out.print("EtiopiaAmericano");
    }
}
