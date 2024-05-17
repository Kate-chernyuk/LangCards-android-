package com.thatgame.langcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FlashCardsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    dbConnect db;
    String lang;
    List<String> words, translations;
    int count=1;
    int watched, flag;
    String login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);

        db = new dbConnect(this);

        lang = Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).get("lang")).toString();
        watched = getIntent().getIntExtra("watched", watched);
        flag = getIntent().getIntExtra("flag", flag);
        login = Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).get("login")).toString();

        Button plus = (Button) findViewById(R.id.plusButton1);
        plus.setOnClickListener(v -> {
            WordsDialog wordsDialog = new WordsDialog();
            wordsDialog.show(getSupportFragmentManager(), "123");
            initAdapter();
        });

        ImageView goBack = (ImageView) findViewById(R.id.go_back_button);
        goBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LangChooserActivity.class);
            intent.putExtra("watched", watched);
            intent.putExtra("flag", flag);
            intent.putExtra("login", login);
            startActivity(intent);
        });
        initAdapter();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), LangChooserActivity.class);
        intent.putExtra("watched", watched);
        intent.putExtra("flag", flag);
        intent.putExtra("login", login);
        startActivity(intent);
    }

    void initAdapter() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db.createNewTableDict(lang + "_" + login);
        words = db.getWords(lang + "_" + login).stream().limit(count).collect(Collectors.toList());
        translations = db.getTranslations(lang + "_" + login).stream().limit(count).collect(Collectors.toList());
        adapter = new Adapter(this, words, translations, this);
        recyclerView.setAdapter(adapter);
    }
}