package com.arr.indianbankandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryDataAdapter extends BaseAdapter {

    private ArrayList<TransactionsHistory> tfhList=new ArrayList<>();

    //we need this object to link between this java class and the list_row XML file
    private LayoutInflater inflater;

    //constructor
    public HistoryDataAdapter(Context context, ArrayList<TransactionsHistory>tfhList){
        this.tfhList=tfhList;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return tfhList.size();
    }

    @Override
    public Object getItem(int position) {
        return tfhList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null) {
            convertView = inflater.inflate(R.layout.transfer_history_list_row, null);
            holder = new ViewHolder();
            holder.tfDate = convertView.findViewById(R.id.tvDate);
            holder.tftype = convertView.findViewById(R.id.tvType);
            holder.tfdecription = convertView.findViewById(R.id.tvDescription);
            holder.tfamount = convertView.findViewById(R.id.tvTransferHisAmount);
            convertView.setTag(holder);


        }else
            holder=(ViewHolder) convertView.getTag();
        holder.tfDate.setText(tfhList.get(position).getTransferDate());
        holder.tftype.setText(tfhList.get(position).getType());
        holder.tfdecription.setText(tfhList.get(position).getDescription());
        holder.tfamount.setText(String.valueOf(tfhList.get(position).getAmount()));

        return convertView;
    }
    private class ViewHolder{
        //create attributes according to the object related to the list_row
        private TextView tfDate;
        private TextView tftype;
        private TextView tfdecription;
        private TextView tfamount;

    }
}
