package com.ua.shop.util;

import org.apache.commons.lang.StringUtils;

public class NameConverterUtils {

    private static final String UNDER_SLASH = "_";

    /*
    *   Remove leading and trailing whitespace
    *   Replace whitespaces, slashes, dots, commas, wildcards, ampersands to UNDER_SLASH
    *   Converts to lower case
    */
    public static String convert(String name) {
        if (name == null) {
            return name;
        }

        name = name.replaceAll("[\\?\\.\\*\\+\\\\/|,&#@\\^\\$%-]", "").trim().replaceAll(" ", UNDER_SLASH).toLowerCase();

        return name;
    }

}
