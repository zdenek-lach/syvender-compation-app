package io.github.syvendercompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class QuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MultiAdapter extends RecyclerView.Adapter<MultiAdapter.MultiViewHolder> {

        public class MultiViewHolder extends RecyclerView.ViewHolder{
            private TextView textView;
            private ImageView imageView;

            public MultiViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.quest_name);
                imageView = itemView.findViewById(R.id.imageviewTick);

            }

            void bind(final QuestModelClass questModelClass){
                imageView.setVisibility(questModelClass.isChecked ? View.VISIBLE : View.GONE);
                textView.setText(questModelClass.getName());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        questModelClass.setChecked(!questModelClass.isChecked());
                        imageView.setVisibility(questModelClass.isChecked ? View.VISIBLE : View.GONE);
//https://youtu.be/r75l8GFYoXc?t=373
                    }
                });
            }

        }
    }



    public class QuestModelClass implements Serializable {
        private boolean isChecked = false;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }
    }
}