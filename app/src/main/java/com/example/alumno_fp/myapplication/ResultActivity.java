package com.example.alumno_fp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    EditText name, comments;
    Button btnSend;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        inicialize();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("NAME", name.getText().toString());
                intent.putExtra("COMMENTS", comments.getText().toString());
                setResult(Utils.CODE_OK, intent);
                finish();
            }
        });
    }

    private void inicialize(){
        name = findViewById(R.id.place_text);
        comments = findViewById(R.id.comments_text);
        btnSend = findViewById(R.id.btnSend);
    }
}
