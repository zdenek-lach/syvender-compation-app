package dev.syvender.syvendercompanion;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {
    private BackgroundMusic mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        mediaPlayer = new BackgroundMusic(this);
        mediaPlayer.startMusic();
    }

    public void switchMusic(View view){
        mediaPlayer.switchMusicState(view);
    }

    public void GoToQuestTracker(View view){
        Intent intent = new Intent(this,QuestTrackerActivity.class);
        startActivity(intent);
    }
    public void GoToItemInfo(View view){
        Intent intent = new Intent(this,ItemInfoActivity.class);
        startActivity(intent);
    }
    public void GoToStoryCollection(View view){
        Intent intent = new Intent(this,StoryCollectionActivity.class);
        startActivity(intent);
    }
    public void GoToUpdates(View view){
        Intent intent = new Intent(this,UpdatesActivity.class);
        startActivity(intent);
    }
    public void GoToEvents(View view){
        Intent intent = new Intent(this,EventsActivity.class);
        startActivity(intent);
    }
    public void GoToMerchendise(View view){
        Intent intent = new Intent(this,MerchendiseActivity.class);
        startActivity(intent);
    }
    public void GoToDonates(View view){
        Intent intent = new Intent(this,DonateActivity.class);
        startActivity(intent);
    }



}