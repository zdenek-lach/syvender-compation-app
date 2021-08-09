package io.github.syvendercompanion;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

public class SyvenderDJ {
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    public SyvenderDJ(Context context) {
        try {
            this.mediaPlayer = MediaPlayer.create(context, R.raw.syvender_music);
            this.mediaPlayer.start();
        } catch (Exception ex) {
            Toast.makeText(context, "Background song playback failed!", Toast.LENGTH_LONG);
            mediaPlayer.stop();
            throw new IllegalStateException("Song playback failed!");
        }
        this.mediaPlayer.setLooping(true);
    }

    public void switchPausePlay(Context context) {
        if (isPlaying()){
            pauseMusic();
            Toast.makeText(context, "Music paused.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            startMusic();
            Toast.makeText(context, "Music playing..", Toast.LENGTH_SHORT).show();
        }
    }

    public int pauseMusic() {
        this.mediaPlayer.pause();
        isPlaying = false;
        return 1;
    }

    public int startMusic() {
        this.mediaPlayer.start();
        isPlaying = true;
        return 0;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
