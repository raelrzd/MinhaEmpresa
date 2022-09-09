package rezende.israel.minhaempresa.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import rezende.israel.minhaempresa.R;
import rezende.israel.minhaempresa.dao.ColaboradoresDAO;
import rezende.israel.minhaempresa.modelo.Colaborador;
import rezende.israel.minhaempresa.ui.adapter.ListaColaboradoresAdapter;

public class TelaInicial extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Minha Empresa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        setTitle(TITULO_APPBAR);
        configuraFabNovoColaborador();

        ColaboradoresDAO dao = new ColaboradoresDAO();
        dao.insere(new Colaborador("Israel", "Rezende", "29/03/2001"));

        RecyclerView listaColaboradores = findViewById(R.id.recycleview);
        listaColaboradores.setAdapter(new ListaColaboradoresAdapter(dao.todos(), this));

    }

    private void configuraFabNovoColaborador() {
        FloatingActionButton FabNovoColaborador = findViewById(R.id.floatingActionButton3);
        Intent intent = new Intent(this, CadastroColaboradoresActivity.class);
        FabNovoColaborador.setOnClickListener(view -> startActivity(intent));
    }
}