package com.aum.apicaller.helper;


import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class HttpHelper {

    public enum Verb {
        GET,POST,PUT,DELETE
    }

    private static String apiToken;
    private static HttpHelper instance;

    private static String url;


    private static Verb method;

    private HttpHelper() {
        apiToken = "";
        url = "";
        method = Verb.GET;
    }


    public static HttpHelper setApiToken(String token) {
        apiToken = token;
        return getInstance();
    }
    public static HttpHelper setMethod(Verb method) {
        HttpHelper.method = method;
        return getInstance();
    }

    public static HttpHelper getInstance() {
        if (instance == null) {
            instance = new HttpHelper();
        }
        return instance;
    }

    public static HttpHelper setUrl(String url) {
        HttpHelper.url = url;
        return getInstance();
    }

    public static ResponseMessage getResponseMessage(String body)  {

        ResponseMessage message = new ResponseMessage();

        if (url==null){
            message.setStatusCode(0);
            return message;
        }
        Request.Builder requestBuilder = new Request.Builder();

        try {
            requestBuilder = requestBuilder
                    .url(url);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(method != Verb.GET) {
            final MediaType JSON
                    = MediaType.parse("application/json; charset=utf-8");
            RequestBody requestBody = RequestBody.create(JSON, body);
            requestBuilder.method(method.name(), requestBody);
        }


        if (apiToken != "")
            requestBuilder.addHeader("Authorization", apiToken);

        OkHttpClient client = new OkHttpClient();

        Request request = requestBuilder.build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response.isSuccessful()) {
            try {
                message.setStatusCode(response.code());
                message.setMessage(response.body().string());
            } catch (IOException e) {}
        } else {
            try {
                message.setStatusCode(response.code());
                message.setMessage(response.body().string());
            } catch (IOException e) {}
        }

        return message;
    }



}