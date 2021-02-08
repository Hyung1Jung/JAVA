package DesignPattern.adapterPattern;

import java.util.Objects;

public class ClientWithNoAdapter {
    public static void main(String[] args) {
        ServiceA sa1 = new ServiceA();
        ServiceB sb2 = new ServiceB();

        sa1.runServiceA();
        sb2.runServiceB();
    }
}
