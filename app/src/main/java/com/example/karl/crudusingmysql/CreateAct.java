package com.example.karl.crudusingmysql;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CreateAct extends AppCompatActivity {
    private String lName;
    private String fName;
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
    }

    public void Insert(View v)
    {
        lName = ((EditText) findViewById(R.id.lName)).getText().toString();
        fName = ((EditText) findViewById(R.id.fName)).getText().toString();
        mName = ((EditText) findViewById(R.id.mName)).getText().toString();

        new InsertIntoDatabaseClass().execute("");

        GoToMain(v);
    }

    public class InsertIntoDatabaseClass extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... strings) {
            return InsertIntoDatabase();
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }

    public String InsertIntoDatabase()
    {
        try {
            Class.forName(MySQLConnectionString.driver);
            try{
                Connection con = (Connection) DriverManager.getConnection(MySQLConnectionString.DatabaseConnection);
                try {
                    PreparedStatement command = (PreparedStatement) con.prepareStatement("INSERT INTO person (lName, fName, mName) VALUES (?, ?, ?)");
                    command.setString(1, lName);
                    command.setString(2, fName);
                    command.setString(3, mName);
                    command.executeUpdate();
                    command.close();
                    con.close();
                    return "Record Created!";
                } catch (Exception e) {
                    return e.getMessage();
                }
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
    }

    public void Clear(View v)
    {
        ((EditText) findViewById(R.id.lName)).setText("");
        ((EditText) findViewById(R.id.fName)).setText("");
        ((EditText) findViewById(R.id.mName)).setText("");
    }

    public void GoToMain(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

}
