/**
 * Fyber Android SDK
 * <p/>
 * Copyright (c) 2015 Fyber. All rights reserved.
 */

package com.alexios.shutterstockandroiddemowithlibs.retrofit;

import com.alexios.shutterstockandroiddemowithlibs.model.Image;
import com.alexios.shutterstockandroiddemowithlibs.model.ImagesResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ImageCallback implements Callback<ImagesResponse> {
	Callback<List<Image>> cb;

	ImageCallback(Callback<List<Image>> cb) {
		this.cb = cb;
	}

	@Override
	public void success(ImagesResponse imagesResponse, Response response) {
		cb.success(imagesResponse.getData(), response);

	}

	@Override
	public void failure(RetrofitError error) {
		cb.failure(error);
	}
}
