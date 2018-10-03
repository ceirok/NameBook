package com.example.android.namebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowActivity extends AppCompatActivity {

    private People people;
    private ListView view;
    public ArrayAdapter<Person> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Intent intent = getIntent();
        people = (People) intent.getSerializableExtra("people");
        view = findViewById(R.id.listNames);
        registerForContextMenu(view);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        new MenuInflater(this).inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Person person = people.getPerson(menuInfo.position);
        switch (item.getItemId()){
            case R.id.showDetails:
                Intent intent = new Intent(ShowActivity.this, DetailsActivity.class);
                intent.putExtra("person", person);
                startActivity(intent);
                return true;
            case R.id.removeMenu:
                
        }
        return super.onContextItemSelected(item);
    }
}
