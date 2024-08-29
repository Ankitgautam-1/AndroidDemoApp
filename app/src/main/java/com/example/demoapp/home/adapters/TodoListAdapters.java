package com.example.demoapp.home.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoapp.R;
import com.example.demoapp.utils.model.Todo;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class TodoListAdapters extends BaseAdapter {
     Context context;
     List<Todo> listOfTodo;
     LayoutInflater layoutInflater;
    public TodoListAdapters(Context context, List<Todo> listOfTodo) {
        this.context=context;
        this.listOfTodo = listOfTodo;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listOfTodo.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("TodoListAdapter","getView"+position+" title "+listOfTodo.get(position).title);
       convertView= layoutInflater.inflate(R.layout.todo_item_layout,null);
       TextView textView=(TextView) convertView.findViewById(R.id.todoTitle);
        textView.setText(listOfTodo.get(position).title);
        CheckBox checkBox=(CheckBox) convertView.findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Toast.makeText(context,"Todo for "+listOfTodo.get(position).title+" item is "+isChecked,Toast.LENGTH_SHORT).show();

                Snackbar.make(buttonView.getRootView(),"Todo for "+listOfTodo.get(position).title+" item is "+isChecked,Snackbar.LENGTH_SHORT).show();
            }
        });
       return convertView;
    }
}
