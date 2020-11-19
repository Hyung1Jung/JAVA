package java8.string;

public class StringBuliderTest2 {
    public static void main(String[] args)  {
        String s = "jeonghyungil";
        StringBuilder sb = new StringBuilder(s); // String -> StringBuilder

        System.out.println("처음 상태 : " + sb); //처음상태 : abcdefg
        System.out.println("문자열 String 변환 : " + sb.toString()); //String 변환하기
        System.out.println("문자열 추출 : " + sb.substring(2,4)); //문자열 추출하기
        System.out.println("문자열 추가 : " + sb.insert(2,"추가")); //문자열 추가하기
        System.out.println("문자열 삭제 : " + sb.delete(2,4)); //문자열 삭제하기
        System.out.println("문자열 연결 : " + sb.append("zzzzzzz")); //문자열 붙이기
        System.out.println("문자열의 길이 : " + sb.length()); //문자열의 길이구하기
        System.out.println("용량의 크기 : " + sb.capacity()); //용량의 크기 구하기
        System.out.println("문자열 역순 변경 : " + sb.reverse()); //문자열 뒤집기
        System.out.println("마지막 상태 : " + sb); //마지막상태 : kjihgfedcba
    }
}
