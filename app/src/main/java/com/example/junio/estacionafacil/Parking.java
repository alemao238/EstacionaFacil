package com.example.junio.estacionafacil;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by junio on 22/06/16.
 */
public class Parking {
    private Long id;
    private String name;
    private String address;
    private int vacancies;
    private LatLng latLng;


    public Parking(Long id,String nome,String address,int vacancies,LatLng latLng){
        this.setId(id);
        this.setName(nome);
        this.setAddress(address);
        this.setVacancies(vacancies);
        this.setLatLng(latLng);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getVacancies() {
        return vacancies;
    }

    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
