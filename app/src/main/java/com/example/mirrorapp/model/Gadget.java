package com.example.mirrorapp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Gadget {
    private String name;
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }
    public Gadget(){

    }

    public Gadget(String name, boolean flag) {
        this.name = name;
        this.flag = flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static Gadget buildFromJsonObject(JSONObject jsonGadget){
        if(jsonGadget == null) return null;
        Gadget gadget = new Gadget();
        try {
            gadget.setName(jsonGadget.getString("Description"));
            gadget.setFlag(jsonGadget.getBoolean("IsActive"));
            return gadget;
        } catch(JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Gadget> buildFromJsonObject(JSONArray jsonGadgets){
        int gadgetCount = jsonGadgets.length();
        List<Gadget> gadgets = new ArrayList<>();
        for(int i = 0; i < gadgetCount; i++){
            try{
                JSONObject jsonGadget = (JSONObject) jsonGadgets.get(i);
                Gadget gadget =Gadget.buildFromJsonObject(jsonGadget);
                gadgets.add(gadget);

            } catch(JSONException e){
                e.printStackTrace();
            }
        }

        return gadgets;
    }
}

