package com.example.alumno_fp.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;



public class ListAdapter extends ArrayAdapter<String> {
    public ListAdapter(@NonNull Context context, @NonNull ArrayList objects) {
        super(context, 0, objects);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(null == convertView){
            convertView = inflater.inflate(R.layout.place,parent, false);
        }

        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        TextView place = convertView.findViewById(R.id.place);
        String item = getItem(position);
        place.setText(item);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(getItem(position));
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

}
