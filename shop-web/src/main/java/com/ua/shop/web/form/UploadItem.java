package com.ua.shop.web.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadItem {
    private String name;
    private CommonsMultipartFile file;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }
}
