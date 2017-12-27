package com.example.kevin.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Kevin on 11/4/17.
 */

public class ExpenseTracker extends BaseAdapter {
    List<ExpenseLogEntryData> list = new ArrayList<ExpenseLogEntryData>();
    LayoutInflater inflater;
    Context context;
    TextView description, notes,time;
    Button deleteButton;

    public ExpenseTracker(Context context, List<ExpenseLogEntryData> newList){
        super();
        list=newList;
        this.context=context;
        inflater = LayoutInflater.from(this.context);
    }
    public int getCount() {
        return list.size();
    }

    public Object getItem(int index){
        return list.get(index);
    }

    public long getItemId(int index){
        return index;
    }

    public View getView(final int index, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.expense_entry, null);
        description = view.findViewById(R.id.description);
        notes =  view.findViewById(R.id.notes);
        time =  view.findViewById(R.id.time);
        description.setText(list.get(index).getDescription());
        notes.setText(list.get(index).getNote());
        time.setText(list.get(index).getTime());


        return view; 

    }


}
