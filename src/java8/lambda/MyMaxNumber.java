package java8.lambda;

@FunctionalInterface // 람다식을 위한 인터페이스 // 함수형 인터페이스는 메서드를 2개 이상 쓸 수 없다.
public interface MyMaxNumber {
    int getMaxNumber(int x, int y);
}
