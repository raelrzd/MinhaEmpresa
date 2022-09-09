package rezende.israel.minhaempresa.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import rezende.israel.minhaempresa.R;

public class CadastroColaboradoresActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Minha Empresa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_colaboradores);
        setTitle(TITULO_APPBAR);

    }
}