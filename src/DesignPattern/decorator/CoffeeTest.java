package DesignPattern.decorator;

public class CoffeeTest {

    public static void main(String[] args) {
        Coffee americano = new KenyaAmericano();
        americano.brewing();
        System.out.println();

        Coffee KenyaLatte  = new Latte(new KenyaAmericano()); // 장식자
        KenyaLatte.brewing();
        System.out.println();

        // 장식자가 생성자에서 또다른 데코레이터를 받을 수 있고, 일반 컴포넌트를 받을 수도 있고
        // 메인 안에있는건 일반 컴포넌트여야 한다. 그래야 읽고 쓸 수 있으니깐
        Coffee KenyaMocha = new Mocha(new Latte((new KenyaAmericano())));
        KenyaMocha.brewing();
        System.out.println();

        Coffee etiopiaMocha = new Mocha(new Latte(new EtiopiaAmericano()));
        etiopiaMocha.brewing();

    }
}
