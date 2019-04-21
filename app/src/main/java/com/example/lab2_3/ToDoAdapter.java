package com.example.lab2_3;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ToDoAdapter extends ArrayAdapter<TodoItem>{
    private Context mContext;
    private ArrayList<TodoItem> mToDoList;
    public ToDoAdapter(@NonNull Context context, ArrayList<TodoItem> data ) {
        super(context,0, data);
        mContext = context;
        mToDoList = data;
    }

}
