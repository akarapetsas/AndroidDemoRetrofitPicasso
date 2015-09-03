package com.alexios.shutterstockandroiddemowithlibs;

import android.app.Application;
import android.content.Context;



public class SSApplication extends Application {

	public static SSApplication instance;

	public void onCreate() {
		super.onCreate();

		instance = this;
	}

	public static Context getContext() {
		return instance;
	}

}
