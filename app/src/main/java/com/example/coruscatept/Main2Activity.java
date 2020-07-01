package com.example.coruscatept;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView txtName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtName=findViewById(R.id.txtName);
        Intent ii=getIntent();
        String name=ii.getExtras().getString("name");
        txtName.setText(name);
    }
}
