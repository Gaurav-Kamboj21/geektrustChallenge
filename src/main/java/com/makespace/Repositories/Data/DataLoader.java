package com.makespace.Repositories.Data;

import java.util.HashMap;
import java.util.Map;

import com.makespace.Exceptions.NoSuchDataFoundException;

public class DataLoader {
    private static final Map<String, Data> dataMap = new HashMap<>();

    // Register the data into the HashMap
    public void register(String dataName, Data data){
        dataMap.put(dataName,data);
    }

    // Get the registered Data
    private Data get(String dataName){
        return dataMap.get(dataName);
    }

    // Execute the registered Data
    public void executeData(String dataName) throws NoSuchDataFoundException {
        Data data = get(dataName);
        if(data == null){
            // Handle Exception
            throw new NoSuchDataFoundException("");
        }
        data.loadData();
    }
}
