package com.alexios.shutterstockandroiddemowithlibs.retrofit;

import android.util.Base64;

import com.alexios.shutterstockandroiddemowithlibs.model.Categories;
import com.alexios.shutterstockandroiddemowithlibs.model.Image;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;


public class ShutterStock {
    private static final RestAdapter ADAPTER = new RestAdapter.Builder()
            .setEndpoint("https://api.shutterstock.com/v2")
            .setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    String authInfo = "f58688d39233e8575717:a2384929485096a98090be8d0811280e9c4240b4";
                    String auth = "Basic "+ Base64.encodeToString(authInfo.getBytes(), Base64.NO_WRAP);
                    request.addHeader("Authorization", auth);
                }
            })
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

    private static final ShutterStockService SERVICE = ADAPTER.create(ShutterStockService.class);

    public static void getCategories(Callback<List<Categories>> cb){
        SERVICE.getCategories( new CategoriesCallback(cb));
    }

    public static void getImages(String id, Callback<List<Image>> cb){
        SERVICE.getImages(id, new ImageCallback(cb));
    }

}
