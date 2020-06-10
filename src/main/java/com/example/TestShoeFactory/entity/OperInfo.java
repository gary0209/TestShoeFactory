package com.example.TestShoeFactory.entity;

import lombok.Data;
import org.springframework.lang.NonNull;

import java.io.Serializable;

@Data
public class OperInfo implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public OperInfo(Object data){
        this();
        this.data=data;
    }

    public OperInfo() {
        this.code = 200;
        this.msg = "success";
    }

    public OperInfo(@NonNull int code, @NonNull String msg) {
        this.code = code;
        this.msg = msg;
    }
}
