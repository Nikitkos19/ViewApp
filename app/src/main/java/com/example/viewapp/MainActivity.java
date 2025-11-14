package com.example.viewapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ObjectItem> objectList;
    private SharedPreferences sharedPref;
    private boolean isDarkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = getSharedPreferences("AppSettings", MODE_PRIVATE);
        isDarkMode = sharedPref.getBoolean("DARK_MODE", false);

        // Применяем тему перед super.onCreate()
        if (isDarkMode) {
            setTheme(R.style.Base_Theme_ViewApp); // тёмная тема
        } else {
            setTheme(R.style.Theme_ViewApp); // светлая тема
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Настраиваем RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // помогает с производительностью
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        objectList = new ArrayList<>();
        objectList.add(new ObjectItem(R.drawable.image1, "ВК Музыка", "Музыкальный плеер", "Кайфуйте"));
        objectList.add(new ObjectItem(R.drawable.image2, "ВК Видео", "Видео плеер", "Сдохните"));
        // Добавляй другие объекты при необходимости

        ObjectAdapter adapter = new ObjectAdapter(this, objectList);
        recyclerView.setAdapter(adapter);

        // Кнопка смены темы
        Button switchThemeButton = findViewById(R.id.switchThemeButton);
        switchThemeButton.setOnClickListener(v -> {
            isDarkMode = !isDarkMode;
            sharedPref.edit().putBoolean("DARK_MODE", isDarkMode).apply();

            // Применяем тему через AppCompatDelegate без recreate()
            AppCompatDelegate.setDefaultNightMode(
                    isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES
                            : AppCompatDelegate.MODE_NIGHT_NO
            );
        });
    }
}
