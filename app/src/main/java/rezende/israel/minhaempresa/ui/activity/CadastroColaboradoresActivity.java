package rezende.israel.minhaempresa.ui.activity;

import static java.lang.Double.valueOf;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import rezende.israel.minhaempresa.R;
import rezende.israel.minhaempresa.dao.ColaboradoresDAO;
import rezende.israel.minhaempresa.datePicker.DatePickerFragment;
import rezende.israel.minhaempresa.modelo.Colaborador;


public class CadastroColaboradoresActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static final String TITULO_APPBAR = "Minha Empresa";
    public static final String CHAVE_COLABORADOR = "colaborador";
    private EditText dataNasc;
    private ColaboradoresDAO dao;
    private RatingBar estrelas;
    private TextView nome;
    private TextView sobrenome;
    private Button botaoSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_colaboradores);
        setTitle(TITULO_APPBAR);
        Spinner spinner = configuraSpinner();
        realizaByIdDasViews();
        configuraBotaoSalvar(spinner);
    }

    private void configuraBotaoSalvar(Spinner spinner) {
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Colaborador novoColaborador = criaNovoColaborador();
                enviaDadosParaActivityInicial(novoColaborador);
                validaCargo(dao, spinner);
                dao.adicionaAvaliacao(valueOf(estrelas.getRating()));
                finish();
            }
        });
    }

    private void enviaDadosParaActivityInicial(Colaborador novoColaborador) {
        Intent salvaColaborador = new Intent();
        salvaColaborador.putExtra(CHAVE_COLABORADOR, novoColaborador);
        setResult(RESULT_OK, salvaColaborador);
    }

    @NonNull
    private Colaborador criaNovoColaborador() {
        dao = new ColaboradoresDAO();
        Colaborador novoColaborador = new Colaborador(nome.getText().toString(), sobrenome.getText().toString());
        dao.insere(novoColaborador);
        return novoColaborador;
    }

    private void realizaByIdDasViews() {
        nome = findViewById(R.id.editText_form_nome);
        sobrenome = findViewById(R.id.editText_form_sobrenome);
        dataNasc = findViewById(R.id.editText_form_data_nascimento);
        botaoSalvar = findViewById(R.id.button_form_salvar);
        estrelas = findViewById(R.id.ratingBar_form);
    }

    @NonNull
    private Spinner configuraSpinner() {
        Spinner spinner = findViewById(R.id.spinner_form);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.itens_cargos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return spinner;
    }

    private void validaCargo(ColaboradoresDAO dao, Spinner spinner) {
        int selectedItemPosition = spinner.getSelectedItemPosition();
        if (selectedItemPosition == 0) {
            dao.addComercial();
        } else if (selectedItemPosition == 1) {
            dao.addDev();
        } else if (selectedItemPosition == 2) {
            dao.addSuporte();
        } else if (selectedItemPosition == 3) {
            dao.addAdmin();
        }
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment(this);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
        dataNasc.setText(dia + "/" + mes + "/" + ano);
    }
}

