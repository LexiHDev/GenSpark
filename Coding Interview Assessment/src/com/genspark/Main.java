package com.genspark;

public class Main {

    public static String reverseAString(String reversable) {
        String ret = "";
        for (int i = reversable.length() - 1; i >= 0; i-- ) {
            //noinspection StringConcatenationInLoop
            ret = ret + reversable.charAt(i);
        }
        return ret;
    }
    
    public static void main(String[] args) {
        System.out.println(reverseAString("Hello Lexi!"));
    }
}
