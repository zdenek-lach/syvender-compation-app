package io.github.syvendercompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

//    List<Item> listOfItem;

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

//        listOfItem = new ArrayList<>();
//        listOfItem.add(new Item("Gold Coin","Misc","The most precious peace of coin you can get!",R.drawable.gold_coin));
//        listOfItem.add(new Item("Silver Coin","Misc","The coin for the rich, but still not the richest!",R.drawable.silver_coin));
//        listOfItem.add(new Item("Bronze Coin","Misc","A piece of metal that'll keep you alive!",R.drawable.bronze_coin));
//        listOfItem.add(new Item("Gold key","Misc","This key unlocks a great treassure.",R.drawable.gold_key));
//        listOfItem.add(new Item("Silver Key","Misc","Your reward awaits in a locked chest that's no longer a problem!",R.drawable.silver_key));
//        listOfItem.add(new Item("Axe","Weapon","What does a viking swing in your face? It's an axe!",R.drawable.axe));
//        listOfItem.add(new Item("Bone","Misc","In a history long time ago, there was a skeleton. This is a part of it.",R.drawable.bone));
//        listOfItem.add(new Item("Boots","Armor","With these on, you don't have to rub your precious toes on the ground!",R.drawable.boots));
//        listOfItem.add(new Item("Helmet","Armor","When they bonk your head, you bonk them back!",R.drawable.helmet));
//        listOfItem.add(new Item("Leggings","Armor","Protection where necessary!",R.drawable.leggings));
//        listOfItem.add(new Item("Bow","Ranged Weapon","Trusty bow is always a good companion on every journey.",R.drawable.bow));
//        listOfItem.add(new Item("Cookie","Food","Who could even think about surviving without the delicious cookie?!",R.drawable.cookie));
//        listOfItem.add(new Item("Health Potion","Potions","Someone chopped your hand of? Grab the potion with the other hand and chop their hands back!",R.drawable.health_potion));
//        listOfItem.add(new Item("Mana Potion","Potions","Basically Redbull (not sponsored).",R.drawable.mana_potion));
//        listOfItem.add(new Item("Meat","Food","With a bit of spice it's gonna be a heavenly feast!",R.drawable.meat));
//        listOfItem.add(new Item("Silver Arrow","Ranged Weapon","Don't just smash your enemies with the bow, use this to reach further!",R.drawable.silver_arrow));
//        listOfItem.add(new Item("Sword","Weapon","Swing and slash, smash and dash! Get them all!",R.drawable.sword));
//
//        RecyclerView myRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
//        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,listOfItem);
//        myRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
//        myRecyclerView.setAdapter(myAdapter);


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
            case R.id.nav_item_collection:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ItemCollectionFragment()).commit();
                break;
            case R.id.nav_merch:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MerchendiseFragment()).commit();
                Toast.makeText(this, "We're still preparing merchendise for you.", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_donate:
                OpenPaypalLink();
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

    public void OpenPaypalLink() {
        String link = getString(R.string.donate_link);
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