package DesignPattern.decorator;

public abstract class Decorator extends Coffee{

    Coffee coffee;
    public Decorator(Coffee coffee) {
        this.coffee = coffee;
    }

    // 만약 fileInput Class라면 read하는 기능
    @Override
    public void brewing() {
        coffee.brewing();
    }

}
