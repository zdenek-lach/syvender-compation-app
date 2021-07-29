package io.github.syvendercompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private SyvenderDJ DJ;
    private int isMusicPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
            case R.id.nav_merch:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MerchendiseFragment()).commit();
                break;
            case R.id.nav_donate:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DonateFragment()).commit();
                break;
            case R.id.nav_music_toggle:
                switch (isMusicPlaying) {
                    case 1:
                        isMusicPlaying = DJ.pauseMusic();
                        Toast.makeText(this, R.string.music_play_toast, Toast.LENGTH_LONG).show();
                        break;

                    case 0:
                        isMusicPlaying = DJ.startMusic();
                        Toast.makeText(this, R.string.music_pause_toast, Toast.LENGTH_LONG).show();
                        break;
                }
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


    public void sendEmail(){
        Intent emailIntent = new Intent (Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"devs@syvender.io"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Reporting a bug!");
        startActivity(emailIntent);
    }
}