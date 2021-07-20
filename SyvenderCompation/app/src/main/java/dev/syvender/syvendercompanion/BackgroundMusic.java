package dev.syvender.syvendercompanion;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

public class BackgroundMusic {
    private MediaPlayer mediaPlayer;
    private int playbackBgMusic;

    public BackgroundMusic(Context context) {
        try {
            this.mediaPlayer = MediaPlayer.create(context, R.raw.syvender_music);
        }catch(Exception ex)
        {
            throw new IllegalStateException("Song not loaded!");
        }
        this.mediaPlayer.setLooping(true);
        this.playbackBgMusic = 1;
    }


    public void pauseMusic() {
        this.mediaPlayer.pause();
    }

    public void startMusic() {
        this.mediaPlayer.start();
    }

    public void switchMusicState() {
        switch (this.playbackBgMusic) {
            case 1:
                pauseMusic();
                this.playbackBgMusic = 2;
                break;

            case 2:
                startMusic();
                this.playbackBgMusic = 1;
                break;
            default:
                throw new IllegalStateException("Playback play/pause switch reached illegal state!");
        }
    }
}
