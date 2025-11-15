package com.example.viewapp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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
            setTheme(R.style.Base_Theme_ViewApp);
        } else {
            setTheme(R.style.Theme_ViewApp);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        objectList = loadObjectsFromRaw();
        for (ObjectItem item : objectList) item.imageResId = getResources().getIdentifier(item.imageResName, "drawable", getPackageName());
        ObjectAdapter adapter = new ObjectAdapter(this, objectList);
        recyclerView.setAdapter(adapter);
        String[] categories = {"Все", "Развлечения", "Государство", "Онлайн-покупки", "Финансы", "Образование"};
        Spinner categorySpinner = findViewById(R.id.categorySpinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                      @Override
                                                      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                          String category = (String) parent.getItemAtPosition(position);
                                                          adapter.filterByCategory(category);
                                                      }

                                                      @Override
                                                      public void onNothingSelected(AdapterView<?> parent) {}
                                                  });
            Button switchThemeButton = findViewById(R.id.switchThemeButton);
        switchThemeButton.setOnClickListener(v ->

            {
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

