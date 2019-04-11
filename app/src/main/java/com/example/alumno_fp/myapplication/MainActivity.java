package com.example.alumno_fp.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    ListView myList;
    ArrayAdapter<Place> myAdapter;
    Places places;
    Button btnAdd;
    UtilsDB db;
    int lastUpdatePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicialize();
        myList.setAdapter(myAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Place place = (Place) adapter.getItemAtPosition(position);
                lastUpdatePosition = position;
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("DETAIL_ID", String.valueOf(place.getId()));
                intent.putExtra("DETAIL_PLACE", place.getPlace());
                intent.putExtra("DETAIL_COMMENTS", place.getComments());
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivityForResult(intent,2);
            }
        });

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            int id = Integer.parseInt(intent.getStringExtra("ID"));
            String place = intent.getStringExtra("PLACE");
            String comments = intent.getStringExtra("COMMENTS");

            if(db.updateDB(id,place, comments)){
                Place nPlace = (Place) places.getPlacesList().get(lastUpdatePosition);
                nPlace.setPlace(place);
                nPlace.setComments(comments);
                myAdapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Utils.CODE_OK) {
            String name = data.getStringExtra("NAME");
            String comments = data.getStringExtra("COMMENTS");
           if (!name.isEmpty() && !comments.isEmpty()) {

                if(db.insertDB(name, comments)){
                    int lastId = db.getLastId();

                    if(lastId != -1) {
                        places.addPlace(new Place(lastId, name, comments));
                        myAdapter.notifyDataSetChanged();
                    }
                    else
                        Log.e("INSERCION", "No se pudo obtener el id");
                }

            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.err_msg_empty_place), Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void inicialize() {
        myList = findViewById(R.id.list);
        btnAdd = findViewById(R.id.btnAdd);
        places = new Places();

        db = new UtilsDB(this);
        places = db.readDB(places);

        myAdapter = new ListAdapter(this, places.getPlacesList());
        myList.setAdapter(myAdapter);
    }


}
