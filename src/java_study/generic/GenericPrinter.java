package java_study.generic;

public class GenericPrinter<T extends Meterial> {

    // Printer을 쓸 때, 재료를 정하자, T type에 적용이 되서 클래스가 생성 될 때, T타입에 모두 대입이 된다.
    private T material;


    public T getMaterial() {
        return material;
    }

    public void setMaterial(T material) {
        this.material = material;
    }

    public String toString() {
        return material.toString();
    }

    public void printing() {
        material.doPrinting();
    }

}
