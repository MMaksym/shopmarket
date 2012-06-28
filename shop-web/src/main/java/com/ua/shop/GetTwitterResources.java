package com.ua.shop;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

public class GetTwitterResources {

    public static void main(String[] args) {

        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod("https://api.twitter.com/oauth/authorize");

        method.setQueryString(new NameValuePair[]{new NameValuePair("oauth_token", "396214360-js4DjXkWh5yq9rHeoCAqBDhtCFuONh9EEPc2LhYl")});
        try {
            int i = client.executeMethod(method);

            System.out.println(method.getResponseBodyAsString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
