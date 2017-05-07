package com.example.felipe.pet_project.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.felipe.pet_project.R;
import com.example.felipe.pet_project.model.Owner;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Felipe on 06-05-17.
 */

public class OwnerAdapter extends RecyclerView.Adapter<OwnerAdapter.ListViewHolder> {

    Context context;
    List<Owner> dataList = new ArrayList<>();
    LayoutInflater inflater;


    public OwnerAdapter(Context context, List<Owner> dataList) {

        this.context = context;
        this.dataList = dataList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = inflater.inflate(R.layout.detail_owner, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OwnerAdapter.ListViewHolder holder, int position) {
        holder.tvRut.setText(dataList.get(position).rut);
        holder.TvAddress.setText(dataList.get(position).address);
        holder.TvName.setText(dataList.get(position).name);
        holder.TvTelephone.setText(dataList.get(position).telephone);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        TextView TvName;
        TextView TvAddress;
        TextView tvRut;
        TextView TvTelephone;

        public ListViewHolder(View itemView) {
            super(itemView);

            tvRut = (TextView) itemView.findViewById(R.id.result_rut);
            TvName = (TextView) itemView.findViewById(R.id.result_name);
            TvAddress = (TextView) itemView.findViewById(R.id.result_address);
            TvTelephone = (TextView) itemView.findViewById(R.id.result_telephone);

        }
    }
}
