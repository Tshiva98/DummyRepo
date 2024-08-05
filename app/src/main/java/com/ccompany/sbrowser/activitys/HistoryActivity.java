package com.ccompany.sbrowser.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.ccompany.sbrowser.R;
import com.ccompany.sbrowser.activitys.adapter.AdapterHistory;
import com.ccompany.sbrowser.activitys.database.HistoryDao;
import com.ccompany.sbrowser.activitys.database.HistoryDatabase;
import com.ccompany.sbrowser.activitys.database.HistoryEntity;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView rv_history;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);



        HistoryDatabase database = Room.databaseBuilder(getApplicationContext(),HistoryDatabase.class,"historyURL")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        HistoryDao historyDao = database.historyDao();

        rv_history = findViewById(R.id.rv_history);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv_history.setLayoutManager(llm);

        List<HistoryEntity> historyList = historyDao.getAllFavText();

        AdapterHistory adapterHistory = new AdapterHistory(historyList,getApplicationContext());

        llm.setStackFromEnd(true);
        llm.setReverseLayout(true);

        rv_history.setAdapter(adapterHistory);



    }
}