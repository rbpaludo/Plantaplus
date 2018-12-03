package br.ufg.inf.plantaplus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufg.inf.plantaplus.R;
import br.ufg.inf.plantaplus.model.Planta;

public class PlantsAdapter extends RecyclerView.Adapter<PlantsViewHolder>{

    private List<Planta> plantaList = new ArrayList<>();
    private Context context;

    public PlantsAdapter(List<Planta> plantaList, Context context) {
        this.plantaList = plantaList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.plants_item, parent, false);
        return new PlantsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantsViewHolder holder, int position) {
        holder.textNome.setText(plantaList.get(position).getEspecie());
        holder.textEspecie.setText(plantaList.get(position).getEspecie());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNasc = plantaList.get(position).getDataNasc();
        Integer idadeDias = (int) ((new Date().getTime() -  dataNasc.getTime()) / 24 / 3600 / 1000);

        holder.textIdade.setText(idadeDias.toString());
    }

    @Override
    public int getItemCount() {
        return plantaList.size();
    }
}
