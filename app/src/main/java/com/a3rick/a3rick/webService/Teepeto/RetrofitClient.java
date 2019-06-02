package com.a3rick.a3rick.webService.Teepeto;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL ="http://79.175.138.89:8088/TeepeTo/";

    public static String FILE_URL="http://79.175.138.77:7091/file/getfile?FileType=%s&fileid=%s";

    public RetrofitClient() {
    }

    public static Retrofit getRetroClient() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static FileApi getApiService() {
        return getRetroClient().create(FileApi.class);
    }

}
