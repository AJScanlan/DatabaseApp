package com.ajscanlan.databaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ajscanlan.databaseapp.database.DatabaseHandler;
import com.ajscanlan.databaseapp.person.Person;

public class PersonActivity extends AppCompatActivity {
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        person = new Person();
        Intent intent = getIntent();

        person.setName(intent.getStringExtra("PERSON_NAME"));
        person.setEmail(intent.getStringExtra("PERSON_EMAIL"));
        person.setPhoneNumber(intent.getStringExtra("PERSON_PHONE_NUMBER"));
        person.setID(intent.getIntExtra("PERSON_ID", -1));

        TextView nameTextView = (TextView) findViewById(R.id.display_name_field);
        TextView emailTextView = (TextView) findViewById(R.id.display_mail_field);
        TextView phoneTextView = (TextView) findViewById(R.id.display_phone_field);

        nameTextView.setText(person.getName());
        emailTextView.setText(person.getEmail());
        phoneTextView.setText(person.getPhoneNumber());
    }

    public void onRemoveClick(View view) {
        DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
        databaseHandler.removePerson(person.getID());

        setResult(1);
        finish();
    }
}
