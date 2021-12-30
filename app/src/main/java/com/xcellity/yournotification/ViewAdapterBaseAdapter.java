package com.xcellity.yournotification;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewAdapterBaseAdapter extends BaseAdapter {

    Context context;
    List<NotiModel> arrayList1;

    public ViewAdapterBaseAdapter(Context context, List<NotiModel> arrayList1) {
        this.context = context;
        this.arrayList1 = arrayList1;
    }

    @Override
    public int getCount() {
        return arrayList1.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView txtv_title;
        TextView txtv_message;
        TextView txtv_package;
        TextView txtv_time;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View row = convertView;

        ViewHolder holder = new ViewHolder();

        if (row == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_noti, null);

            holder.txtv_title = row.findViewById(R.id.txtv_title);
            holder.txtv_message = row.findViewById(R.id.txtv_message);
            holder.txtv_package = row.findViewById(R.id.txtv_package);
            holder.txtv_time = row.findViewById(R.id.txtv_time);
            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }
        NotiModel expense = arrayList1.get(position);
        holder.txtv_title.setText(expense.getTitle());
        holder.txtv_message.setText(expense.getMessage());
        holder.txtv_package.setText(expense.getPackages());
        holder.txtv_time.setText(expense.getDate());

        return row;
    }
}
