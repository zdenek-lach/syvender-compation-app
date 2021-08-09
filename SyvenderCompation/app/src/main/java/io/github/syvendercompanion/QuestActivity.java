package io.github.syvendercompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class QuestActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArrayList<QuestModelClass> quests = new ArrayList<>();
    private MultiAdapter adapter;
    Button saveButton;
    Button loadButton;


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
        saveButton = findViewById(R.id.quest_recyclerview_save_button);
        loadButton = findViewById(R.id.quest_recyclerview_load_button);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new MultiAdapter(this, quests);
        recyclerView.setAdapter(adapter);
        CreateListOfData();
//        CreateListOfMockData();

//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (adapter.getSelected().size() > 0) {
//                    StringBuilder stringBuilder = new StringBuilder();
//                    for (int i = 0; i < adapter.getSelected().size(); i++) {
//                        stringBuilder.append(adapter.getSelected().get(i).getName());
//                        stringBuilder.append("\n");
//                    }
//                    ShowToast(stringBuilder.toString().trim());
//
//                } else
//                    ShowToast("No Selection");
//
//            }
//        });

        saveButton.setOnClickListener(v -> {
            ArrayList<QuestModelClass> adapterAll = adapter.getAll();
            Context context = QuestActivity.this.recyclerView.getContext();
            writeToFile(ConvertQuestToStringForFile(adapterAll), context);
        });

        loadButton.setOnClickListener(v -> {
            Context context = QuestActivity.this.recyclerView.getContext();
            ArrayList<QuestModelClass> loaded = ConvertStringFromFileToQuest(readFromFile(context));

            for (int i = 0; i < loaded.size(); i++) {
                System.out.println("Load button has loaded this: " + loaded.get(i));
            }
            FillListOfDataFromFile(loaded);

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
            if (i == 0) {
                quest.setChecked(true);
            }

            quests.add(quest);
        }
        adapter.setQuests(quests);
    }

    private void writeToFile(String data, Context context) {
        try {
            data = chop(data);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("QuestSave.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            System.out.println("WRITE TO FILE IS SAVING THIS: " + data);
            outputStreamWriter.close();
            ShowToast("Saving successful");
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String chop(String data) {
        return data.substring(0, data.length() - 1);
    }


    private String readFromFile(Context context) {
        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("QuestSave.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("quest activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("quest activity", "Can not read file: " + e.toString());
        }
        ShowToast("Loading successful");
        return ret;
    }


    private String ConvertQuestToStringForFile(ArrayList<QuestModelClass> checkedQuestList) {
        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < checkedQuestList.size(); i++) {
            ret.append(checkedQuestList.get(i).getName());
            ret.append("~");
            ret.append(checkedQuestList.get(i).isChecked());
            ret.append(",");
            System.out.println("ConvertQuestToStringForFile has this data:"+ret.toString());
        }
        return ret.toString();

    }

    private ArrayList<QuestModelClass> ConvertStringFromFileToQuest(String listOfLoadedQuests) {
        ArrayList<QuestModelClass> ret = new ArrayList<>();
        listOfLoadedQuests = listOfLoadedQuests.trim();

        String[] allQuests = listOfLoadedQuests.split(",");

        String[] selectedQuest;
        for (String quest : allQuests) {
            System.out.println("Check log for foreach in method 'ConvertStringFromFileToQuest'. Quest value is: " + quest);
            selectedQuest = quest.split("~");
            ret.add(new QuestModelClass(selectedQuest[0], selectedQuest[1]));
        }

        return ret;
    }


    private void CreateListOfData() {
        quests = new ArrayList<>();
        String[] questList = getResources().getStringArray(R.array.questListArray);
        for (String value : questList
        ) {
            value = value.trim();
            System.out.println("QUEST LIST VALUE: " + "'" + value + "'");
        }
        for (String s : questList) {
            QuestModelClass quest = new QuestModelClass();
            quest.setName(s);

            quests.add(quest);
        }
        adapter.setQuests(quests);
    }

    private void FillListOfDataFromFile(ArrayList<QuestModelClass> questList) {
        quests = new ArrayList<>();

        quests.addAll(questList);

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

        public QuestModelClass(String name, String isCheckedAsS) {
            this.name = name;
            this.isChecked = Boolean.parseBoolean(isCheckedAsS.trim());
//            System.out.println("QuestModelClass received: '" + isCheckedAsS + "'");
//            System.out.println("QuestModelClass saved: " + this.isChecked);
        }

        public QuestModelClass() {

        }

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

        @Override
        public String toString() {
            return getName() + "|" + isChecked;
        }
    }
}