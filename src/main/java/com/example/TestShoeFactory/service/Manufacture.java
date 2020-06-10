package com.example.TestShoeFactory.service;

import com.example.TestShoeFactory.entity.Box;
import com.example.TestShoeFactory.entity.Shoe;

import java.util.List;

public interface Manufacture {

    List<Shoe> produceShoeData(String date, int limit);

    List<Box> produceBoxData(String date, int limit);

}
