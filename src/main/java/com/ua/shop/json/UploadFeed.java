package com.ua.shop.json;

import java.util.Date;

public class UploadFeed {
    private String id;
    private String text;
    private String url;
    private int width;
    private int height;
    private long size;
    private String type;
    private String timestamp;
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UploadFeed{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", user=" + user +
                '}';
    }

    public static class User {
        private String id;
        private String screen_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getScreen_name() {
            return screen_name;
        }

        public void setScreen_name(String screen_name) {
            this.screen_name = screen_name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id='" + id + '\'' +
                    ", screen_name='" + screen_name + '\'' +
                    '}';
        }
    }
}
