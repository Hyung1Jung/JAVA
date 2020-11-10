//package java_performance_tuning;
//
//public class For {
//    public static void example(List<Integer> numbers) {
//        for (int i = 0; i < numbers.size(); i++) {
//        ....
//        }
//    }
//
//    public static void example(List<Integer> numbers) {
//        int numbersSize = numbers.size();
//        for (int i = 0; i < numbersSize; i++) {
//        ....
//        }
//    }
//
//    public static void example(List<Integer> numbers) {
//        for (int i = 0, numberSize = numbers.size(); i < numberSize; i++) {
//        ....
//        }
//    }
//
//    public void example(List<Integer> numbers) {
//        for (int number : numbers) {
//        ...
//        }
//    }
//
//    public static void example(List<String> words) {
//        Iterator value = words.iterator();
//
//        while(value.hasNext()) {
//            String word = (String)value.next();
//        ...
//        }
//    }
//
//    public void example(String[] words) {
//        for (String word : words) {
//        ...
//        }
//    }
//
//    public void example(String[] words) {
//        String[] value1 = words;
//        int value2 = words.length;
//
//        for(int value3 = 0; value3 < value2; ++value3) {
//            String word = value1[value3];
//            System.out.println(word);
//        }
//    }
//}