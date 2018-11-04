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

public class UpdateAct extends AppCompatActivity {
    private String oldlName;
    private String oldfName;
    private String oldmName;
    private String lName;
    private String fName;
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //oldlName = ((EditText) findViewById(R.id.lName)).getText().toString();
        //oldfName = ((EditText) findViewById(R.id.fName)).getText().toString();
        //oldmName = ((EditText) findViewById(R.id.mName)).getText().toString();
    }

    public void UpdateRecord(View v)
    {
        //lName = ((EditText) findViewById(R.id.lName)).getText().toString();
        //fName = ((EditText) findViewById(R.id.fName)).getText().toString();
        //mName = ((EditText) findViewById(R.id.mName)).getText().toString();

        new UpdateFromDatabaseClass().execute("");
        GoToMain(v);
    }

    public class UpdateFromDatabaseClass extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... strings) {
            return UpdateFromDatabase();
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }

    public String UpdateFromDatabase()
    {
        try {
            Class.forName(MySQLConnectionString.driver);
            try{
                Connection con = (Connection) DriverManager.getConnection(MySQLConnectionString.DatabaseConnection);
                try {
                    PreparedStatement command = (PreparedStatement) con.prepareStatement("UPDATE SET lName = ?, fName = ?, mName = ? FROM person WHERE lName = ? AND fName = ? AND mName = ?");
                    command.setString(1, lName);
                    command.setString(2, fName);
                    command.setString(3, mName);
                    command.setString(4, oldlName);
                    command.setString(5, oldfName);
                    command.setString(6, oldmName);
                    command.executeUpdate();
                    command.close();
                    con.close();
                    return "Record Updated!";
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

    public void GoToMain(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
}
