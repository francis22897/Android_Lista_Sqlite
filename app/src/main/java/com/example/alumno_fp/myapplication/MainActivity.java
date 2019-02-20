package com.example.alumno_fp.myapplication;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView myList;
    ArrayAdapter<String> myAdapter;
    ArrayList<String> places;
    Button btnAdd, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicialize();

        myList.setAdapter(myAdapter);
        myAdapter.clear();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivityForResult(intent,2);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Utils.CODE_OK){
            String message = data.getStringExtra("PLACE");
            if(! message.isEmpty()) {
                places.add(message);
                myAdapter.notifyDataSetChanged();
            }else{
                Toast.makeText(getApplicationContext(), getString(R.string.err_msg_empty_place), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void inicialize(){
        myList = findViewById(R.id.list);
        places = new ArrayList<>();
        myAdapter = new ListAdapter(this, places);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
    }
}
