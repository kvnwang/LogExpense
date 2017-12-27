package com.example.kevin.assignment2;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


/**
 * Created by Kevin on 11/4/17.
 */

public class ExpenseEntry extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_add);
    }


    public void saveEntry(View view){
        EditText description = findViewById(R.id.descriptioninput);
        String desc = description.getText().toString();
        EditText notes = findViewById(R.id.noteinput);
        String note = notes.getText().toString();

        Intent i= new Intent();
        i.putExtra("getDescription", desc);
        i.putExtra("getNote", note);
        setResult(RESULT_OK, i);
        finish();
    }

    public void cancelEntry(View view){
        Intent goBack= new Intent();
        goBack.putExtra("getDescription", "");
        goBack.putExtra("getNote", "");
        setResult(RESULT_OK, goBack);
        finish();
    }

}
