package com.example.TestShoeFactory.builder;

import com.example.TestShoeFactory.entity.Box;
import com.example.TestShoeFactory.entity.Combine;
import com.example.TestShoeFactory.entity.CombineFactory;
import com.example.TestShoeFactory.entity.Shoe;
import com.example.TestShoeFactory.exception.AppException;
import com.example.TestShoeFactory.utils.FileUtils;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class CombinationBuilder {

    public List<CombineFactory> packageShoeBox(String shoeData, String boxData, int limit){
        List<Shoe> shoeList = new ArrayList<>();
        List<Box> boxList = new ArrayList<>();
        List<CombineFactory> combList = new ArrayList<>();
        try {
            JSONArray sjson = new JSONArray(shoeData);
            JSONArray bjson = new JSONArray(boxData);
            for (int i = 0; i < bjson.length(); i++) {
                Box bf = new Box();
                JSONObject boxobj = bjson.getJSONObject(i);
                bf.ids = boxobj.getString("ids");
                bf.date = boxobj.getString("date");
                boxList.add(bf);
            }

            for (int j = 0; j < sjson.length(); j++){
                Shoe sf = new Shoe();
                JSONObject sobj = sjson.getJSONObject(j);
                sf.ids = sobj.getString("ids");
                sf.date = sobj.getString("date");
                shoeList.add(sf);
            }

            Iterator<Box> ibf = boxList.iterator();
            Iterator<Shoe> isf = shoeList.iterator();
            int size = 0;
            while (isf.hasNext()){
                Shoe sf = isf.next();
                if(sf != null){
                    Box bf = ibf.next();
                    Combine cf = new Combine();
                    String cids = bf.ids.length() == 8 ? cf.getCIds(bf.ids.substring(2), sf.ids.substring(2)) :  cf.getCIds(bf.ids.substring(1), sf.ids.substring(1));
                    cf.combIds = cids;
                    cf.date = sf.date;
                    combList.add(cf);
                    ibf.remove();
                    isf.remove();
                    size++;
                    if(size == limit){
                        System.out.println("full loading");
                        break;
                    }
                }
            }

            if(boxList.size() > 0){
                FileUtils.writeFile("excessBox", boxList);
            }
            if(shoeList.size() > 0){
                FileUtils.writeFile("excessShoe", shoeList);
            }


        } catch (JSONException e) {
//            e.printStackTrace();
            throw new AppException("json parser error, stacktrace:"+ e.getStackTrace());
        }catch (IOException e){
            throw new AppException("write file failed! stacktrace:" + e.getStackTrace());
        }

        return combList;
    }
}
