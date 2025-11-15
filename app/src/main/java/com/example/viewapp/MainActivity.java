package com.example.viewapp;

import android.content.Intent;
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

        // Проверяем, проходил ли пользователь онбординг
        if (!isOnboardingCompleted()) {
            startActivity(new Intent(this, OnboardingActivity.class));
            finish();
            return;
        }

        // Настройки темы
        sharedPref = getSharedPreferences("AppSettings", MODE_PRIVATE);
        isDarkMode = sharedPref.getBoolean("DARK_MODE", false);

        if (isDarkMode) {
            setTheme(R.style.Base_Theme_ViewApp); // тёмная тема
        } else {
            setTheme(R.style.Theme_ViewApp); // светлая тема
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Настраиваем RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        objectList = new ArrayList<>();

        objectList.add(new ObjectItem(R.drawable.image1, "ВК Музыка", "ВК Музыка", "Кайфуйте"));
        objectList.add(new ObjectItem(R.drawable.image2, "ВК Видео", "ВК Видео", "Сдохните"));

        ObjectAdapter adapter = new ObjectAdapter(this, objectList);
        recyclerView.setAdapter(adapter);

        // Кнопка смены темы
        Button switchThemeButton = findViewById(R.id.switchThemeButton);
        switchThemeButton.setOnClickListener(v -> {
            isDarkMode = !isDarkMode;
            sharedPref.edit().putBoolean("DARK_MODE", isDarkMode).apply();

            AppCompatDelegate.setDefaultNightMode(
                    isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES
                            : AppCompatDelegate.MODE_NIGHT_NO
            );
        });
    }

    private boolean isOnboardingCompleted() {
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        return prefs.getBoolean("onboarding_completed", false);
    }
}
