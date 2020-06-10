package com.example.TestShoeFactory.entity;

import lombok.Data;


@Data
public class Shoe implements Factory {

    @Override
    public String getFactoryName() {
        return "SHOE";
    }

    public String ids;

    public String date;

}
