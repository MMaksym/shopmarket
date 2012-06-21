package com.ua.shop;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        List<String> l = new LinkedList<String>();

        l.add("max");
    }
}

class A{
    void m(A a){
        System.out.println("A");
    }
}

class B extends A{

     void m(B b) {
         System.out.println("B");

    }

    public static void main(String[] args) {
        A a = new B();
        a.m(new B());
    }
}
