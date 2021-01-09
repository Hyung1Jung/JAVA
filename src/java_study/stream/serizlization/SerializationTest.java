package java_study.stream.serizlization;

import java.io.*;

class Person implements Externalizable {
    String name;
    transient String job;

    public Person(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String toString() {
        return name + "," + job;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}

public class SerializationTest {
    public static void main(String[] args) {

        Person personJung = new Person("정형일", "개발자");
        Person personLee = new Person("이승진", "교수");

        try (FileOutputStream fos = new FileOutputStream("serial.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(personJung);
            oos.writeObject(personLee);
        } catch (IOException e) {
            System.out.println(e);
        }

        try (FileInputStream fis = new FileInputStream("serial.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Person p1 = (Person) ois.readObject();  // writeObject로 썻으니까 readObject로 읽으면 된다. 반환값이 오브젝트로 되고, 안전하게 Person으로 다운캐스팅 해준다.
            Person p2 = (Person) ois.readObject();

            System.out.println(p1);
            System.out.println(p2);

        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
