package com.ua.shop.util;

import static org.junit.Assert.*;
import org.junit.Test;

public class NameConverterUtilsTest {

    private String[][] TEST_DATA = {
            {"max", " .*+/\\max? "},
            {"max", " .*+/\\, ma&-#$^%x? "},
            {"big_technique", " Big Technique ++++++++++++++++"}

    };

    @Test
    public void nameConvertTest() {

        for (String[] line : TEST_DATA) {
            String expected = line[0];
            String name = line[1];

            String actual = NameConverterUtils.convert(name);

            assertEquals(expected, actual);
        }
    }

}
