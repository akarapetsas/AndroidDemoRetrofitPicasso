package com.alexios.shutterstockandroiddemowithlibs.retrofit;

import com.alexios.shutterstockandroiddemowithlibs.model.CategoriesResponse;
import com.alexios.shutterstockandroiddemowithlibs.model.ImagesResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


interface ShutterStockService {
    @GET("/images/categories")
    void getCategories(Callback<CategoriesResponse> cb);

    @GET("/images/search")
    void getImages(@Query("category") String date, Callback<ImagesResponse> cb);

}
