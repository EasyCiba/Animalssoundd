	package easy.ciba.animalssound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

	public class GameActivity extends AppCompatActivity {
		TextView text, textlvl;
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



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		text = (TextView) findViewById(R.id.textView3);
		textlvl = (TextView)findViewById(R.id.textcounter);

		text.setText("Игрок: "+getIntent().getStringExtra("NAME"));

		button1 = (ImageButton) findViewById(R.id.imageButton16);
		button2 = (ImageButton) findViewById(R.id.imageButton17);
		button3 = (ImageButton) findViewById(R.id.imageButton18);
		button4 = (ImageButton) findViewById(R.id.imageButton19);

		//button1.setBackgroundResource(R.drawable.mybutton);
		//button2.setBackgroundResource(R.drawable.mybutton);
		//button3.setBackgroundResource(R.drawable.mybutton);
		//button4.setBackgroundResource(R.drawable.mybutton);

		this.setGame();


	}


		public void setGame() {
			result();
			button1.setEnabled(true);
			button2.setEnabled(true);
			button3.setEnabled(true);
			button4.setEnabled(true);



			a0 = r.nextInt(14);
			a1 = r.nextInt(14);
			while (a1 == a0) {
				a1 = r.nextInt(14);
			}
			a2 = r.nextInt(14);
			while ((a2 == a0) || (a2 == a1)) {
				a2 = r.nextInt(14);
			}
			a3 = r.nextInt(14);
			while ((a3 == a0) || (a3 == a1) || (a3 == a2)) {
				a3 = r.nextInt(14);
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
				CountLvl++;
				textlvl.setText(Integer.toString(CountLvl));
				button1.setBackgroundColor(Color.GREEN);

			} else {
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
					setGame();
				}
			}, 2000);
		}

		public void onButton2Click(View view) {
			if (mediaPlayer != null)
				mediaPlayer.stop();
			if (res == a1) {
				CountLvl++;
				textlvl.setText(Integer.toString(CountLvl));
				button2.setBackgroundColor(Color.GREEN);
				;

			} else {
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
					setGame();
				}
			}, 2000);
		}

		public void onButton3Click(View view) {
			if (mediaPlayer != null)
				mediaPlayer.stop();
			if (res == a2) {
				CountLvl++;
				textlvl.setText(Integer.toString(CountLvl));
				button3.setBackgroundColor(Color.GREEN);

			} else {
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
					setGame();
				}
			}, 2000);
		}

		public void onButton4Click(View view) {
			if (mediaPlayer != null)
				mediaPlayer.stop();
			if (res == a3) {
				CountLvl++;
				textlvl.setText(Integer.toString(CountLvl));
				button4.setBackgroundColor(Color.GREEN);

			} else {
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
					setGame();
				}
			}, 2000);
		}

		public void result (){
			if (mediaPlayer != null)
				mediaPlayer.stop();
		if (CountLvl == 5){
			Intent i = new Intent(GameActivity.this, GameActivityTwo.class);
			GameActivity.this.startActivity(i);

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
			}
			return MediaPlayer.create(getBaseContext(), R.raw.sobaka);
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
			}
		}


}
