package com.example.junio.estacionafacil;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junio.estacionafacil.adapter.ParkingAdapter;
import com.example.junio.estacionafacil.service.ParkingRestClient;
import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private String resultado;
    private ParkingRestClient client;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;

    private ImageView refresh,search,manage;

    private List<Parking> parkingList = new ArrayList<>();

    private CollapsingToolbarLayout cToolbar;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        mToolbar=(Toolbar) findViewById(R.id.toolbar_details);

        mToolbar.getBackground().setAlpha(0);

        mRecyclerView=(RecyclerView) findViewById(R.id.recycler_map);

        refresh=(ImageView) findViewById(R.id.refresh);
        search=(ImageView) findViewById(R.id.search);
        manage=(ImageView) findViewById(R.id.manage);


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parkingList.clear();
                addMarkers();
            }
        });


        text = (TextView) findViewById(R.id.text);

        client = new ParkingRestClient();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        addMarkers();



        // Add a marker in Sydney and move the camera

    }


    public void addMarkers() {
        client.get("/parkings", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONArray jsonArray=new JSONArray(new String(responseBody));
                    for (int i=0;i < jsonArray.length();i++){
                        JSONObject jsonObject=new JSONObject(jsonArray.getJSONObject(i).toString());
                        LatLng l=new LatLng(jsonObject.getDouble("latitude"),jsonObject.getDouble("longitude"));
                        Parking p=new Parking(jsonObject.getLong("id"),jsonObject.getString("name"),jsonObject.getString("address"),jsonObject.getInt("vacancies"),l);

                        parkingList.add(p);
                    }
                    Toast.makeText(MapsActivity.this,parkingList.get(0).getName(), Toast.LENGTH_SHORT).show();
                    for (Parking parking:parkingList){

                        mMap.addMarker(new MarkerOptions().position(parking.getLatLng()).title(parking.getName()).snippet(parking.getVacancies()+" Vagas"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parking.getLatLng(),13));
                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                Toast.makeText(MapsActivity.this, ""+marker.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker) {
                                String idParking= marker.getId().toString().substring(1);

                                Toast.makeText(MapsActivity.this, ""+idParking
                                        , Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        });
                    }

                    LinearLayoutManager llm=new LinearLayoutManager(MapsActivity.this);
                    llm.setOrientation(llm.getOrientation());
                    ParkingAdapter adapter=new ParkingAdapter(MapsActivity.this,parkingList);

                    adapter.notifyDataSetChanged();
                    mRecyclerView.setLayoutManager(llm);
                    mRecyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MapsActivity.this, "Erro"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }



}
