	package easy.ciba.animalssound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

	public class GameActivity extends AppCompatActivity {
		TextView text, textlvl, textscrol;
	Random r = new Random();
	MediaPlayer mediaPlayer;
	private ImageButton button1;
	private ImageButton button2;
	private ImageButton button3;
	private ImageButton button4;
	int res;
	int a0;
	int a1;
	int a2;
	int a3;
	private int CountLvl = 0;
	private int CountScrol = 0;

	private SoundPool soundPool;
	private int trueSound, falseSound;
	float volumeleft = 0f;
	float volumeright = 0.6f;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);


		text = (TextView) findViewById(R.id.textView3);
		textlvl = (TextView)findViewById(R.id.textcounter);
		textscrol = (TextView) findViewById(R.id.textView6);


		button1 = (ImageButton) findViewById(R.id.imageButton16);
		button2 = (ImageButton) findViewById(R.id.imageButton17);
		button3 = (ImageButton) findViewById(R.id.imageButton18);
		button4 = (ImageButton) findViewById(R.id.imageButton19);


		if  (Build.VERSION.SDK_INT >  Build.VERSION_CODES.LOLLIPOP){
			AudioAttributes audioAttributes = new AudioAttributes.Builder()
					.setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
					.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
					.build();

			soundPool = new SoundPool.Builder()
					.setMaxStreams(1)
					.setAudioAttributes(audioAttributes)
					.build();
		}else {

			soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		}

		trueSound = soundPool.load(this, R.raw.truesound,1);
		falseSound = soundPool.load(this, R.raw.falsesound,1);

		this.setGame();




	}


		public void setGame() {
			if (mediaPlayer != null)
				mediaPlayer.stop();
			//result();
			button1.setEnabled(true);
			button2.setEnabled(true);
			button3.setEnabled(true);
			button4.setEnabled(true);

			button1.setBackgroundResource(R.drawable.mybutton);
			button2.setBackgroundResource(R.drawable.mybutton);
			button3.setBackgroundResource(R.drawable.mybutton);
			button4.setBackgroundResource(R.drawable.mybutton);



			a0 = r.nextInt(31);
			a1 = r.nextInt(31);
			while (a1 == a0) {
				a1 = r.nextInt(31);
			}
			a2 = r.nextInt(31);
			while ((a2 == a0) || (a2 == a1)) {
				a2 = r.nextInt(31);
			}
			a3 = r.nextInt(31);
			while ((a3 == a0) || (a3 == a1) || (a3 == a2)) {
				a3 = r.nextInt(31);
			}

			this.setImage(button1, a0);
			this.setImage(button2, a1);
			this.setImage(button3, a2);
			this.setImage(button4, a3);


			int i = r.nextInt(4);
			switch (i) {
				case 0:
					this.res = a0;
					break;
				case 1:
					this.res = a1;
					break;
				case 2:
					this.res = a2;
					break;
				case 3:
					this.res = a3;
					break;
			}
			if (mediaPlayer != null)
				mediaPlayer.stop();
			mediaPlayer = setSound(res);
			mediaPlayer.start();

		}


		public void onButton1Click(View view) {
			if (mediaPlayer != null)
				mediaPlayer.stop();
			if (res == a0) {
				soundPool.play(trueSound,volumeleft,volumeright,1,0,1f);
				CountScrol+=200;
				textscrol.setText(Integer.toString(CountScrol));
				CountLvl++;
				textlvl.setText(Integer.toString(CountLvl));
				button1.setBackgroundColor(Color.GREEN);

			} else {
				soundPool.play(falseSound,volumeleft,volumeright,1,0,1f);
				CountLvl++;
				textlvl.setText(Integer.toString(CountLvl));
				button1.setBackgroundColor(Color.RED);

			}

			mediaPlayer.start();
			button1.setEnabled(false);
			button2.setEnabled(false);
			button3.setEnabled(false);
			button4.setEnabled(false);

			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				public void run() {
					mediaPlayer.stop();
					if (CountLvl >= 5){
						result();
					}
					else
						setGame();
				}
			}, 2000);
		}

		public void onButton2Click(View view) {
			if (mediaPlayer != null)
				mediaPlayer.stop();
			if (res == a1) {
				soundPool.play(trueSound,volumeleft,volumeright,1,0,1f);
				CountScrol+=200;
				textscrol.setText(Integer.toString(CountScrol));
				CountLvl++;
				textlvl.setText(Integer.toString(CountLvl));
				button2.setBackgroundColor(Color.GREEN);
				;

			} else {
				soundPool.play(falseSound,volumeleft,volumeright,1,0,1f);
				CountLvl++;
				textlvl.setText(Integer.toString(CountLvl));
				button2.setBackgroundColor(Color.RED);
				;

			}
			mediaPlayer.start();
			button1.setEnabled(false);
			button2.setEnabled(false);
			button3.setEnabled(false);
			button4.setEnabled(false);

			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				public void run() {
					mediaPlayer.stop();
					if (CountLvl >= 5){
						result();
					}
					else
						setGame();
				}
			}, 2000);
		}

		public void onButton3Click(View view) {
			if (mediaPlayer != null)
				mediaPlayer.stop();
			if (res == a2) {
				soundPool.play(trueSound,volumeleft,volumeright,1,0,1f);
				CountScrol+=200;
				textscrol.setText(Integer.toString(CountScrol));
				CountLvl++;
				textlvl.setText(Integer.toString(CountLvl));
				button3.setBackgroundColor(Color.GREEN);

			} else {
				soundPool.play(falseSound,volumeleft,volumeright,1,0,1f);
				CountLvl++;
				textlvl.setText(Integer.toString(CountLvl));
				button3.setBackgroundColor(Color.RED);

			}
			mediaPlayer.start();
			button1.setEnabled(false);
			button2.setEnabled(false);
			button3.setEnabled(false);
			button4.setEnabled(false);

			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				public void run() {
					mediaPlayer.stop();
					if (CountLvl >= 5){
						result();
					}
					else
						setGame();
				}
			}, 2000);
		}

		public void onButton4Click(View view) {
			if (mediaPlayer != null)
				mediaPlayer.stop();
			if (res == a3) {
				soundPool.play(trueSound,volumeleft,volumeright,1,0,1f);
				CountScrol+=200;
				textscrol.setText(Integer.toString(CountScrol));
				CountLvl++;
				textlvl.setText(Integer.toString(CountLvl));
				button4.setBackgroundColor(Color.GREEN);

			} else {
				soundPool.play(falseSound,volumeleft,volumeright,1,0,1f);
				CountLvl++;
				textlvl.setText(Integer.toString(CountLvl));
				button4.setBackgroundColor(Color.RED);

			}
			mediaPlayer.start();
			button1.setEnabled(false);
			button2.setEnabled(false);
			button3.setEnabled(false);
			button4.setEnabled(false);

			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				public void run() {
					mediaPlayer.stop();
					if (CountLvl >= 5){
						result();
					}
					else
					setGame();
				}
			}, 2000);
		}

		public void result (){

		if (CountLvl >= 5){
			mediaPlayer.stop();

			SharedPreferences preferences = getSharedPreferences("PREFS", 0);
			SharedPreferences.Editor editor = preferences.edit();
			editor.putInt("lastScrol", CountScrol);
			editor.apply();

			Intent intent = new Intent(GameActivity.this, GameActivityTwo.class);
			GameActivity.this.startActivity(intent);
			GameActivity.this.finish();



		}

		}



		public MediaPlayer setSound(int num) {
			switch (num) {
				case 0:
					return MediaPlayer.create(getBaseContext(), R.raw.mausse);
				case 1:
					return MediaPlayer.create(getBaseContext(), R.raw.kengyry);
				case 2:
					return MediaPlayer.create(getBaseContext(), R.raw.jiraf);
				case 3:
					return MediaPlayer.create(getBaseContext(), R.raw.medvv);
				case 4:
					return MediaPlayer.create(getBaseContext(), R.raw.belkaaa);
				case 5:
					return MediaPlayer.create(getBaseContext(), R.raw.chaikaa);
				case 6:
					return MediaPlayer.create(getBaseContext(), R.raw.delfiiin);
				case 7:
					return MediaPlayer.create(getBaseContext(), R.raw.giena);
				case 8:
					return MediaPlayer.create(getBaseContext(), R.raw.gys);
				case 9:
					return MediaPlayer.create(getBaseContext(), R.raw.kittt);
				case 10:
					return MediaPlayer.create(getBaseContext(), R.raw.lev);
				case 11:
					return MediaPlayer.create(getBaseContext(), R.raw.orell);
				case 12:
					return MediaPlayer.create(getBaseContext(), R.raw.petyx);
				case 13:
					return MediaPlayer.create(getBaseContext(), R.raw.sobaka);
				case 14:
					return MediaPlayer.create(getBaseContext(), R.raw.dikobrazz);
				case 15:
					return MediaPlayer.create(getBaseContext(), R.raw.djatell);
				case 16:
					return MediaPlayer.create(getBaseContext(), R.raw.flamingoo);
				case 17:
					return MediaPlayer.create(getBaseContext(), R.raw.horsess);
				case 18:
					return MediaPlayer.create(getBaseContext(), R.raw.induk);
				case 19:
					return MediaPlayer.create(getBaseContext(), R.raw.korova);
				case 20:
					return MediaPlayer.create(getBaseContext(), R.raw.kot);
				case 21:
					return MediaPlayer.create(getBaseContext(), R.raw.olen);
				case 22:
					return MediaPlayer.create(getBaseContext(), R.raw.ovca);
				case 23:
					return MediaPlayer.create(getBaseContext(), R.raw.panda);
				case 24:
					return MediaPlayer.create(getBaseContext(), R.raw.pingvinn);
				case 25:
					return MediaPlayer.create(getBaseContext(), R.raw.slonnn);
				case 26:
					return MediaPlayer.create(getBaseContext(), R.raw.soloveii);
				case 27:
					return MediaPlayer.create(getBaseContext(), R.raw.sovaa);
				case 28:
					return MediaPlayer.create(getBaseContext(), R.raw.volk);
				case 29:
					return MediaPlayer.create(getBaseContext(), R.raw.voronaa);
				case 30:
					return MediaPlayer.create(getBaseContext(), R.raw.vorobeei);
			}
			return MediaPlayer.create(getBaseContext(), R.raw.vorobeei);
		}



		public void setImage(ImageButton button, int num) {
			switch (num) {
				case 0:
					button.setImageResource(R.drawable.mouse);
					break;
				case 1:
					button.setImageResource(R.drawable.kengyry);
					break;
				case 2:
					button.setImageResource(R.drawable.jiraff);
					break;
				case 3:
					button.setImageResource(R.drawable.bear);
					break;
				case 4:
					button.setImageResource(R.drawable.belka);
					break;
				case 5:
					button.setImageResource(R.drawable.chayka);
					break;
				case 6:
					button.setImageResource(R.drawable.delfin);
					break;
				case 7:
					button.setImageResource(R.drawable.giena);
					break;
				case 8:
					button.setImageResource(R.drawable.gys);
					break;
				case 9:
					button.setImageResource(R.drawable.kit);
					break;
				case 10:
					button.setImageResource(R.drawable.lion);
					break;
				case 11:
					button.setImageResource(R.drawable.eagle);
					break;
				case 12:
					button.setImageResource(R.drawable.petyx);
					break;
				case 13:
					button.setImageResource(R.drawable.dog);
					break;
				case 14:
					button.setImageResource(R.drawable.dikoobraz);
					break;
				case 15:
					button.setImageResource(R.drawable.djatel);
					break;
				case 16:
					button.setImageResource(R.drawable.flsmingo);
					break;
				case 17:
					button.setImageResource(R.drawable.lohad);
					break;
				case 18:
					button.setImageResource(R.drawable.indyc);
					break;
				case 19:
					button.setImageResource(R.drawable.korova);
					break;
				case 20:
					button.setImageResource(R.drawable.cat);
					break;
				case 21:
					button.setImageResource(R.drawable.olen);
					break;
				case 22:
					button.setImageResource(R.drawable.ovca);
					break;
				case 23:
					button.setImageResource(R.drawable.panda);
					break;
				case 24:
					button.setImageResource(R.drawable.ping);
					break;
				case 25:
					button.setImageResource(R.drawable.eleff);
					break;
				case 26:
					button.setImageResource(R.drawable.solovey);
					break;
				case 27:
					button.setImageResource(R.drawable.sova);
					break;
				case 28:
					button.setImageResource(R.drawable.wolf);
					break;
				case 29:
					button.setImageResource(R.drawable.vorona);
					break;
				case 30:
					button.setImageResource(R.drawable.sinich);
					break;
			}
		}
		@Override
		public   void onDestroy(){
			super.onDestroy();
			soundPool.release();
			soundPool = null;
		}


}
