package java_study.optional;

import java.util.Optional;

public class Test1 {
    public static void main(String[] args) {
        Optional<String> cls1 = Optional.empty(); //초기화 목적
        String str = null;
        Optional<String> cls2 = Optional.ofNullable(str); // null값도 허용한다 , 초기화 목적

        //isPresent() -> null값이 아닌 어떤 값을 갖고 있는지, true or false
        System.out.println("cls1 is Present: " + cls1.isPresent());
        System.out.println("cls2 is Present: " + cls2.isPresent());

        // orElse 값이 있는지 없는지, null 값을 가지고 있으면 orElse,
        // null값을 가지고 있으면 "null" 문자로 바꾼다. 리턴값이 null
        System.out.println("c1s1 orElse: " + cls1.orElse("null"));

        System.out.println("cls2 orElseGet " +
                cls2.orElseGet(() -> {
                    int i = 10;
                    i++;
                    return i + "";
                })
        );
        cls1.ifPresent(v -> {
            int i = 10;
            i++;
        });
        // 이런 옵셔널이 거추장스러워 벗어던지고 싶으면?
        System.out.println("cls2 get: " + cls2.get());
    }
}

