package com.bt.sample.core.models;

import ch.qos.logback.core.net.SyslogOutputStream;

public class DebugTheJavaFile {

    public static int i;

    public static void main(String[] args) {
        int i =20;
        System.out.println("hello");
        System.out.println(i);
        DebugTheJavaFile obj = new DebugTheJavaFile();
        obj.m1(i);
    }

    public void m1(int i){
        System.out.println("Heloo "+i);
    }
}