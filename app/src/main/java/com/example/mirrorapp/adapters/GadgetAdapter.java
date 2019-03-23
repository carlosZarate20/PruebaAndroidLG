package com.example.mirrorapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.mirrorapp.R;
import com.example.mirrorapp.model.Gadget;

import org.w3c.dom.Text;

import java.util.List;

public class GadgetAdapter extends RecyclerView.Adapter<GadgetAdapter.GadgetViewHolder> {

    private List<Gadget> listGadet;
    private Context context;

    public GadgetAdapter setGadget(List<Gadget> plista) {
        this.listGadet = plista;
        return this;
    }
    public GadgetAdapter(List<Gadget> plista, Context context) {
        this.context = context;
        this.listGadet = plista;

    }
    @NonNull
    @Override
    public GadgetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gadget_lista, parent, false);
        GadgetViewHolder gadgetVH = new GadgetViewHolder(view, this.context, this.listGadet);

        return gadgetVH;
    }

    @Override
    public void onBindViewHolder(@NonNull GadgetViewHolder holder, int position) {
        Gadget gasget = listGadet.get(position);

        holder.textviewNombre.setText(gasget.getName());
        holder.viewFlag.setChecked(gasget.isFlag());
    }

    @Override
    public int getItemCount() {
        return listGadet.size();
    }

    public class GadgetViewHolder extends RecyclerView.ViewHolder {
        TextView textviewNombre, textViewFlag;
        Switch viewFlag;
        ImageView imageviewIcon;
        List<Gadget> listGadgetVH;
        Context contextVH;

        public GadgetViewHolder(View itemView, Context pcontext, List<Gadget> plista) {
            super(itemView);
            this.contextVH = pcontext;
            this.listGadgetVH = plista;

            textviewNombre = (TextView) itemView.findViewById(R.id.nameGadget);
            viewFlag = (Switch) itemView.findViewById(R.id.flagGadget);
        }
    }
}
