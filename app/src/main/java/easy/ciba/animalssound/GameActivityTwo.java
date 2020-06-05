package easy.ciba.animalssound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.text.DateFormat;
import java.util.Calendar;

public class GameActivityTwo extends AppCompatActivity {

	int lastScrol;
	DatabaseHelper db;
	TextView scrol, date;
	ImageButton BackBtn;
	private AdView mAdView;
	private InterstitialAd mInterstitialAd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_two);

		db = new DatabaseHelper(this);

		Calendar calendar = Calendar.getInstance();
		String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

		date = (TextView)findViewById(R.id.datee);
		date.setText(currentDate);

		scrol = (TextView)findViewById(R.id.textLvl);
		BackBtn = (ImageButton)findViewById(R.id.back);



		MobileAds.initialize(this,"ca-app-pub-5806026314019170~2002004664");


		mInterstitialAd = new InterstitialAd(this);
		mInterstitialAd.setAdUnitId("ca-app-pub-5806026314019170/5111996116");
		mInterstitialAd.loadAd(new AdRequest.Builder().build());

		mInterstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				if(mInterstitialAd.isLoaded()){
					mInterstitialAd.show();
				}

			}
		});



		SharedPreferences preferences = getSharedPreferences("PREFS", 0);
		lastScrol = preferences.getInt("lastScrol", 0);

		scrol.setText(" "+ lastScrol);



				BackBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				String scroll = scrol.getText().toString();
				String time = date.getText().toString();

				boolean insertData = db.insertDate(scroll, time);
				if (insertData == true) {

				} else {

				}



				SharedPreferences preferences = getSharedPreferences("PREFS", 0);
				SharedPreferences.Editor editor = preferences.edit();
				editor.putInt("lastScrol", lastScrol);
				editor.apply();


				Intent intent = new Intent(GameActivityTwo.this, MainActivity.class);
				GameActivityTwo.this.startActivity(intent);
				mInterstitialAd.loadAd(new AdRequest.Builder().build());
				GameActivityTwo.this.finish();


			}
		});

	}
}
