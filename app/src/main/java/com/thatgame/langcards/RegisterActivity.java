package com.thatgame.langcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class RegisterActivity extends AppCompatActivity {

    dbConnect db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText newUsername = (EditText) findViewById(R.id.newUsername);
        EditText newPassword = (EditText) findViewById(R.id.newPassword);
        MaterialButton registerBtn = (MaterialButton) findViewById(R.id.singUpButton);

        db = new dbConnect(this);

        registerBtn.setOnClickListener(v -> {
            String username = newUsername.getText().toString();
            String password = newPassword.getText().toString();
            if (db.checkUsername(username)) {
                db.addUser(username, password);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(RegisterActivity.this, "Такой логин уже существует", Toast.LENGTH_LONG).show();
            }
        });
    }


}