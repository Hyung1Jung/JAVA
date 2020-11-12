package DesignPattern.singleton.ex1;

public class Skhu {

    private static Skhu instance = new Skhu();
    // 자기 자신의 타입을 가진 변수를 만든다. skhu가 내부에서 쓸 수 있는 생성자이기 떄문에

    private Skhu() {}
    //private라 외부에서 쓸 수가 없다.

    public static Skhu getInstance() {

        if(instance == null) {
            instance = new Skhu();
        }

        return instance;
    }


}
