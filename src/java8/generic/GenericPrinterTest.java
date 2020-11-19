package java8.generic;

public class GenericPrinterTest {

    public static void main(String[] args) {

        GenericPrinter<Powder>  powderGenericPrinter = new GenericPrinter<>();
        Powder powder = new Powder(); // 파우더에 관한 재료를 넣어준다.
        // GenericPrinter 클래스에서 제네릭 타입만 생성했다고 해서 되는게 아니다. 생성을 해서 set으로 넣어줘야 한다.
        powderGenericPrinter.setMaterial(powder);
        System.out.println(powderGenericPrinter);

        GenericPrinter<Plastic>  plasticGenericPrinter = new GenericPrinter<>();
        Plastic plastic = new Plastic();
        plasticGenericPrinter.setMaterial(plastic);
        System.out.println(plasticGenericPrinter);

       // GenericPrinter<Water> waterGenericPrinter = new GenericPrinter<>();

        powderGenericPrinter.printing();
        plasticGenericPrinter.printing();

    }
}
