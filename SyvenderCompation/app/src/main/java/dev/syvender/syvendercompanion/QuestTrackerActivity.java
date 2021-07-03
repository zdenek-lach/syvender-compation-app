package dev.syvender.syvendercompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class QuestTrackerActivity extends AppCompatActivity {
    private BackgroundMusic mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer = new BackgroundMusic(this);
        mediaPlayer.startMusic();
        setContentView(R.layout.activity_quest_tracker);
    }
}