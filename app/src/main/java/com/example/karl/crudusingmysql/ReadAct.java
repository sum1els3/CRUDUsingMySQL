package com.example.karl.crudusingmysql;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReadAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new LoadData().execute();
    }

    public class LoadData extends AsyncTask <String, String, String>
    {
        @Override
        protected String doInBackground(String... strings) {
            return RetrieveFromDatabase();
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            ((TextView) findViewById(R.id.names)).setText(s);
        }
    }

    public String RetrieveFromDatabase()
    {
        String names = "";
        try {
            Class.forName(MySQLConnectionString.driver);
            try{
                Connection con = (Connection) DriverManager.getConnection(MySQLConnectionString.DatabaseConnection);
                try {
                    PreparedStatement command = (PreparedStatement) con.prepareStatement("SELECT lName, fName, mName FROM person");
                    ResultSet result = command.executeQuery();
                    while(result.next())
                    {
                        names += String.format("%1$s, %2$s %3$s.%4$s", result.getString(1), result.getString(2), result.getString(3), System.lineSeparator());
                    }
                    command.close();
                    con.close();
                    return names;
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
}
