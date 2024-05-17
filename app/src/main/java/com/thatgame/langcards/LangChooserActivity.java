package com.thatgame.langcards;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Objects;

public class LangChooserActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> langs;
    dbConnect db;
    LinearLayout linear;
    int watched;
    int flag;
    String login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_chooser);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new dbConnect(this);

        watched = getIntent().getIntExtra("watched", watched);
        flag = getIntent().getIntExtra("flag", flag);
        login = Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).get("login")).toString();

        linear = (LinearLayout) findViewById(R.id.linear);

        Button plus = (Button) findViewById(R.id.plusButton);
        plus.setOnClickListener(v -> {
            LangDialog langDialog = new LangDialog();
            langDialog.show(getSupportFragmentManager(), "123");
        });

        init();
    }

    public void init() {
        linear.removeAllViews();
        langs = db.getLangs(login);
        for (int i = 0; i < langs.size(); i++) {
            Button b = new Button(this);
            b.setText(langs.get(i));
            b.setOnClickListener(this);
            linear.addView(b);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(LangChooserActivity.this, FlashCardsActivity.class);
        intent.putExtra("lang", ((Button) view).getText().toString());
        intent.putExtra("watched", watched);
        intent.putExtra("flag", flag);
        intent.putExtra("login", login);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.stats) {
            StatsDialog stats = new StatsDialog();
            stats.show(getSupportFragmentManager(), "123");
        }
        if (id == R.id.out) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}