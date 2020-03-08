package easy.ciba.animalssound;

import com.google.ads.consent.*;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
	private ConsentForm form;
	private AdView mAdView;
	private InterstitialAd mInterstitialAd;
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
				// User's consent status successfully updated.

				boolean inEEA = ConsentInformation.getInstance(getApplicationContext()).isRequestLocationInEeaOrUnknown();

				if(inEEA){
					if (consentStatus == consentStatus.PERSONALIZED){
						//here not have code
					}else if (consentStatus == consentStatus.NON_PERSONALIZED){
						Bundle extras = new Bundle();
						extras.putString("npa", "1");

						AdRequest request = new AdRequest.Builder()
								.addNetworkExtrasBundle(AdMobAdapter.class, extras)
								.build();
					}else{//code form
						URL privacyUrl = null;
						try {
							// TODO: Replace with your app's privacy policy URL.
							privacyUrl = new URL("https://www.your.com/privacyurl");
						} catch (MalformedURLException e) {
							e.printStackTrace();
							// Handle error.
						}
						form = new ConsentForm.Builder(MainActivity.this, privacyUrl)
								.withListener(new ConsentFormListener() {
									@Override
									public void onConsentFormLoaded() {
										// Consent form loaded successfully.
										form.show();
									}

									@Override
									public void onConsentFormOpened() {
										// Consent form was displayed.
									}

									@Override
									public void onConsentFormClosed(
											ConsentStatus consentStatus, Boolean userPrefersAdFree) {
										// Consent form was closed.
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
										// Consent form error.
									}
								})
								.withPersonalizedAdsOption()
								.withNonPersonalizedAdsOption()
								//.withAdFreeOption()
								.build();
						form.load();
					}//end code form
				}else{
					//not write code
				}


			}

			@Override
			public void onFailedToUpdateConsentInfo(String errorDescription) {
				// User's consent status failed to update.
			}
		});


		MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");


		mAdView = findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);
		mInterstitialAd = new InterstitialAd(this);
		mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
		mInterstitialAd.loadAd(new AdRequest.Builder().build());

		mInterstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				if(mInterstitialAd.isLoaded()){
					mInterstitialAd.show();
				}

			}
			@Override
			public void onAdClosed() {
				// Load the next interstitial.
				//mInterstitialAd.loadAd(new AdRequest.Builder().build());
			}
		});



		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
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
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class below).
			// return PlaceholderFragment.newInstance(position + 1);
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
			// Show 5 total pages.
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