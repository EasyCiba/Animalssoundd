package easy.ciba.animalssound;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class BlankFragment2 extends Fragment {

	private int sound, sound1, sound2, sound3, sound4, sound5, sound6, sound7;
	private SoundPool soundPool;
	float volumeleft = 0f;
	float volumeright = 0.2f;



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
		getActivity().setVolumeControlStream(AudioManager.STREAM_NOTIFICATION);


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


		sound = soundPool.load(getActivity(), R.raw.kot,1);
		sound1 = soundPool.load(getActivity(), R.raw.sobaka,1);
		sound2 = soundPool.load(getActivity(), R.raw.petyx,1);
		sound3 = soundPool.load(getActivity(), R.raw.gys,1);
		sound4 = soundPool.load(getActivity(), R.raw.induk,1);
		sound5 = soundPool.load(getActivity(), R.raw.korova,1);
		sound6 = soundPool.load(getActivity(), R.raw.svinia,1);
		sound7 = soundPool.load(getActivity(), R.raw.ovca,1);



		ImageButton imageButton = (ImageButton) view.findViewById(R.id.imageButton);

		imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				soundPool.play(sound,volumeleft, volumeright, 1, 0, 1f);


			}
		});

		ImageButton imageButton2 = (ImageButton) view.findViewById(R.id.imageButton2);

		imageButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				soundPool.play(sound1,volumeleft, volumeright, 1, 0, 1f);

			}
		});

		ImageButton imageButton3 = (ImageButton) view.findViewById(R.id.imageButton3);

		imageButton3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				soundPool.play(sound2,volumeleft, volumeright, 1, 0, 1f);


			}
		});

		ImageButton imageButton4 = (ImageButton) view.findViewById(R.id.imageButton4);

		imageButton4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				soundPool.play(sound3,volumeleft, volumeright, 1, 0, 1f);


			}
		});

		ImageButton imageButton5 = (ImageButton) view.findViewById(R.id.imageButton5);

		imageButton5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				soundPool.play(sound4,volumeleft, volumeright, 1, 0, 1f);


			}
		});

		ImageButton imageButton6 = (ImageButton) view.findViewById(R.id.imageButton6);


		imageButton6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				soundPool.play(sound5,volumeleft, volumeright, 1, 0, 1f);


			}
		});

		ImageButton imageButton7 = (ImageButton) view.findViewById(R.id.imageButton7);


		imageButton7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				soundPool.play(sound6,volumeleft, volumeright, 1, 0, 1f);


			}
		});

		ImageButton imageButton8 = (ImageButton) view.findViewById(R.id.imageButton8);


		imageButton8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				soundPool.play(sound7,volumeleft, volumeright, 1, 0, 1f);


			}
		});


		return  view;
	}

	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);

	}

	@Override
	public   void onDestroy(){
		super.onDestroy();
		soundPool.release();
		soundPool = null;
	}



}
