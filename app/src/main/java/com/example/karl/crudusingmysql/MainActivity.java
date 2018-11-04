package com.example.karl.crudusingmysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Create(View v)
    {
        Intent intent = new Intent(this, CreateAct.class);
        this.startActivity(intent);
    }

    public void Read(View v)
    {
        Intent intent = new Intent(this, ReadAct.class);
        this.startActivity(intent);
    }

    public void Update(View v)
    {
        Intent intent = new Intent(this, UpdateAct.class);
        this.startActivity(intent);
    }

    public void Delete(View v)
    {
        Intent intent = new Intent(this, DeleteAct.class);
        this.startActivity(intent);
    }
}
