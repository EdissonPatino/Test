package com.project.test.util;

import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.*;

public class Utils {
    public static boolean validarSoloTexto(String value){
        return !isBlank(value) && isAlphaSpace(value);
    }

    public static boolean validarSoloNumeros(String value){
        return !isBlank(value) && isNumeric(value);
    }

    public static boolean validarAlfanumerico(String value){
        return !isBlank(value) && isAlphanumeric(value);
    }

    public static boolean validarEmail(String value){
        return !isBlank(value) && Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
                .matcher(value)
                .matches();
    }
}
