package rezende.israel.minhaempresa.ui.activity;

import static java.lang.Double.valueOf;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;
import java.util.Calendar;

import rezende.israel.minhaempresa.R;
import rezende.israel.minhaempresa.dao.ColaboradoresDAO;
import rezende.israel.minhaempresa.datePicker.DatePickerFragment;
import rezende.israel.minhaempresa.modelo.Colaborador;
import rezende.israel.minhaempresa.ui.adapter.ListaColaboradoresAdapter;


public class CadastroColaboradoresActivity extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener {

    public static final String TITULO_APPBAR = "Minha Empresa";
    private EditText dataNasc;
    private ColaboradoresDAO dao;
    private RatingBar estrelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_colaboradores);
        setTitle(TITULO_APPBAR);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.itens_cargos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        TextView nome = findViewById(R.id.editTextTextPersonName);
        TextView sobrenome = findViewById(R.id.editTextTextPersonName2);
        dataNasc = findViewById(R.id.button2);
        Button botaoSalvar = findViewById(R.id.button);
        estrelas = findViewById(R.id.ratingBar);


        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao = new ColaboradoresDAO();
                Colaborador novoColaborador = new Colaborador(nome.getText().toString(), sobrenome.getText().toString(), dataNasc.getText().toString());
                dao.insere(novoColaborador);
                Intent salvaColaborador = new Intent();
                salvaColaborador.putExtra("colaborador", novoColaborador);
                setResult(RESULT_OK, salvaColaborador);
                validaCargo(dao, spinner);
                dao.adicionaAvaliacao(valueOf(estrelas.getRating()));
                finish();
            }
        });



    }

    private void validaCargo(ColaboradoresDAO dao, Spinner spinner) {
        int selectedItemPosition = spinner.getSelectedItemPosition();
        if (selectedItemPosition == 0){
            dao.addComercial();
        } else if (selectedItemPosition == 1){
            dao.addDev();
        } else if (selectedItemPosition == 2){
            dao.addSuporte();
        } else if (selectedItemPosition == 3){
            dao.addAdmin();
        }
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment(this);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
            dataNasc.setText(dia+"/"+mes+"/"+ano);
    }
}

