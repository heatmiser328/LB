package com.ica.dice;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

public class PlayAudio {
    private MediaPlayer player;
    private Context context;

	public PlayAudio(Context ctx) {
		context = ctx;
        player = null;
	}
		
	public void play () {
		try {
			player = MediaPlayer.create (context, R.raw.droll);
            player.setLooping(false);
			player.setOnCompletionListener(new OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (player != null) {
                        player.stop();
                        player.release();
                        player = null;
                    }
                }
			});
			player.start ();
		} catch (Exception ex) {
            Log.e("PlayAudio", "Failed to play audio", ex);
		}
	}

}
