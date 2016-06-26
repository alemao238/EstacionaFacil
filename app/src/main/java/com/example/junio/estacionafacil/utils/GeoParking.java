package com.example.junio.estacionafacil.utils;

import com.example.junio.estacionafacil.Parking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junio on 26/06/16.
 */
public class GeoParking {


    public List<Parking> calculaDistancia(double lat1, double lng1, List<Parking> list) {
        //double earthRadius = 3958.75;//miles
        double dist = 0;
        List<Parking>  parkings=new ArrayList<>();

        for(Parking p:list) {

            double lat2=p.getLatLng().latitude;
            double lng2=p.getLatLng().longitude;

            double earthRadius = 6371;//kilometers
            double dLat = Math.toRadians(lat2 - lat1);
            double dLng = Math.toRadians(lng2 - lng1);
            double sindLat = Math.sin(dLat / 2);
            double sindLng = Math.sin(dLng / 2);
            double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                    * Math.cos(Math.toRadians(lat1))
                    * Math.cos(Math.toRadians(lat2));
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            dist = earthRadius * c;

            if(dist<1000) {

                parkings.add(p);
               /* if (dist < 1000) {

                    double res = dist * 1000;
                    int res1 = (int) res;

                } else if (dist > 0) {
                    int res = (int) dist;

                }*/
            }

        }

        return parkings;
        //em metros
    }
}
