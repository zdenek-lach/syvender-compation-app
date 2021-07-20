package dev.syvender.syvendercompanion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainMenu extends AppCompatActivity{
    private BackgroundMusic mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        mediaPlayer = new BackgroundMusic(this);
        mediaPlayer.startMusic();
    }



    public void GoToQuestTracker(MenuItem view){
        Intent intent = new Intent(this,QuestTrackerActivity.class);
        startActivity(intent);
    }
    public void GoToItemInfo(MenuItem view){
        Intent intent = new Intent(this,ItemInfoActivity.class);
        startActivity(intent);
    }
    public void GoToStoryTime(MenuItem view){
        Intent intent = new Intent(this, StoryTimeActivity.class);
        startActivity(intent);
    }
    public void GoToUpdates(MenuItem view){
        Intent intent = new Intent(this,UpdatesActivity.class);
        startActivity(intent);
    }
    public void GoToDiscord(MenuItem view){
        Intent intent = new Intent(this,EventsActivity.class);
        startActivity(intent);
    }
    public void GoToMerchendise(MenuItem view){
        Intent intent = new Intent(this,MerchendiseActivity.class);
        startActivity(intent);
    }
    public void GoToDonates(MenuItem view){
        Intent intent = new Intent(this,DonateActivity.class);
        startActivity(intent);
    }
}

