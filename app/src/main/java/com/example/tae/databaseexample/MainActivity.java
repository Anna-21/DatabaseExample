package com.example.tae.databaseexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;


public class MainActivity extends AppCompatActivity {

    private Button saveButton, loadButton;
    private EditText nameEdotText, emailEditText;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveButton=findViewById(R.id.saveButton);
        loadButton=findViewById(R.id.loadButton);

        nameEdotText = findViewById(R.id.editText);
        emailEditText=findViewById(R.id.editText2);

        Realm.init(this);

        realm = Realm.getDefaultInstance();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserToDatabase(nameEdotText.getText().toString(), emailEditText.getText().toString());
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user= findUserByName(nameEdotText.getText().toString());
                emailEditText.setText(user.getEmail());
            }
        });
    }


    public boolean saveUserToDatabase (final String name, final String email){
        realm.beginTransaction();
        User user = realm.createObject (User.class);
        user.setName(name);
        user.setEmail(email);
        realm.commitTransaction();
         return true;
        }
    public User findUserByName(final String name) {
        User user = realm.where(User.class).
                equalTo("name", name).findFirst();
        return user;
    }

    }

