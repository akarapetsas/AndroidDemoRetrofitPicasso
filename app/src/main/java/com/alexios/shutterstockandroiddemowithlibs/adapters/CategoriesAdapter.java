package com.alexios.shutterstockandroiddemowithlibs.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alexios.shutterstockandroiddemowithlibs.R;
import com.alexios.shutterstockandroiddemowithlibs.SSApplication;
import com.alexios.shutterstockandroiddemowithlibs.model.Categories;

import java.util.List;


public class CategoriesAdapter extends BaseAdapter {

	private List<Categories> categories;

	public CategoriesAdapter(List<Categories> categories) {
		this.categories = categories;
	}

	@Override
	public int getCount() {
		return categories.size();
	}

	@Override
	public Object getItem(int position) {
		return categories.get(position);
	}

	@Override
	public long getItemId(int position) {
		return categories.indexOf(getItem(position));
	}

	private class ViewHolder {
		TextView txtTitle;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		LayoutInflater mInflater = (LayoutInflater) SSApplication.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_text, null);
			holder = new ViewHolder();
			holder.txtTitle  = (TextView) convertView.findViewById(R.id.text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Categories rowItem = (Categories) getItem(position);

		holder.txtTitle.setText(rowItem.getName());

		return convertView;
	}

}
