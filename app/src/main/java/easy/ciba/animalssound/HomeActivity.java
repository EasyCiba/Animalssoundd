package easy.ciba.animalssound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class HomeActivity extends AppCompatActivity {
private final int SPLASH_DISPLAY_LEHGHT = 2000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);


		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent i = new Intent(HomeActivity.this, MainActivity.class);
				HomeActivity.this.startActivity(i);
				HomeActivity.this.finish();
			}
		},SPLASH_DISPLAY_LEHGHT);
	}
	@Override
	public void onBackPressed(){
		super.onBackPressed();
	}
}
