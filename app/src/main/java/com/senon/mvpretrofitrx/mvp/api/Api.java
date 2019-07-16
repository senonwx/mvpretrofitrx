package com.senon.mvpretrofitrx.mvp.api;

import com.senon.mvpretrofitrx.mvp.base.BaseApi;

/**
 *
 */

public class Api {

//    private String baseUrl = "http://www.kuaidi100.com/";
    private String baseUrl = "https://www.wanandroid.com/";


    private volatile static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (Api.class) {
                if (apiService == null) {
                    new Api();
                }
            }
        }
        return apiService;
    }

    private Api() {
        BaseApi baseApi = new BaseApi();
        apiService = baseApi.getRetrofit(baseUrl).create(ApiService.class);
    }
}
