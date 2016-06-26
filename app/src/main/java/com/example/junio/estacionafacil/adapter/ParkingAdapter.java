package com.example.junio.estacionafacil.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.junio.estacionafacil.Parking;
import com.example.junio.estacionafacil.R;
import com.example.junio.estacionafacil.holder.ParkingViewHolder;

import java.util.List;

/**
 * Created by junio on 24/06/16.
 */
public class ParkingAdapter extends RecyclerView.Adapter<ParkingViewHolder> {
    private LayoutInflater inflater;
    private List<Parking> mList;
    private Context c;
    public ParkingAdapter(Context c,List<Parking> l){
        inflater=LayoutInflater.from(c);
        mList=l;
        this.c=c;

    }

    @Override
    public ParkingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.parking_item_recycler,parent,false);
        ParkingViewHolder pvh=new ParkingViewHolder(v,c);


        return pvh;
    }

    @Override
    public void onBindViewHolder(ParkingViewHolder holder, int position) {
        holder.name.setText(mList.get(position).getName());
        holder.address.setText(mList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
