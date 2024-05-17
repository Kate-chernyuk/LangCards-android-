package com.thatgame.langcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class ChangePasswordActivity extends AppCompatActivity {

    dbConnect db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        EditText oldUsername = (EditText) findViewById(R.id.oldUsername);
        EditText newestPassword = (EditText) findViewById(R.id.newestPassword);
        MaterialButton registerBtn = (MaterialButton) findViewById(R.id.resetButton);

        db = new dbConnect(this);

        registerBtn.setOnClickListener(v -> {
            String username = oldUsername.getText().toString();
            String password = newestPassword.getText().toString();
            if (!db.checkUsername(username)) {
                db.updatePassword(username, password);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(ChangePasswordActivity.this, "Такого логина не существует", Toast.LENGTH_LONG).show();
            }
        });
    }
}