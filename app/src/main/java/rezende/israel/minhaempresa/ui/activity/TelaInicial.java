package rezende.israel.minhaempresa.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import rezende.israel.minhaempresa.R;

public class TelaInicial extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Minha Empresa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        setTitle(TITULO_APPBAR);

        Intent intent = new Intent(this, CadastroColaboradoresActivity.class);
        startActivity(intent);

    }
}