package com.example.junio.estacionafacil.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.junio.estacionafacil.R;

import org.w3c.dom.Text;

/**
 * Created by junio on 24/06/16.
 */
public class ParkingViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView address;

    public ParkingViewHolder(View itemView) {
        super(itemView);

        name=(TextView) itemView.findViewById(R.id.name_parking);
        address=(TextView) itemView.findViewById(R.id.address);

    }
}
