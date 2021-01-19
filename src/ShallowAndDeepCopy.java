import java.util.ArrayList;
import java.util.List;

public class ShallowAndDeepCopy {
    public static void main(String[] args) {

//        얕은 복사
//        List<Integer> first = new ArrayList<>();
//        first.add(0);
//        first.add(1);
//        first.add(2);
//
//        List<Integer> second  = first;
//
//        first.add(3);
//
//        System.out.println(second.toString());
//    }

//      깊은복사
        List<Integer> first = new ArrayList<>();
        first.add(0);
        first.add(1);
        first.add(2);
        List<Integer> second = new ArrayList<>();
        for (Integer i : first) {
            second.add(i);
        }
        first.add(3);
        System.out.println(second.toString());
    }
}
