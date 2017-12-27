package com.example.kevin.assignment2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    private static final int RESULT_CODE = 1;
    private static final int RESULT_MENU=1;
    ListView lview=null;
    DBHelper db;
    Context context = MainActivity.this;
    SimpleCursorAdapter adapter=null;
    Cursor cursor;
    static final String [] from=new String[] { "description", "note", "time"};
    static final int [] to= new int[]{R.id.description, R.id.notes, R.id.time};


    protected void onCreate(Bundle savedInstanceState) {
        db=new DBHelper(context);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cursor=db.getAllData();
        showList();

    }

    protected void showList() {
        lview = findViewById(R.id.elements);
        cursor = db.getAllData();
        adapter = new SimpleCursorAdapter(context, R.layout.expense_entry, cursor, from, to, 0) {
            @Override
            public void bindView(final View view, Context context, Cursor cursor) {
                super.bindView(view, context, cursor);
                Button btnRemove = view.findViewById(R.id.delete);
                final int id_value = cursor.getInt(cursor.getColumnIndex("_id"));
                btnRemove.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        db.deleteExpense(id_value);
                        Cursor c = db.getAllData();
                        adapter.swapCursor(c);
                        notifyDataSetChanged();
                        lview.setAdapter(adapter);
                    }
                });
            }
        };
        lview.setAdapter(adapter);


    }





    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_CODE) {
            if (resultCode == RESULT_OK) {
                String description = data.getStringExtra("getDescription");
                String note = data.getStringExtra("getNote");
                if(!description.equals("") && !note.equals("")) {
                    ExpenseLogEntryData e = new ExpenseLogEntryData(description, note);
                    db.insert(e);

                }
                showList();
                super.onActivityResult(requestCode, resultCode, data);

            }
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_button, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent1 = new Intent(this,ExpenseEntry.class);
            startActivityForResult(intent1, RESULT_MENU);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}
