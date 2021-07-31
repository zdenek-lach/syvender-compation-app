package io.github.syvendercompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    private TextView textViewTitle,textViewDescreiption,textViewcategory;
    private ImageView imageViewThumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        textViewTitle = (TextView) findViewById(R.id.item_detail_title);
        textViewDescreiption = (TextView) findViewById(R.id.item_detail_description);
        textViewcategory = (TextView) findViewById(R.id.item_detail_category);
        imageViewThumbnail = (ImageView) findViewById(R.id.item_detail_thumbnail);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("Title");
        String category = intent.getExtras().getString("Category");
        String Ddescription = intent.getExtras().getString("Description");
        Integer thumbnail = intent.getExtras().getInt("Thumbnail");

        textViewTitle.setText(title);
        textViewDescreiption.setText(Ddescription);
        textViewcategory.setText(category);
        imageViewThumbnail.setImageResource(thumbnail);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id==android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}