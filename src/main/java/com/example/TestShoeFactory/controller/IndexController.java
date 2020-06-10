package com.example.TestShoeFactory.controller;

import com.example.TestShoeFactory.builder.CombinationBuilder;
import com.example.TestShoeFactory.entity.Box;
import com.example.TestShoeFactory.entity.CombineFactory;
import com.example.TestShoeFactory.entity.OperInfo;
import com.example.TestShoeFactory.entity.Shoe;
import com.example.TestShoeFactory.exception.AppException;
import com.example.TestShoeFactory.service.Manufacture;
import com.example.TestShoeFactory.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {

    @Autowired
    private Manufacture cs;
    @Autowired
    private CombinationBuilder cb;
    @Value("${combine.max-produce}")
    int maxCombine;
    @Value("${shoe.max-produce}")
    int maxShoe;
    @Value("${box.max-produce}")
    int maxBox;

    @GetMapping("shoe")
    public Object getShoe(){
        List<Shoe> list_sf = cs.produceShoeData("2020-05-28", maxShoe);
        try {
            FileUtils.writeFile("shoe", list_sf);
        }catch (IOException e){
            throw new AppException("Write file failed!");
        }

        return new OperInfo(list_sf);
    }

    @GetMapping("box")
    public Object getBox(){
        List<Box> list_bf = cs.produceBoxData("2020-05-28", maxBox);
        try {
            FileUtils.writeFile("box", list_bf);
        }catch (IOException e){
            throw new AppException("Write file failed!");
        }
        return new OperInfo(list_bf);
    }

    @GetMapping("combine")
    public Object getCombine(){
        String shoeData, boxData;
        List<CombineFactory> list_cf = new ArrayList<>();
        try {
            shoeData = FileUtils.readFile("shoe");
            boxData = FileUtils.readFile("box");
            list_cf = cb.packageShoeBox(shoeData, boxData, maxCombine);
            FileUtils.writeFile("combine", list_cf);

        } catch (IOException e) {
            e.printStackTrace();
            throw new AppException("read File error!");
        }
        return new OperInfo(list_cf);
    }


    @GetMapping("excess")
    public Object getExcess(@RequestParam(value = "type")String type){
        String excess;
        try {
            excess = FileUtils.readFile(type);

        }catch (IOException e){
            throw new AppException("read File error!" + e.getMessage());
        }
        return new OperInfo(excess);
    }

}
