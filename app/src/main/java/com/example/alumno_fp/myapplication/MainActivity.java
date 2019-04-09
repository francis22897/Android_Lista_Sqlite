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
    PlacesSQLiteHelper pdbh;
    protected SQLiteDatabase db;
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
                intent.putExtra("ID", String.valueOf(place.getId()));
                intent.putExtra("NEW_PLACE", place.getPlace());
                intent.putExtra("NEW_COMMENTS", place.getComments());
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

            updateBD(id, place, comments, lastUpdatePosition);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Utils.CODE_OK) {
            String name = data.getStringExtra("NAME");
            String comments = data.getStringExtra("COMMENTS");
           if (!name.isEmpty() && !comments.isEmpty()) {

                insertDB(name, comments);

            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.err_msg_empty_place), Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void inicialize() {
        myList = findViewById(R.id.list);
        btnAdd = findViewById(R.id.btnAdd);
        places = new Places();
        pdbh = new PlacesSQLiteHelper(this, "DBPlaces", null,1);
        db = pdbh.getWritableDatabase();
        readBD();
    }

    private void insertDB(String name, String comments){
        try{
            ContentValues newRecord = new ContentValues();

            newRecord.put("name", name);
            newRecord.put("comments", comments);
            db.insert("Places", null, newRecord);

            int lastId = getLastId();

            if(lastId != -1)
                places.addPlace(new Place(lastId, name, comments));
            else
                throw new Exception("No se pudo obtener el id");

            myAdapter.notifyDataSetChanged();

        }catch (Exception ex){
            Log.e("INSERCION", ex.getMessage());
        }
    }

    private void readBD(){
        try{

            Cursor c = db.rawQuery("SELECT id, name, comments FROM Places", null);

            if(c.moveToFirst()){
                do{
                    Place place = new Place(Integer.parseInt(c.getString(0)), c.getString(1), c.getString(2));
                    places.addPlace(place);
                }while(c.moveToNext());
            }

        }catch(Exception ex){
            Log.e("LECTURA", ex.getMessage());
        }

        myAdapter = new ListAdapter(this, places.getPlacesList(), db);
    }

    private void updateBD(int id, String place, String comments, int position){
        try{

            ContentValues values = new ContentValues();
            values.put("name", place);
            values.put("comments", comments);
            db.update("Places", values, "id=" + id, null);

            Place nPlace = (Place) places.getPlacesList().get(position);
            nPlace.setPlace(place);
            nPlace.setComments(comments);
            myAdapter.notifyDataSetChanged();

        }catch (Exception ex){
            Log.e("ACTUALIZADO", ex.getMessage());
        }


    }

    private int getLastId(){
        Cursor c = db.rawQuery("SELECT id FROM Places", null);

        int id = -1;

        if(c.moveToLast()){
            try{
                id = Integer.parseInt(c.getString(0));

            }catch(Exception ex){

            }
        }
        return id;
    }

}
