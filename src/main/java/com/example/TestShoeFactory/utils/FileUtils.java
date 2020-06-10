package com.example.TestShoeFactory.utils;

import com.example.TestShoeFactory.exception.AppException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.*;

public class FileUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void writeFile(String fileName, Object content) throws IOException {

        FileWriter fw = new FileWriter(fileName+".json");
        String json = objectMapper.writeValueAsString(content);
        fw.write(json);
        fw.flush();
        fw.close();
    }

    public static String readFile(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName+".json");
        BufferedReader br = new BufferedReader(fr);
        String s = null, ws = null;
        while ((s = br.readLine()) != null) {
            try {
                JSONArray dataJson = new JSONArray(s);
                ws = dataJson.toString();
            } catch (JSONException e) {
                throw new AppException("read file format error");
            }
        }
        fr.close();

        return ws;
    }
}
