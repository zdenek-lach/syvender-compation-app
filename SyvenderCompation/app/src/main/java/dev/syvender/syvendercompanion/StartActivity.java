package dev.syvender.syvendercompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {


    private BackgroundMusic mediaPlayer;
    public void launchApp(View view){
        setContentView(R.layout.activity_start);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer = new BackgroundMusic(this);
        mediaPlayer.startMusic();
        setContentView(R.layout.activity_start);
    }

    public void switchMusic(View view){
        mediaPlayer.switchMusicState();
    }

    public void switchToMainMenu(View view){
        Intent intent = new Intent(this,MainMenu.class);
        mediaPlayer.switchMusicState();
        startActivity(intent);
        this.finish();
    }




}