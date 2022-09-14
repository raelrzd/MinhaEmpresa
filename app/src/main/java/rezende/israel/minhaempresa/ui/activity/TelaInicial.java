package rezende.israel.minhaempresa.ui.activity;

import androidx.annotation.Nullable;
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
    private ListaColaboradoresAdapter adapter;
    private ColaboradoresDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        setTitle(TITULO_APPBAR);
        configuraFabNovoColaborador();

        dao = new ColaboradoresDAO();
        dao.insere(new Colaborador("Israel", "Rezende", "29/03/2001"));

        RecyclerView listaColaboradoresRecycler = findViewById(R.id.recycleview);
        adapter = new ListaColaboradoresAdapter(dao.todos(), this);
        listaColaboradoresRecycler.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 50){
            if(resultCode == RESULT_OK){
                if (data.hasExtra("colaborador")) {
                    Colaborador colaborador = (Colaborador) data.getSerializableExtra("colaborador");
                    adapter.insere(colaborador);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        Intent intent = getIntent();

        super.onResume();
    }


    private void configuraFabNovoColaborador() {
        FloatingActionButton FabNovoColaborador = findViewById(R.id.floatingActionButton3);
        Intent intent = new Intent(this, CadastroColaboradoresActivity.class);
        FabNovoColaborador.setOnClickListener(view -> startActivityForResult(intent, 50));
    }
}