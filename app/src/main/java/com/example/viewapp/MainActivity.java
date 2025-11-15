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

        objectList.add(new ObjectItem(R.drawable.image1, "ВК Музыка", "ВК Музыка", "VK Музыка — это стриминговый сервис, где есть миллионы треков, подкасты, аудиокниги и радио. В приложении для музыки можно слушать любимые треки и открывать новые — благодаря сниппетам, плейлистам настроений и рекомендациям от алгоритмов, пользователей, сообществ и редакции. Музыка без интернета: оформите подписку, скачайте песни прямо в приложении и слушайте офлайн."));
        objectList.add(new ObjectItem(R.drawable.image2, "ВК Видео", "ВК Видео", "Смотри кино, мультики, сериалы, ТВ онлайн, спортивные трансляции и фильмы бесплатно на всех устройствах: от смартфона до телевизора. Премьеры и блокбастеры из кинотеатров и онлайн-платформ на твоем девайсе!\n" +
                "\n" +
                "Откройте интертеймент без границ с VK Видео: онлайн-просмотр ТВ, новинки кино и сериалов, познавательные и развлекательные видео, мультики для детей (без интернета), эксклюзивный контент и спортивные трансляции ждут тебя. Ты можешь скачать видео для офлайн просмотра в один клик."));
        objectList.add(new ObjectItem(R.drawable.image3, "Госуслуги", "Госуслуги", "Приложение «Госуслуги» — ваш помощник для взаимодействия с ведомствами и государством\n" +
                "\n" +
                "В приложении можно оплачивать штрафы и госпошлины, подавать заявления в ведомства, хранить личные документы и предъявлять их в бытовых ситуациях, сканировать товары, управлять согласиями на использование личных и биометрических данных и многое другое"));
        objectList.add(new ObjectItem(R.drawable.image4, "Мой Налог", "Мой Налог", "Мой налог – приложение самозанятых, обеспечивающее взаимодействие с налоговыми органами при применении специального налогового режима «Налог на профессиональный доход».\n" +
                "Доход самозанятого облагается по ставкам: 4% в отношении доходов, полученных от физических лиц, и 6% в отношении доходов, полученных от юридических лиц и ИП.\n" +
                "Приложение позволяет самозанятым:\n" +
                "- встать на учет в качестве налогоплательщика налога на профессиональный доход;\n" +
                "- формировать чеки, не используя контрольно-кассовую технику;\n" +
                "- отправлять чеки покупателям;\n" +
                "- уплачивать налог на профессиональный доход;\n" +
                "- отслеживать статистику по доходам;\n" +
                "- получать справки о постановке на учет и о доходах;\n" +
                "- сняться с учета."));
        objectList.add(new ObjectItem(R.drawable.image5, "Steam", "Steam", "Покупайте компьютерные игры, узнавайте последние новости и держите свой аккаунт под надёжной защитой. Просматривайте каталог компьютерных игр в Steam с телефона. Теперь вы не пропустите ни одной скидки!\n"));
        objectList.add(new ObjectItem(R.drawable.image6, "RUTUBE", "RUTUBE", "Видеохостинг RUTUBE — стремительно растущая платформа для любителей и создателей контента. Смотрите видео, трансляции, сериалы, фильмы от крупнейших онлайн-кинотеатров — «Иви», PREMIER, START — и ТВ онлайн. Делитесь собственными видео с миллионами зрителей, развивайте свой канал и зарабатывайте на своём творчестве!\n" +
                "\n" +
                "Смотреть любимые видео, трансляции и прямые эфиры в приложении RUTUBE просто и привычно: оно легко заменит ваш любимый онлайн-кинотеатр. Скачайте его, чтобы смотреть фильмы, когда и где вам удобно. А с подпиской RUTUBE можно скачивать видео, чтобы смотреть их даже без интернета.\n"));
        objectList.add(new ObjectItem(R.drawable.image7, "Т-банк", "Т-банк", "Наши карты решат любые ваши задачи. С дебетовкой Black можно быстро оплачивать счета, переводить деньги без комиссии и получать кэшбэк рублями за всё. Легендарная кредитка Платинум с лимитом до 1 000 000 ₽ подстрахует, если не хватает своих денег. А если нужно накопить, мы подберем подходящий способ это сделать — какой бы ни была ваша цель.\n"));
                objectList.add(new ObjectItem(R.drawable.image8, "Wildberries", "Wildberries","Wildberries — это цифровая платформа, где можно найти почти всё: от пушистых тапочек-единорогов до стиральной машины. Встречи с вами ждут больше 500 миллионов самых разных товаров. 80% заказов привозим уже на следующий день после покупки — не хотим, чтобы вы долго ждали."));
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
