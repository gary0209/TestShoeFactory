package com.example.TestShoeFactory.entity;

public class Combine extends CombineFactory {

    public String getCIds(String c1, String c2){
        return c1.concat("-").concat(c2);
    }
}
