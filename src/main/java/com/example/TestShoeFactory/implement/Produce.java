package com.example.TestShoeFactory.implement;

import com.example.TestShoeFactory.entity.Box;
import com.example.TestShoeFactory.entity.Shoe;
import com.example.TestShoeFactory.service.Manufacture;
import com.example.TestShoeFactory.utils.DateUtils;
import com.example.TestShoeFactory.utils.KeyUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Produce implements Manufacture {

    @Override
    public List<Shoe> produceShoeData(String date, int maxShoe) {
        List<Shoe> list = new ArrayList<>();
        for (int i = 0; i < maxShoe; i++){
            String pdate = DateUtils.randBetweenMonth(date);
            Shoe sf = new Shoe();
            sf.ids = KeyUtils.getKeyByDate(pdate, sf.getFactoryName());
            sf.date = pdate;
            list.add(sf);
        }
        return list;
    }

    @Override
    public List<Box> produceBoxData(String date, int maxBox) {
        List<Box> list = new ArrayList<>();
        for (int i = 0; i < maxBox; i++){
            String pdate = DateUtils.randBetweenMonth(date);
            Box bf = new Box();
            bf.ids = KeyUtils.getKeyByDate(pdate, bf.getFactoryName());
            bf.date = pdate;
            list.add(bf);
        }
        return list;
    }


}
