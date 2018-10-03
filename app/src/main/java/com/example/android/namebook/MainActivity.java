package com.example.android.namebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private People people;
    private EditText name, phone, email, text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadPeople();

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        text = findViewById(R.id.text);
    }

    public void onSave(View view) {
        String _name = name.getText().toString().trim();
        String _phone = phone.getText().toString().trim();
        String _email = email.getText().toString().trim();
        String _text = text.getText().toString().trim();
        if(name.length() > 0){
            try{
                people.save(new Person(_name, _phone, _email, _text));
                Toast.makeText(this, getResources().getString(R.string.msg_save), Toast.LENGTH_SHORT).show();
                clear();
            } catch(Exception ex) {
                return;
            }
        }
    }

    public void onShow(View view) {
        Intent intent = new Intent(MainActivity.this, ShowActivity.class);
        intent.putExtra("people", people);
        startActivity(intent);
    }

    public void onClear(View view) {
        clear();
    }

    public void clear(){
        name.setText("");
        phone.setText("");
        email.setText("");
        text.setText("");
    }

    private void loadPeople(){
        try{
            people = new People(this);
        } catch(Exception ex) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.addCategory(Intent.CATEGORY_HOME);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(home);
        }
    }
}
