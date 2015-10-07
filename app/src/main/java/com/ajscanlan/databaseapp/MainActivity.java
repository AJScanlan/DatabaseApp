package com.ajscanlan.databaseapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ajscanlan.databaseapp.database.DatabaseHandler;
import com.ajscanlan.databaseapp.person.Person;

import java.util.List;

public class MainActivity extends ListActivity {

    private DatabaseHandler mDatabaseHandler;
    private List<Person> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseHandler = new DatabaseHandler(getApplicationContext());
        initListView();
    }

    private void initListView() {
        people = mDatabaseHandler.getAllPeople();

        ArrayAdapter<Person> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.textview_layout,
                people);

        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        ((ArrayAdapter) l.getAdapter()).notifyDataSetChanged();
        Person person = people.get(position);
        Intent intent = new Intent(this, PersonActivity.class);

        intent.putExtra("PERSON_NAME", person.getName());
        intent.putExtra("PERSON_EMAIL", person.getEmail());
        intent.putExtra("PERSON_PHONE_NUMBER", person.getPhoneNumber());
        intent.putExtra("PERSON_ID", person.getID());

        startActivityForResult(intent, 1);
    }

    public void onAddClicked(View v){
        Intent intent = new Intent(this, AddPersonActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == 1) initListView();
    }
}
