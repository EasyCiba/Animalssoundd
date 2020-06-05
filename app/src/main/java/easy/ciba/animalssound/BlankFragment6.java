package easy.ciba.animalssound;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;




public class BlankFragment6 extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_blank_fragment6, container, false);
		final DatabaseHelper db;
		int lastScrol;

		db = new DatabaseHelper(getActivity());


		TextView scrol = (TextView)view.findViewById(R.id.textView7);
		ImageButton StartGame = (ImageButton) view.findViewById(R.id.BtnStartGame);
		ImageButton ViewDate = (ImageButton)view.findViewById(R.id.viewDate);


		SharedPreferences preferences = this.getActivity().getSharedPreferences("PREFS", 0);
		lastScrol = preferences.getInt("lastScrol", 0);





		scrol.setText("" + lastScrol);





		ViewDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Cursor data = db.viewData();

				if (data.getCount() == 0) {
					display("Таблица рекордов:", "Нет записей.");
					return;
				}
				StringBuffer buffer = new StringBuffer();
				while (data.moveToNext()) {
					//buffer.append("ID: " + data.getString(0) + "\n");
					buffer.append("Очки: " + data.getString(1) + "\n");
					buffer.append("Дата: " + data.getString(2) + "\n"+"\n");
				}
				display("Таблица рекордов:", buffer.toString());
			}
		});

		StartGame.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getActivity(),GameActivity.class);
				startActivity(intent);

			}
		});
		return  view;
	}


	public void display(String title, String message){
		final DatabaseHelper db;
		db = new DatabaseHelper(getActivity());
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setCancelable(true)
		.setTitle(title)
		.setMessage(message)
				.setNegativeButton("Выйти", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
					}
				})
		        .setPositiveButton("Очистить все", new DialogInterface.OnClickListener(){
			        @Override
			        public void onClick(DialogInterface dialogInterface, int i) {
				    db.delete("TABLE_NAME", null, null);
			        }
		        });


		builder.show();
	}


}
