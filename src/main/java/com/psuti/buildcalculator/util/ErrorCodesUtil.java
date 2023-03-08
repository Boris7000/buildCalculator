package com.psuti.buildcalculator.util;

import java.util.HashMap;
import java.util.Locale;

public class ErrorCodesUtil {

    public static HashMap<String, Object> doesNotExistByIdError(Integer id, String type){
        return ResponseMapUtil.wrapInError(String.format(Locale.getDefault(),
                "%s с id: %s не существует", type, id), 501);
    }

}
