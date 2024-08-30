package com.example.demoapp.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demoapp.R;
import com.example.demoapp.utils.model.ProductListModel;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    ProductListModel productList;
    Context context;
    LayoutInflater layoutInflater;

    public ProductAdapter(Context context, ProductListModel productList) {
        this.context = context;
        this.productList = productList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return productList.products.size();
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
        convertView= layoutInflater.inflate(R.layout.todo_item_layout,null);
        TextView textView=(TextView) convertView.findViewById(R.id.todoTitle);
        textView.setText(productList.products.get(position).getTitle() );
        return convertView;
    }
}
