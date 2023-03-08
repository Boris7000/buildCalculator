package com.psuti.buildcalculator.util;

import java.util.HashMap;
import java.util.List;

public class ResponseMapUtil {

    public static HashMap<String, Object> wrapInError(String errorMessage, Integer errorCode){
        HashMap<String, Object> errorMap = new HashMap<>();
        errorMap.put("errorMessage", errorMessage);
        errorMap.put("errorCode", errorCode);
        return errorMap;
    }

    public static HashMap<String, Object> wrapInItem(Object object, String objectName){
        HashMap<String, Object> itemsMap = new HashMap<>();
        itemsMap.put(objectName, object);
        return itemsMap;
    }
}
