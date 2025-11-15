package com.example.viewapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ObjectItem> objectList;
    private SharedPreferences sharedPref;
    private boolean isDarkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (!isOnboardingCompleted()) {
            startActivity(new Intent(this, OnboardingActivity.class));
            finish();
            return;
        }

        sharedPref = getSharedPreferences("AppSettings", MODE_PRIVATE);
        isDarkMode = sharedPref.getBoolean("DARK_MODE", false);

        if (isDarkMode) {
            setTheme(R.style.Base_Theme_ViewApp); // тёмная тема
        } else {
            setTheme(R.style.Theme_ViewApp); // светлая тема
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        objectList = loadObjectsFromRaw();
        for (ObjectItem item : objectList) {
            int resId = getResources().getIdentifier(item.imageResName, "drawable", getPackageName());
            item.imageResId = resId;
        }
        ObjectAdapter adapter = new ObjectAdapter(this, objectList);
        recyclerView.setAdapter(adapter);

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
    private List<ObjectItem> loadObjectsFromRaw() {
        List<ObjectItem> list = new ArrayList<>();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.items);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Type listType = new TypeToken<ArrayList<ObjectItem>>() {}.getType();
            list = new Gson().fromJson(reader, listType);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
