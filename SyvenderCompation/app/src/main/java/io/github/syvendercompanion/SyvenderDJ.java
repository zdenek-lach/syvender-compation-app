package io.github.syvendercompanion;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

public class SyvenderDJ {
    private MediaPlayer mediaPlayer;

    public SyvenderDJ(Context context) {
        try {
            this.mediaPlayer = MediaPlayer.create(context,R.raw.syvender_music);
        }catch(Exception ex)
        {
            Toast.makeText(context,"Background song playback failed!",Toast.LENGTH_LONG);
            throw new IllegalStateException("Song playback failed!");
        }
        this.mediaPlayer.setLooping(true);
    }

    public int pauseMusic() {
        this.mediaPlayer.pause();
        return 1;
    }

    public int startMusic() {
        this.mediaPlayer.start();
        return 0;
    }




}
