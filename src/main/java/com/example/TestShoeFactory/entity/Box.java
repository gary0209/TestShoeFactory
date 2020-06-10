package com.example.TestShoeFactory.entity;

import lombok.Data;

@Data
public class Box implements Factory {
    @Override
    public String getFactoryName() {
        return "BBOX";
    }

    public String ids;

    public String date;

}
