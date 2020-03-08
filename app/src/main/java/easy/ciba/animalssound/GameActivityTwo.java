package easy.ciba.animalssound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivityTwo extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_two);



		Button BackBtn = (Button)findViewById(R.id.back);

		BackBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(GameActivityTwo.this, MainActivity.class);
				GameActivityTwo.this.startActivity(i);
				GameActivityTwo.this.finish();

			}
		});

	}
}
