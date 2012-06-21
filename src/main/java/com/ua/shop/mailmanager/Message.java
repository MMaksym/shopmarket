package com.ua.shop.mailmanager;

import java.io.File;
import java.util.List;

public class Message {
    
    private String body;

    private List<File> attachments;

    public Message() {
    }

    public Message(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<File> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<File> attachments) {
        this.attachments = attachments;
    }
}
