package com.alexios.shutterstockandroiddemowithlibs.adapters;

import android.app.Activity;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexios.shutterstockandroiddemowithlibs.R;
import com.alexios.shutterstockandroiddemowithlibs.SSApplication;
import com.alexios.shutterstockandroiddemowithlibs.model.Image;
import com.alexios.shutterstockandroiddemowithlibs.utils.PaletteGeneratorTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImagesAdapter extends BaseAdapter {

	private List<Image> offers;

	public ImagesAdapter(List<Image> offers) {
		this.offers = offers;
	}

	@Override
	public int getCount() {
		return offers.size();
	}

	@Override
	public Object getItem(int position) {
		return offers.get(position);
	}

	@Override
	public long getItemId(int position) {
		return offers.indexOf(getItem(position));
	}

	private class ViewHolder {
		ImageView imageView;
		TextView txtTeaser;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;

		LayoutInflater mInflater = (LayoutInflater) SSApplication.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.row_item, null);
			holder = new ViewHolder();
			holder.txtTeaser = (TextView) convertView.findViewById(R.id.title);
			holder.imageView = (ImageView) convertView.findViewById(R.id.icon);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Image rowItem = (Image) getItem(position);

		holder.txtTeaser.setText(rowItem.getDescription());

		String url = rowItem.getAssets().getPreview().getUrl();

		final View finalConvertView = convertView;
		Picasso.with(SSApplication.getContext()).load(url).placeholder(R.drawable.no_image).error(R.drawable.error_image)
				.transform(new PaletteGeneratorTransformation(3))
				.into(holder.imageView, new PaletteGeneratorTransformation.Callback(holder.imageView) {
					@Override
					public void onPalette(final Palette palette) {
						Palette.Swatch vibrant = palette.getVibrantSwatch();
						// If we have a vibrant color
						// update the title TextView
						if (vibrant != null) {
							finalConvertView.setBackgroundColor(vibrant.getRgb());
							holder.txtTeaser.setTextColor(vibrant.getTitleTextColor());
						}
					}
				});



		return convertView;
	}

}
