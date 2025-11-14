package com.example.viewapp;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ObjectItem> objectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize your object list
        objectList = new ArrayList<>();
        objectList.add(new ObjectItem(R.drawable.image1, "ВК Музыка", "Музыкальный плеер", "Кайфуйте"));
        objectList.add(new ObjectItem(R.drawable.image2, "ВК Видео", "Видео плеер", "Сдохните"));
        // Add more objects as needed

        ObjectAdapter adapter = new ObjectAdapter(this, objectList);
        recyclerView.setAdapter(adapter);
    }
}