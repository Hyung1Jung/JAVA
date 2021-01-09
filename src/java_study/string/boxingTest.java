package java_study.string;

public class boxingTest {
    public static void main(String[] args) {
        int n = 10;
        // Integer i = Integer.valueOf(n);  // 이렇게 할 필요 없이
        Integer i = n;                      // 이렇게만 써주면 된다. 자동박싱

        int n2 = i; // i라는 객체가 가지고 있는 정수값을 간편하게 뽑아낼 수 있다. 언박싱
    }
}
