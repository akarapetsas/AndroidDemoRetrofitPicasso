package com.alexios.shutterstockandroiddemowithlibs.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alexios.shutterstockandroiddemowithlibs.R;
import com.alexios.shutterstockandroiddemowithlibs.adapters.CategoriesAdapter;
import com.alexios.shutterstockandroiddemowithlibs.model.Categories;
import com.alexios.shutterstockandroiddemowithlibs.retrofit.ShutterStock;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class CategoriesFragment extends Fragment  implements Callback<List<Categories>> {

	public static final String CATEGORY_ID = "category_id";
	public static final String DOWNLOADING_CATEGORIES = "Downloading categories";
	public static final String PLEASE_WAIT = "Please wait...";

	private ListView categoriesList;
	private ProgressDialog dialog;
	private ViewStub errorMessage;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View returnView = inflater.inflate(R.layout.categories_fragment, container, false);

		categoriesList = (ListView) returnView.findViewById(R.id.list_view_form);
		//display a progress dialog while the user is waiting
		dialog = ProgressDialog.show(getActivity(), DOWNLOADING_CATEGORIES, PLEASE_WAIT, true);
		errorMessage = ((ViewStub) returnView.findViewById(R.id.stub_import));

		ShutterStock.getCategories(this);


		return returnView;
	}


	private void commitFragmentsTransaction(Integer id) {
		FragmentManager fm = getActivity().getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

		//add the url into a bundle because we have to
		//pass it on the offers fragment
		final Bundle bundle = new Bundle();
		bundle.putString(CATEGORY_ID, Integer.toString(id));


		ImagesFragment offersFragment = new ImagesFragment();
		offersFragment.setArguments(bundle);

		ft.replace(android.R.id.content, offersFragment);

		//We use the addToBackStack method of the FragmentTrasaction
		//in the backstack as we want to avoid the closing of
		//the app when the user will click on the back button
		//as the back button operates on the activity level
		ft.addToBackStack(ImagesFragment.class.getName());
		ft.commit();
	}


	@Override
	public void success(List<Categories> categories, Response response) {
		CategoriesAdapter mAdapter = new CategoriesAdapter(categories);
		categoriesList.setAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();
		dialog.dismiss();
		categoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				commitFragmentsTransaction(position);
			}
		});
	}

	@Override
	public void failure(RetrofitError error) {
		dialog.dismiss();
		errorMessage.setVisibility(View.VISIBLE);
	}
}
