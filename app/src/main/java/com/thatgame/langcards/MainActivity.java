package com.thatgame.langcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    dbConnect db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        EditText usrname = (EditText) findViewById(R.id.username);
        EditText pssword = (EditText) findViewById(R.id.password);

        db = new dbConnect(this);

        MaterialButton loginBtn = (MaterialButton) findViewById(R.id.singInButton);

        loginBtn.setOnClickListener(view -> {
            String username = usrname.getText().toString();
            String password = pssword.getText().toString();
            if (db.checkUser(username, password)) {
                Intent intent = new Intent(getApplicationContext(), LangChooserActivity.class);
                db.createNewTableDict("Английский_" + username);
                intent.putExtra("watched", 0);
                intent.putExtra("flag", 1);
                intent.putExtra("login", username);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Неверный логин или пароль", Toast.LENGTH_LONG).show();
            }
        });

        TextView noReg = (TextView) findViewById(R.id.toReg);
        noReg.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        });

        TextView forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
            startActivity(intent);
        });

        TextView withoutReg = (TextView) findViewById(R.id.noReg);
        withoutReg.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LangChooserActivity.class);
            db.cleanUnregDict();
            // db.createNewTableDict("Английский_unreg");
            intent.putExtra("watched", 0);
            intent.putExtra("flag", 0);
            intent.putExtra("login", "unreg");
            startActivity(intent);
        });
    }
}