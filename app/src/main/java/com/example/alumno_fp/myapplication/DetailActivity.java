package com.example.alumno_fp.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    TextView id;
    EditText place, comments;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        inicialize();

        Intent intent = getIntent();

        if(intent.getExtras() != null){
            id.setText(intent.getStringExtra("ID"));
            place.setText(intent.getStringExtra("PLACE"));
            comments.setText(intent.getStringExtra("COMMENTS"));
        }
    }

    private void inicialize(){
        id = findViewById(R.id.detailActivity_id_text);
        place = findViewById(R.id.detailActivity_place_text);
        comments = findViewById(R.id.detailActivity_comments_text);
        btnUpdate = findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                intent.putExtra("ID", id.getText().toString());
                intent.putExtra("PLACE", place.getText().toString());
                intent.putExtra("COMMENTS", comments.getText().toString());

                startActivity(intent);
            }
        });
    }
}
