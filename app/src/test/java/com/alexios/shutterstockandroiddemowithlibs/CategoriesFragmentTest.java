package com.alexios.shutterstockandroiddemowithlibs;

import android.support.v4.app.Fragment;

import com.alexios.shutterstockandroiddemowithlibs.activities.MainActivity;
import com.alexios.shutterstockandroiddemowithlibs.fragments.CategoriesFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(com.alexios.shutterstockandroiddemowithlibs.RobolectricDataBindingTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = 21)
public class CategoriesFragmentTest {

	private MainActivity activity;

	@Before
	public void setUp() throws Exception {
		activity = Robolectric.buildActivity(MainActivity.class).create().start().resume().visible().get();
	}

	@Test
	public void checkActivityNotNull() throws Exception {
		assertNotNull(activity);
	}

	@Test
	public void categoriesFragmentHasBeenSet() throws Exception {

		List<Fragment> fragments = activity.getSupportFragmentManager().getFragments();
		//the categories fragment has been attached and therefore the following
		//statement is false
		assertFalse(fragments.isEmpty());

		//there is only one fragment set
		assertTrue(fragments.size() == 1);

		//and it's an instance of the CategoriesFragment
		assertTrue(fragments.get(0) instanceof CategoriesFragment);
	}

	@Test
	public void whenCategoriesFragmentRemovedListISEmpty() throws Exception {

		activity.getSupportFragmentManager().getFragments().remove(0);
		//the categories fragment has been de-attached and it's empty
		assertTrue(activity.getSupportFragmentManager().getFragments().isEmpty());

		//and the size is 0
		assertTrue(activity.getSupportFragmentManager().getFragments().size() == 0);
	}


	@After
	public void tearDown() throws Exception {
		activity = null;
	}

}