package net.kuronekoserver.reactrolebot.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T extends Entity> {
    Gson gson = new Gson();
    ObjectMapper mapper = new ObjectMapper();
    final String apiUrl;
    final String identifier;

    public Repository(String apiUrl, String identifier) {
        this.apiUrl = apiUrl;
        this.identifier = identifier;
    }

    public abstract Type getArrayType();
    public abstract Type getType();

    public List<T> all() {
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(apiUrl + "/api/v1/"+identifier)
                    .build();

            Response response = client.newCall(request).execute();

            RequestFormat<ArrayList<T>> resObj = gson.fromJson(response.body().string(), getArrayType());

            response.body().close();

            return resObj.data;
        } catch (IOException | NullPointerException e) {
            return null;
        }
    }

    public T find(long id) {
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(apiUrl + "/api/v1/"+identifier+"/" + id)
                    .build();

            Response response = client.newCall(request).execute();

            if (!response.isSuccessful())
                return null;

            RequestFormat<T> resObj = gson.fromJson(response.body().string(), getType());

            response.body().close();

            return resObj.data;
        } catch (IOException | NullPointerException e) {
            return null;
        }
    }

    public void save(T item) {
        if ( find(item.id) == null ) {
            insert(item);
        } else {
            update(item);
        }
    }

    public void insert(T item) {
        try {
            OkHttpClient client = new OkHttpClient();

            final okhttp3.MediaType mediaTypeJson = okhttp3.MediaType.parse("application/json");

            final RequestBody requestBody = RequestBody.create(mediaTypeJson, gson.toJson(item));

            Request request = new Request.Builder()
                    .url(apiUrl + "/api/v1/"+identifier)
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            response.body().close();
        } catch (IOException | NullPointerException e) {
        }
    }

    public void update(T item) {
        try {
            OkHttpClient client = new OkHttpClient();

            final okhttp3.MediaType mediaTypeJson = okhttp3.MediaType.parse("application/json");

            final RequestBody requestBody = RequestBody.create(mediaTypeJson, gson.toJson(item));

            Request request = new Request.Builder()
                    .url(apiUrl + "/api/v1/"+identifier+"/" + item.id)
                    .put(requestBody)
                    .build();

            Response response = client.newCall(request).execute();

            response.body().close();
        } catch (IOException | NullPointerException e) {
        }
    }
}
