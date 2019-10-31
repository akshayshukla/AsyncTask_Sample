package com.example.akshay.demohttp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Akshay on 24-04-2017.
 */

public class UserAdapter extends BaseAdapter {
    Context context;
    int layoutResourceId;
    ArrayList<User> dataSet;

    public UserAdapter(Context context, ArrayList<User> dataSet) {
        //super(context, layoutResourceId, dataSet);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.dataSet = dataSet;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dataSet.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(R.layout.list_item_row, parent, false);
        }

        ImageView imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
        TextView name = (TextView) row.findViewById(R.id.uname);
        TextView email = (TextView) row.findViewById(R.id.uemail);
        TextView usergroup_title = (TextView) row.findViewById(R.id.uusergroup);

        name.setText(dataSet.get(position).getName());
        imgIcon.setImageResource(dataSet.get(position).getIcon());
        email.setText(dataSet.get(position).getEmail());
        usergroup_title.setText(dataSet.get(position).getUsergroup_title());

        return row;
    }
}
