package com.example.alumno_fp.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UtilsDB {

    private Context context;
    private PlacesSQLiteHelper psqlh;
    private SQLiteDatabase db;

    public UtilsDB(Context context) {
        this.context = context;
        psqlh = new PlacesSQLiteHelper(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION);

        db = psqlh.getWritableDatabase();
    }

    protected boolean insertDB(String name, String comments){
        try{
            ContentValues newRecord = new ContentValues();

            newRecord.put("name", name);
            newRecord.put("comments", comments);
            db.insert("Places", null, newRecord);

            return true;

        }catch (Exception ex){
            Log.e("INSERCION", ex.getMessage());
            return false;
        }
    }

    protected Places readDB(Places places){
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

        return places;
    }

    protected boolean updateDB(int id, String place, String comments){
        try{

            ContentValues values = new ContentValues();
            values.put("name", place);
            values.put("comments", comments);
            db.update("Places", values, "id=" + id, null);

            return true;

        }catch (Exception ex){
            Log.e("ACTUALIZADO", ex.getMessage());
            return false;
        }
    }

    protected boolean deleteDB(int id){
        try{
            db.delete("Places", "id=" + id +"", null);
            return true;
        }catch (Exception ex){
            Log.e("BORRADO", ex.getMessage());
            return false;
        }
    }

    protected int getLastId(){
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
