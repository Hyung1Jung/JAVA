package java8.string;

public class StringBuilderTest {

    public static void main(String[] args) {

        String str1 = new String("hyungil");
        String str2 = new String("cute");

        StringBuilder buffer = new StringBuilder(str1);
        System.out.println(System.identityHashCode(buffer));
        buffer.append("cute");
        System.out.println(System.identityHashCode(buffer));

        str1 = buffer.toString();
    }
}
