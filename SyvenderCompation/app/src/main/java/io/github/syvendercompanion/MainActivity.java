package io.github.syvendercompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private SyvenderDJ DJ;
    private int isMusicPlaying;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(R.color.teal_200);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DJ = new SyvenderDJ(this);
        DJ.startMusic();


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_closed);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_menu);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
                break;
            case R.id.nav_story_time:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StoryTimeFragment()).commit();
                break;
            case R.id.nav_quest_tracker:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new QuestTrackerFragment()).commit();
                break;
            case R.id.nav_discord:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DiscordFragment()).commit();
                break;
            case R.id.nav_updates:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UpdatesFragment()).commit();
                break;
            case R.id.nav_item_collection:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ItemCollectionFragment()).commit();
                break;
            case R.id.nav_merch:
                Toast.makeText(this, "We're still preparing merchandise for you.", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_donate:
                OpenPaypalLink();
                break;
            case R.id.nav_music_toggle:
                DJ.switchPausePlay(this);
                break;
            case R.id.nav_contact_developers:
                sendEmail();
                break;
            default:
                Toast.makeText(this, "Weird selection happened", Toast.LENGTH_LONG).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void OpenDiscordInvite(View view) {
        String link = getString(R.string.invite_link);
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }

    public void OpenPaypalLink() {
        String link = getString(R.string.donate_link);
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }


    public void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"devs@syvender.io"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Reporting a bug!");
        startActivity(emailIntent);
    }

    public void openQuestActivity(View view) {
        Intent intent = new Intent(this, QuestActivity.class);
        startActivity(intent);
    }
}