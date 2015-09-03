package com.alexios.shutterstockandroiddemowithlibs.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.GridView;

import com.alexios.shutterstockandroiddemowithlibs.R;
import com.alexios.shutterstockandroiddemowithlibs.adapters.ImagesAdapter;
import com.alexios.shutterstockandroiddemowithlibs.model.Image;
import com.alexios.shutterstockandroiddemowithlibs.retrofit.ShutterStock;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ImagesFragment extends Fragment implements Callback<List<Image>> {

	private GridView imagesList;
	private ProgressDialog dialog;
	private ViewStub errorMessage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View returnView = inflater.inflate(R.layout.images_fragment, container, false);

		String id = "";

		//get the requested url from the bundle
		Bundle args = getArguments();
		if (args != null && args.containsKey(CategoriesFragment.CATEGORY_ID))
			id = args.getString(CategoriesFragment.CATEGORY_ID);

		//display a progress dialog while the user is waiting
		dialog = ProgressDialog.show(getActivity(), "Downloading images", "Please wait...", true);

		imagesList = (GridView) returnView.findViewById(R.id.grid_view);
		errorMessage = ((ViewStub) returnView.findViewById(R.id.stub_import));

		ShutterStock.getImages(id, this);


		return returnView;
	}

	private void displayErrorMessage() {
		errorMessage.setVisibility(View.VISIBLE);
	}

	@Override
	public void success(List<Image> images, Response response) {
		dialog.dismiss();

		if (response != null) {

			//in case that we have images pass the list
			// to the ImagesAdapter and it'll display them
			if (images.size() > 0) {
				ImagesAdapter adapter = new ImagesAdapter(images);
				imagesList.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			} else {
				displayErrorMessage();
			}
		} else {
			displayErrorMessage();
		}
	}

	@Override
	public void failure(RetrofitError error) {
		dialog.dismiss();
		displayErrorMessage();
	}
}

