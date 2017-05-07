package com.example.felipe.pet_project.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.felipe.pet_project.R;
import com.example.felipe.pet_project.model.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe on 06-05-17.
 */

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ListViewHolder> {

    Context context;
    List<Pet> dataList = new ArrayList<>();
    LayoutInflater inflater;


    public PetAdapter(Context context, List<Pet> dataList) {

        this.context = context;
        this.dataList = dataList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public PetAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = inflater.inflate(R.layout.detail_pet, parent, false);
        PetAdapter.ListViewHolder viewHolder = new PetAdapter.ListViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PetAdapter.ListViewHolder holder, int position) {
        holder.TvType.setText(dataList.get(position).type);
        holder.TvColor.setText(dataList.get(position).color);
        holder.TvNecklace.setText(dataList.get(position).necklace);
        holder.TvRace.setText(dataList.get(position).race);
        holder.TvOwner.setText(dataList.get(position).owner_id);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        TextView TvType;
        TextView TvRace;
        TextView TvColor;
        TextView TvNecklace;
        TextView TvOwner;

        public ListViewHolder(View itemView) {
            super(itemView);

            TvType = (TextView) itemView.findViewById(R.id.type_pet);
            TvRace = (TextView) itemView.findViewById(R.id.race_pet);
            TvColor = (TextView) itemView.findViewById(R.id.color_pet);
            TvNecklace = (TextView) itemView.findViewById(R.id.necklace_pet);
            TvOwner = (TextView) itemView.findViewById(R.id.owner_pet);

        }
    }
}
