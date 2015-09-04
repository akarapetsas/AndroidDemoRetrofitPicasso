package com.alexios.shutterstockandroiddemowithlibs.retrofit;

import com.alexios.shutterstockandroiddemowithlibs.model.Categories;
import com.alexios.shutterstockandroiddemowithlibs.model.CategoriesResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CategoriesCallback implements Callback<CategoriesResponse> {
	Callback<List<Categories>> cb;
	CategoriesCallback(Callback<List<Categories>> cb){
		this.cb = cb;
	}

	@Override
	public void success(CategoriesResponse imagesResponse, Response response) {
		cb.success(imagesResponse.getData(), response);

	}

	@Override
	public void failure(RetrofitError error) {
		cb.failure(error);
	}
}
