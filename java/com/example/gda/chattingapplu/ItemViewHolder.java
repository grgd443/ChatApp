package com.example.gda.chattingapplu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView nameTv, idTv;

    public TextView getNameTv() {
        return nameTv;
    }

    public TextView getIdTv() {
        return idTv;
    }

    public ItemViewHolder(View itemView) {
        super(itemView);
        nameTv = (TextView) itemView.findViewById(R.id.nameTv);
        idTv = (TextView) itemView.findViewById(R.id.idTv);
    }

}
