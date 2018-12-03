package br.ufg.inf.plantaplus.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.ufg.inf.plantaplus.R;

public class PlantsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    public TextView textNome;
    public TextView textEspecie;
    public TextView textIdade;

    public PlantsViewHolder(View itemView) {
        super(itemView);
        textNome = itemView.findViewById(R.id.plantaNomePopular);
        textEspecie = itemView.findViewById(R.id.plantaNomeCientifico);
        textIdade = itemView.findViewById(R.id.plantaIdade);
    }

    @Override
    public void onClick(View v) {

    }
}
