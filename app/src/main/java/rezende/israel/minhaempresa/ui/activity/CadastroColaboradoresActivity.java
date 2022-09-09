package rezende.israel.minhaempresa.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import rezende.israel.minhaempresa.R;
import rezende.israel.minhaempresa.datePicker.DatePickerFragment;


public class CadastroColaboradoresActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Minha Empresa";

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
        EditText dataNasc = findViewById(R.id.button2);


    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}

