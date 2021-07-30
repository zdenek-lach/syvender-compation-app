package io.github.syvendercompanion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemCollectionFragment extends Fragment {

    List<Item> listOfItem;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listOfItem = new ArrayList<>();
        listOfItem.add(new Item("Gold Coin","Misc","The most precious peace of coin you can get!",R.drawable.gold_coin));
        listOfItem.add(new Item("Silver Coin","Misc","The coin for the rich, but still not the richest!",R.drawable.silver_coin));
        listOfItem.add(new Item("Bronze Coin","Misc","A piece of metal that'll keep you alive!",R.drawable.bronze_coin));
        listOfItem.add(new Item("Gold key","Misc","This key unlocks a great treassure.",R.drawable.gold_key));
        listOfItem.add(new Item("Silver Key","Misc","Your reward awaits in a locked chest that's no longer a problem!",R.drawable.silver_key));
        listOfItem.add(new Item("Axe","Weapon","What does a viking swing in your face? It's an axe!",R.drawable.axe));
        listOfItem.add(new Item("Bone","Misc","In a history long time ago, there was a skeleton. This is a part of it.",R.drawable.bone));
        listOfItem.add(new Item("Boots","Armor","With these on, you don't have to rub your precious toes on the ground!",R.drawable.boots));
        listOfItem.add(new Item("Helmet","Armor","When they bonk your head, you bonk them back!",R.drawable.helmet));
        listOfItem.add(new Item("Leggings","Armor","Protection where necessary!",R.drawable.leggings));
        listOfItem.add(new Item("Bow","Ranged Weapon","Trusty bow is always a good companion on every journey.",R.drawable.bow));
        listOfItem.add(new Item("Cookie","Food","Who could even think about surviving without the delicious cookie?!",R.drawable.cookie));
        listOfItem.add(new Item("Health Potion","Potions","Someone chopped your hand of? Grab the potion with the other hand and chop their hands back!",R.drawable.health_potion));
        listOfItem.add(new Item("Mana Potion","Potions","Basically Redbull (not sponsored).",R.drawable.mana_potion));
        listOfItem.add(new Item("Meat","Food","With a bit of spice it's gonna be a heavenly feast!",R.drawable.meat));
        listOfItem.add(new Item("Silver Arrow","Ranged Weapon","Don't just smash your enemies with the bow, use this to reach further!",R.drawable.silver_arrow));
        listOfItem.add(new Item("Sword","Weapon","Swing and slash, smash and dash! Get them all!",R.drawable.sword));


        RecyclerView myRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this.getContext(),listOfItem);
        myRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        myRecyclerView.setAdapter(myAdapter);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_item_collection,container,false);
    }

}
