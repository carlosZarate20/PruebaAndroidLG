package com.example.mirrorapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.mirrorapp.R;
import com.example.mirrorapp.adapters.GadgetAdapter;
import com.example.mirrorapp.model.Gadget;
import com.example.mirrorapp.services.MirrorService;
import com.google.gson.JsonIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GadgetActivity extends AppCompatActivity {

    final String TAG = "ListaGadget";
    TextView tvGadget;
    List<Gadget> listGadget;
    RecyclerView gadgetRecyclerView;
    LinearLayoutManager gadgetLinearLayoutManager;
    GadgetAdapter gadgetAdapter;

    private void ActualizarDatosGadget(){
        AndroidNetworking.get(MirrorService.MIRROR_URL)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            listGadget = Gadget.buildFromJsonObject(response.getJSONArray("Gadgets"));
                            gadgetAdapter.setGadget(listGadget).notifyDataSetChanged();
                            tvGadget.setVisibility(View.VISIBLE);
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d("Mirror", anError.getLocalizedMessage());
                    }
                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gadget_activity);

        gadgetRecyclerView = (RecyclerView) findViewById(R.id.RVgadgetXML);
        listGadget = new ArrayList<>();
        tvGadget = findViewById(R.id.tvGadget);

        gadgetAdapter = new GadgetAdapter(listGadget, this);
        gadgetRecyclerView.setAdapter(gadgetAdapter);
        gadgetLinearLayoutManager = new LinearLayoutManager(this);
        gadgetRecyclerView.setLayoutManager(new LinearLayoutManager(gadgetRecyclerView.getContext()));

        gadgetRecyclerView.setLayoutManager(gadgetLinearLayoutManager);
    }

    @Override
    protected void onResume(){
        super.onResume();
        ActualizarDatosGadget();
    }


}
