package io.github.syvendercompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArrayList<QuestModelClass> quests = new ArrayList<>();
    private MultiAdapter adapter;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        recyclerView = findViewById(R.id.quests_recyclerview_id);//potential problem here?
        btn = findViewById(R.id.quest_recyclerview_save_button);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new MultiAdapter(this, quests);
        recyclerView.setAdapter(adapter);
        CreateListOfData();
//        CreateListOfMockData();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getSelected().size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < adapter.getSelected().size(); i++) {
                        stringBuilder.append(adapter.getSelected().get(i).getName());
                        stringBuilder.append("\n");
                    }
                    ShowToast(stringBuilder.toString().trim());

                } else
                    ShowToast("No Selection");

            }
        });

    }

    private void ShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void CreateListOfMockData() {
        quests = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            QuestModelClass quest = new QuestModelClass();
            quest.setName("Quests " + (i + 1));
            if (i==0){
                quest.setChecked(true);
            }

            quests.add(quest);
        }
        adapter.setQuests(quests);
    }

    private void CreateListOfData() {
        quests = new ArrayList<>();
//        StringBuilder questBuilder = new StringBuilder(getResources().getStringArray(R.array.questListArray));
        String[] questList = getResources().getStringArray(R.array.questListArray);
        for (String value : questList
        ) {
            value.trim();
            System.out.println("QUEST LIST VALUE: " + "'" + value + "'");
        }
        for (int i = 0; i < questList.length; i++) {
            QuestModelClass quest = new QuestModelClass();
            quest.setName(questList[i]);
//            if (i == 0) {
//                quest.setChecked(true);
//            }

            quests.add(quest);
        }
        adapter.setQuests(quests);
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

        private Context context;
        private ArrayList<QuestModelClass> quests;

        public MultiAdapter(Context context, ArrayList<QuestModelClass> quests) {
            this.context = context;
            this.quests = quests;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public void setQuests(ArrayList<QuestModelClass> quests) {
            this.quests = new ArrayList<>();
            this.quests = quests;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public MultiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.quest_model, parent, false);
            return new MultiViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MultiViewHolder holder, int position) {
            holder.bind(quests.get(position));
        }

        @Override
        public int getItemCount() {
            return quests.size();
        }

        public class MultiViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;
            private ImageView imageView;

            public MultiViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.quest_name);
                imageView = itemView.findViewById(R.id.imageviewTick);

            }

            void bind(final QuestModelClass questModelClass) {
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

        public ArrayList<QuestModelClass> getAll() {
            return quests;
        }

        public ArrayList<QuestModelClass> getSelected() {
            ArrayList<QuestModelClass> selected = new ArrayList<>();
            for (int i = 0; i < quests.size(); i++) {
                if (quests.get(i).isChecked()) {
                    selected.add(quests.get(i));
                }
            }
            return selected;
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