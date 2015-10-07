package com.ajscanlan.databaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ajscanlan.databaseapp.database.DatabaseHandler;
import com.ajscanlan.databaseapp.person.Person;

public class AddPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
    }

    public void addPerson(View view) {
        EditText nameField = (EditText) findViewById(R.id.edit_name);
        EditText emailField = (EditText) findViewById(R.id.edit_email);
        EditText numberField = (EditText) findViewById(R.id.edit_phone_number);

        final Person person = new Person();
        person.setName(nameField.getText().toString());
        person.setEmail(emailField.getText().toString());
        person.setPhoneNumber(numberField.getText().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {

                DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
                databaseHandler.addPerson(person);

                setResult(1);
                finish();
            }
        }).start();

    }
}
