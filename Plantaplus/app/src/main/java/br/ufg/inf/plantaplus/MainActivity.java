package br.ufg.inf.plantaplus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.ufg.inf.plantaplus.adapter.PlantsAdapter;
import br.ufg.inf.plantaplus.model.Planta;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference reference;

    List<Planta> plantaList = new ArrayList<>();
    PlantsAdapter adapter;

    String cpf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);

        /*TODO Remoção de bloco após implementação de funcionalidade
        Este bloco de comandos deve ser removido após a implementação de autenticação de usuário,
        que deverá executar esses comandos sempre que um usuário for autenticado, inserindo o CPF do
        usuário que fez o login na aplicação*/
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("cpfUsuario", "07594381963");
        editor.commit();

        FloatingActionButton fabCadastroPlanta = findViewById(R.id.fabCadastroPlanta);
        fabCadastroPlanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCadastroPlantas();
            }
        });

        cpf = sharedPref.getString("cpfUsuario", "0");
        if(!cpf.equals("0")){

            database = FirebaseDatabase.getInstance();
            reference = database.getReference("usuario");

            recyclerView = findViewById(R.id.plantList);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            loadListPlantas();
        }
    }

    private void loadListPlantas(){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                plantaList.clear();
                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    plantaList.add(dados.getValue(Planta.class));
                }
                Log.w("HALLO", "Entrou aqui na parada");
                // ...
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("Main/ListPlantas", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        reference.child(cpf).child("plantas").addValueEventListener(postListener);
        adapter = new PlantsAdapter(plantaList, this);
        recyclerView.setAdapter(adapter);
    }

    public void startCadastroPlantas(){
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        Intent it = new Intent(this, CadastroPlantasActivity.class);
        it.putExtra("cpf", sharedPref.getString("cpfUsuario", "0"));
        startActivity(it);
    }
}
