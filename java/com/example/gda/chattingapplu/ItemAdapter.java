package com.example.gda.chattingapplu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;


public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private ArrayList<Contact> items;
    private Context context;

    public ItemAdapter(Context context, ArrayList<Contact> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
        ItemViewHolder viewHolder = new ItemViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Contact item = items.get(position);
        holder.getIdTv().setText(String.valueOf(item.getContactID()));
        holder.getNameTv().setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
