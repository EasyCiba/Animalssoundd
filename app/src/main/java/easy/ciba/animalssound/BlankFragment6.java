package easy.ciba.animalssound;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class BlankFragment6 extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_blank_fragment6, container, false);



		final EditText save = (EditText) view.findViewById(R.id.editText);

		final Button StartGame = (Button) view.findViewById(R.id.BtnStartGame);

		save.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				String UserName = save.getText().toString().trim();

				StartGame.setEnabled(!UserName.isEmpty());

			}

			@Override
			public void afterTextChanged(Editable editable) {

			}
		});

		StartGame.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String UserName = save.getText().toString();
				Intent intent = new Intent(getActivity(),GameActivity.class);
				intent.putExtra("NAME",UserName);
				startActivity(intent);

			}
		});
		return  view;
	}




}
