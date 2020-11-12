package DesignPattern.decorator;

public class Latte extends Decorator{

   public Latte(Coffee coffee) {
        super(coffee);
    }

    // 제조 (상위클래스의 제조하는 법을 한 번 호출한후에, 커피를 만든다.
    public void brewing() {
       super.brewing();
       System.out.print(" Adding Milk");
    }
}
