package com.ua.shop.web.form;

/**
 * Author: Lotus
 * Date: 17.03.12
 */
public class ImageUpload {
    
    private String status = "fail";
    private String url;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
