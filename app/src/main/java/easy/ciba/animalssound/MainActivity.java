package easy.ciba.animalssound;

import com.google.ads.consent.*;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
	private ConsentForm form;
	private AdView mAdView;
	/**
	 * The {@link PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link FragmentPagerAdapter} derivative, which will keep every
	 * loaded fragment in memory. If this becomes too memory intensive, it
	 * may be best to switch to a
	 * {@link FragmentStatePagerAdapter}.
	 */
	private SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager mViewPager;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		ConsentInformation consentInformation = ConsentInformation.getInstance(getApplicationContext());
		String[] publisherIds = {"pub-5806026314019170"};
		consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
			@Override
			public void onConsentInfoUpdated(ConsentStatus consentStatus) {

				boolean inEEA = ConsentInformation.getInstance(getApplicationContext()).isRequestLocationInEeaOrUnknown();

				if(inEEA){
					if (consentStatus == consentStatus.PERSONALIZED){

					}else if (consentStatus == consentStatus.NON_PERSONALIZED){
						Bundle extras = new Bundle();
						extras.putString("npa", "1");

						AdRequest request = new AdRequest.Builder()
								.addNetworkExtrasBundle(AdMobAdapter.class, extras)
								.build();
					}else{
						URL privacyUrl = null;
						try {
							// TODO: Replace with your app's privacy policy URL.
							privacyUrl = new URL("https://www.your.com/privacyurl");
						} catch (MalformedURLException e) {
							e.printStackTrace();

						}
						form = new ConsentForm.Builder(MainActivity.this, privacyUrl)
								.withListener(new ConsentFormListener() {
									@Override
									public void onConsentFormLoaded() {

										form.show();
									}

									@Override
									public void onConsentFormOpened() {

									}

									@Override
									public void onConsentFormClosed(
											ConsentStatus consentStatus, Boolean userPrefersAdFree) {

										if(consentStatus == consentStatus.NON_PERSONALIZED){
											Bundle extras = new Bundle();
											extras.putString("npa", "1");

											AdRequest request = new AdRequest.Builder()
													.addNetworkExtrasBundle(AdMobAdapter.class, extras)
													.build();
										}
									}

									@Override
									public void onConsentFormError(String errorDescription) {

									}
								})
								.withPersonalizedAdsOption()
								.withNonPersonalizedAdsOption()

								.build();
						form.load();
					}
				}else{

				}


			}

			@Override
			public void onFailedToUpdateConsentInfo(String errorDescription) {

			}
		});


		mAdView = findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);




		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.container);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		mViewPager.setOffscreenPageLimit(8);

		mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
		tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

		mAdView.setAdListener(new AdListener() {

			@Override
			public void onAdLoaded() {
				mAdView.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAdFailedToLoad(int error) {
				mAdView.setVisibility(View.GONE);
			}

		});


	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	public static class PlaceholderFragment extends Fragment {

		private static final String ARG_SECTION_NUMBER = "section_number";

		public PlaceholderFragment() {
		}

		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}


	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {

			switch (position){

				case 0:
					return  new BlankFragment();

				case 1:
					return  new BlankFragment2();

				case 2:
					return new BlankFragment3();

				case 3:
					return  new BlankFragment4();

				case 4:
					return new BlankFragment5();

				case 5:
					return new BlankFragment6();

				default:
					return null;
			}

		}


		@Override
		public int getCount() {

			return 6;
		}
	}




	protected void onResume(){
		super.onResume();

		mAdView.resume();
	}

	@Override
	protected void onPause(){
		super.onPause();

		mAdView.pause();
	}

	@Override
	protected  void onDestroy(){
		super.onDestroy();

		mAdView.destroy();
	}
}