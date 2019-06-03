package com.example.mycontactapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editNumber;
    EditText editSpaghetti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_Name);
        editNumber = findViewById(R.id.editText_Number);
        editSpaghetti = findViewById(R.id.editText_Spaghetti);
        myDb = new DatabaseHelper(this);
        Log.d( "MyContactApp",  "MainActivity: instantiated DatabaseHelper");

    }

    public void addData (View view){

        Boolean isInserted = myDb.insertData(editName.getText().toString(), editNumber.getText().toString(), editSpaghetti.getText().toString());

        if(isInserted){
            Toast.makeText(MainActivity.this, "Success - Contact Inserted", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(MainActivity.this, "Failed - Contact Not Inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void viewData (View view){
        Log.d( "MyContactApp",  "MainActivity: viewData called");
        Cursor res = myDb.getAllData();

        Log.d( "MyContactApp",  "MainActivity: Cursor instantiated");

        if(res.getCount() == 0){
            showMessage("Error", "no data found in database");
        }

        Log.d( "MyContactApp",  "MainActivity: Checked empty");

        StringBuffer buffer = new StringBuffer();

        Log.d( "MyContactApp",  "MainActivity: Buffer instantiated");

        buffer.append("ID: " + res.getString(0) + "\n");

        Log.d( "MyContactApp",  "MainActivity: Buffer appended");

        Log.d( "MyContactApp",  "MainActivity: showing data");

        showMessage("Data", buffer.toString());
    }

    public void showMessage(String title, String message){
        Log.d( "MyContactApp",  "MainActivity: showing message");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void search (View view){

    }
}
