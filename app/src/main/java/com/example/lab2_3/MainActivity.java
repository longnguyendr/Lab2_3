package com.example.lab2_3;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd, btnEdit, btnRemove, btnSearch;
    ArrayList<String> todoList = new ArrayList<>();
    ArrayList<String> searchItem = new ArrayList<>();
//    ArrayList<TodoItem> todoList = new ArrayList<>();
    ArrayAdapter todoListAdapter;
    EditText userInput;
    ListView todoListView;
    TodoItem mTodoItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupLayout();
        setupListViewAdapter();
        AddButtonEvent();
        EditButtonEvent();
        RemoveButtonEvent();
        SearchButtonEvent();
    }

    public void setupLayout () {
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout layout2 = new LinearLayout(this);
        layout2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setGravity(Gravity.CENTER);

        userInput = new EditText(this);
        todoListView = new ListView(this);
        userInput.setHint("Please enter here");

        btnAdd = new Button(this);
        btnAdd.setText("Add");
        btnEdit = new Button(this);
        btnEdit.setText("Edit");
        btnRemove = new Button(this);
        btnRemove.setText("Remove");
        btnSearch = new Button(this);
        btnSearch.setText("Search");

        layout2.addView(btnAdd);
        layout2.addView(btnEdit);
        layout2.addView(btnRemove);
        layout2.addView(btnSearch);

        layout.addView(userInput);
        layout.addView(layout2);
        layout.addView(todoListView);
        this.setContentView(layout);
    }

    protected void setupListViewAdapter () {
//        todoList.add(new TodoItem("Breakfast") );
//        todoList.add(new TodoItem("Breakfast 2"));
//        todoListAdapter = new ArrayAdapter<TodoItem>(MainActivity.this, android.R.layout.simple_list_item_1 , todoList);
        todoList.add("Breakfast");
        todoList.add("Breakfast 2");
        todoListAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1 , todoList);
        todoListView.setAdapter(todoListAdapter);

    }

     protected void AddButtonEvent () {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textInput = userInput.getText().toString().trim();
                if(checkValid(textInput)) {
                    todoList.add(textInput);
                    userInput.setText("");
                    todoListAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    protected void EditButtonEvent () {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textInput = userInput.getText().toString().trim();
                if(checkValid(textInput)) {
                    for (int i = 0 ; i < todoList.size();i++) {
                        if(todoList.get(i).toLowerCase().equals(textInput.toLowerCase())) {
                            todoList.set(i, textInput);
                            userInput.setText("");
                            todoListAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }
    protected void RemoveButtonEvent () {
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textInput = userInput.getText().toString().trim();
                if(checkValid(textInput)) {
                    for(int i = 0 ; i < todoList.size(); i++) {
                        if(todoList.get(i).toLowerCase().equals(textInput.toLowerCase())) {
                            todoList.remove(i);
                            userInput.setText("");
                            todoListAdapter.notifyDataSetChanged();
                        }
                    }

                }
            }
        });
    }
    protected void SearchButtonEvent () {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textInput = userInput.getText().toString().trim();
                if(checkValid(textInput)) {
                    for(int i = 0 ; i < todoList.size(); i++) {
                        if(todoList.get(i).toLowerCase().contains(textInput.toLowerCase())) {
                            userInput.setText("");
                            todoListAdapter.notifyDataSetChanged();
                        }
                    }

                }
            }
        });
    }
    protected boolean checkValid (String textInput) {
        return (textInput.length() > 0 && textInput != null);
    }

}
