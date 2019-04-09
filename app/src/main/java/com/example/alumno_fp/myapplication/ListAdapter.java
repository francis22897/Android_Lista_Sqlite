package com.example.alumno_fp.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;



public class ListAdapter extends ArrayAdapter<Place> {

    SQLiteDatabase db;

    public ListAdapter(@NonNull Context context, @NonNull ArrayList objects, SQLiteDatabase db) {
        super(context, 0, objects);
        this.db = db;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(null == convertView){
            convertView = inflater.inflate(R.layout.place,parent, false);
        }

        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        TextView id = convertView.findViewById(R.id.detail_id_text);
        TextView place = convertView.findViewById(R.id.detail_place_text);
        TextView comments = convertView.findViewById(R.id.detail_comments_text);
        Place item = getItem(position);

        id.setText(String.valueOf(item.getId()));
        place.setText(item.getPlace());
        comments.setText(item.getComments());
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    db.delete("Places", "id=" + getItem(position).getId() +"", null);
                    remove(getItem(position));
                    notifyDataSetChanged();
                }catch (Exception ex){
                    Log.e("BORRADO", ex.getMessage());
                }


            }
        });

        return convertView;
    }

}
