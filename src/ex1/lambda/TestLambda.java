package ex1.lambda;

interface PrintString{
    void showString(String str);
}

public class TestLambda {

    public static void main(String[] args) {
        PrintString lambdaStr = s -> System.out.println(s);
        lambdaStr.showString("test");

        showMyString(lambdaStr); // 변수가 매개변수로 넝어감

        PrintString test = returnString();
        test.showString("Test3");
    }

    public static void showMyString(PrintString p) {
        p.showString("Test2");
    }

    public  static PrintString returnString() {
        return  s-> System.out.println(s + "!!!");
    }
}
