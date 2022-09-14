package rezende.israel.minhaempresa.ui.activity;

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
    private TextView dataNasc;

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

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColaboradoresDAO dao = new ColaboradoresDAO();
                Colaborador novoColaborador = new Colaborador(nome.getText().toString(), sobrenome.getText().toString(), dataNasc.getText().toString());
                dao.insere(novoColaborador);
                Intent salvaColaborador = new Intent();
                salvaColaborador.putExtra("colaborador", novoColaborador);
                setResult(RESULT_OK, salvaColaborador);
                finish();
            }
        });

        ImageView setaDown = findViewById(R.id.imageView7);
        spinner.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                setaDown.setVisibility(View.INVISIBLE);
            }
        });

    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment(this);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            dataNasc.setText(i+"/"+i1+"/"+i2);
    }
}

