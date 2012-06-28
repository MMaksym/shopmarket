package com.ua.shop.util;

public class FileNameUtils {

    public static String getFileName(String fileName) {
        if (fileName == null) {
            return fileName;
        }

        int lastSpace = fileName.lastIndexOf(".");

        return lastSpace != -1 ? fileName.substring(0, lastSpace) : fileName;
    }

    public static String getExtension(String fileName) {

        if (fileName == null) {
            return fileName;
        }

        int lastSpace = fileName.lastIndexOf(".");
        String extension = "";

        if (lastSpace != -1) {
            extension  = fileName.substring(lastSpace + 1);
        }

        return extension;
    }
    
}
