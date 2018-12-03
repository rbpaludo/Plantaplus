package br.ufg.inf.plantaplus;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufg.inf.plantaplus.model.Planta;

public class CadastroPlantasActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    Button btnCadastrarPlanta;
    EditText etEspecie;
    EditText etIdade;
    EditText etHoras;
    EditText etMinutos;
    EditText etIntervalo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_plantas);
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        final Planta planta = new Planta();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("usuario/" + getIntent().getStringExtra("cpf") +
                "/plantas");

        etEspecie = findViewById(R.id.editEspecie);
        etIdade = findViewById(R.id.editIdade);
        etHoras = findViewById(R.id.editHoras);
        etMinutos = findViewById(R.id.editMinutos);
        etIntervalo = findViewById(R.id.editIntervalo);

        btnCadastrarPlanta = findViewById(R.id.buttonCadastrar);
        btnCadastrarPlanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date dataAtual = new Date();

                planta.setDataNasc(new Date(dataAtual.getTime() -
                        Integer.parseInt(etIdade.getText().toString()) * 24 * 3600 * 1000));
                planta.setEspecie(etEspecie.getText().toString());
                planta.setHorasNotificacao(Integer.parseInt(etHoras.getText().toString()));
                planta.setMinutosNotificacao(Integer.parseInt(etMinutos.getText().toString()));
                planta.setIntervaloNotificacao(Integer.parseInt(etIntervalo.getText().toString()));

                reference.child(planta.getEspecie()).setValue(planta);
            }
        });
    }
}
