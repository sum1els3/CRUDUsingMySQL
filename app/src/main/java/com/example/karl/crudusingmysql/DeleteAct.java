package com.example.karl.crudusingmysql;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DeleteAct extends AppCompatActivity {
    public Person persons;
    private ArrayList<Person> people = new ArrayList<>();
    private String lName;
    private String fName;
    private String mName;
    private Spinner nameSpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        nameSpin = ((Spinner) findViewById(R.id.namess));
        nameSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String name = nameSpin.getSelectedItem().toString();
                for (Person person : people) {
                    if(name.contains(person.lName) && name.contains(person.fName) && name.contains(person.lName))
                    {
                        persons = person;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        new RetrieveFromDatabaseClass().execute("");
    }

    public class RetrieveFromDatabaseClass extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... strings) {
            return RetrieveFromDatabase();
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            ArrayList<String> names = new ArrayList<>();
            for (Person person : people) {
                names.add(person.GetName());
            }
            ArrayAdapter adapter = new ArrayAdapter(DeleteAct.this, R.layout.support_simple_spinner_dropdown_item, names);
            nameSpin.setAdapter(null);
            nameSpin.setAdapter(adapter);
        }
    }

    public String RetrieveFromDatabase()
    {
        try {
            Class.forName(MySQLConnectionString.driver);
            try{
                Connection con = (Connection) DriverManager.getConnection(MySQLConnectionString.DatabaseConnection);
                try {
                    PreparedStatement command = (PreparedStatement) con.prepareStatement("SELECT personID, lName, fName, mName FROM person");
                    ResultSet result = command.executeQuery();
                    while(result.next())
                    {
                        people.add(new Person(result.getInt(1), result.getString(2), result.getString(3), result.getString(4)));
                    }
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


    public void DeleteRecord(View v)
    {
        new DeleteToDatabaseClass().execute("");
        GoToMain(v);
    }

    public class DeleteToDatabaseClass extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... strings) {
            return DeleteFromDatabase();
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }

    public String DeleteFromDatabase()
    {
        try {
            Class.forName(MySQLConnectionString.driver);
            try{
                Connection con = (Connection) DriverManager.getConnection(MySQLConnectionString.DatabaseConnection);
                try {
                    PreparedStatement command = (PreparedStatement) con.prepareStatement("DELETE FROM person WHERE personID = ?");
                    command.setString(1, persons.GetID() + "");
                    command.executeUpdate();
                    command.close();
                    con.close();
                    return "Record Deleted!";
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
