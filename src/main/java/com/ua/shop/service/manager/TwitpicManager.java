package com.ua.shop.service.manager;

import com.ua.shop.json.UploadFeed;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.*;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.ArraySerializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@Service
public class TwitpicManager {

    public static final String SHOW_URL = "http://twitpic.com/show/";

    private static final String MEDIA_PARAM = "media";
    private static final String USER_NAME_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";
//    public static final String CONSUMER_TOKEN_PARAM = "consumer_token";
//    public static final String CONSUMER_SECRET_PARAM = "consumer_secret";
//    public static final String OAUTH_TOKEN_PARAM = "oauth_token";
//    public static final String OAUTH_SECRET_PARAM = "oauth_secret";
//    public static final String KEY_PARAM = "key";


    public static final String MESSAGE_PARAM = "message";
    public static final String UPLOAD_URL = "http://api.twitpic.com/1/upload.json";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private ConfigManager configManager;

    public UploadFeed upload(File file, String message) throws IOException {
        return upload(file, null, null, message);
    }


    public UploadFeed upload(byte[] bytes, String fileName, String message) throws IOException {
        return upload(null, bytes, fileName, message);
    }


    private UploadFeed getUploadFeed(InputStream json) throws IOException {
        return OBJECT_MAPPER.readValue(json, UploadFeed.class);
    }

    private UploadFeed upload(File file, byte[] bytes, String fileName, String message) throws IOException {
        HttpClient client = new HttpClient();

        PostMethod method = new PostMethod(UPLOAD_URL);

        List<Part> parts = new LinkedList<Part>();
                parts.add(new StringPart("consumer_token", "uWW7gAaRkMesUJxJRKEGRA"));
        parts.add(new StringPart("consumer_secret", "ClvKJDo6SRkbocZjkGpJkyFGjgu9TJQSPt1TDWEvmI"));
        parts.add(new StringPart("oauth_token", "396214360-js4DjXkWh5yq9rHeoCAqBDhtCFuONh9EEPc2LhYl"));
        parts.add(new StringPart("oauth_secret", "BCuyaLEJkIthvXOKSYf73kA9LO9YSiM5IBCqDOP9sQ0"));
        parts.add(new StringPart("key", "2f9c72278501602fd2049f5fe29922dd"));
//        parts.add(new StringPart(USER_NAME_PARAM, configManager.getValue(ConfigManager.Key.TWITPIC_USERNAME.getKey())));
//        parts.add(new StringPart(PASSWORD_PARAM, configManager.getValue(ConfigManager.Key.TWITPIC_PASSWORD.getKey())));
        parts.add(new StringPart(MESSAGE_PARAM, message));

        if (file != null) {
            parts.add(new FilePart(MEDIA_PARAM, file));
        } else if (bytes != null) {
            parts.add(new FilePart(MEDIA_PARAM, new ByteArrayPartSource(fileName, bytes)));
        }
        RequestEntity entity = new MultipartRequestEntity(parts.toArray(new Part[parts.size()]), method.getParams());

        method.setRequestEntity(entity);

        UploadFeed uploadFeed = null;

        int status = client.executeMethod(method);

        if (status == HttpStatus.SC_OK) {

            uploadFeed = getUploadFeed(method.getResponseBodyAsStream());

            return uploadFeed;
        }


        return uploadFeed;
    }

}
