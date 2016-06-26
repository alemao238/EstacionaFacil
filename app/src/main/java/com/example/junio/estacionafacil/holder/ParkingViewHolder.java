package com.example.junio.estacionafacil.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junio.estacionafacil.R;

import org.w3c.dom.Text;

/**
 * Created by junio on 24/06/16.
 */
public class ParkingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView name;
    public TextView address;

    Context c;
    public ParkingViewHolder(View itemView,Context c) {
        super(itemView);

        this.c=c;
        name=(TextView) itemView.findViewById(R.id.name_parking);
        address=(TextView) itemView.findViewById(R.id.address);

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(c, name.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
