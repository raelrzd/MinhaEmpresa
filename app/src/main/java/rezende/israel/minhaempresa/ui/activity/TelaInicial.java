package rezende.israel.minhaempresa.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import rezende.israel.minhaempresa.R;
import rezende.israel.minhaempresa.dao.ColaboradoresDAO;
import rezende.israel.minhaempresa.modelo.Colaborador;
import rezende.israel.minhaempresa.ui.adapter.ListaColaboradoresAdapter;

public class TelaInicial extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Minha Empresa";
    public static final int CODIGO_REQUISICAO_NOVO_COLABORADOR = 50;
    public static final String CHAVE_COLABORADOR = "colaborador";
    private ListaColaboradoresAdapter adapter;
    private ColaboradoresDAO dao;
    private TextView totalComercial;
    private TextView totalDev;
    private TextView totalSuporte;
    private TextView totalAdmin;
    private TextView totalColaboradores;
    private RatingBar mediaStars;
    private TextView mediaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        setTitle(TITULO_APPBAR);
        configuraFabNovoColaborador();
        configuraAdapter();
        realizaByIdDasViews();
        preencheTotalDeColaboradores(totalComercial, totalDev, totalSuporte, totalAdmin);
    }

    private void realizaByIdDasViews() {
        totalComercial = findViewById(R.id.totalComercial);
        totalDev = findViewById(R.id.totalDev);
        totalSuporte = findViewById(R.id.totalSuporte);
        totalAdmin = findViewById(R.id.totalAdmin);
        totalColaboradores = findViewById(R.id.totalColaboradores);
        mediaStars = findViewById(R.id.ratingBar_telaInicial_media);
        mediaText = findViewById(R.id.textView_telaInicial_media);
    }

    private void configuraAdapter() {
        dao = new ColaboradoresDAO();
        RecyclerView listaColaboradoresRecycler = findViewById(R.id.recycleview);
        adapter = new ListaColaboradoresAdapter(dao.todos(), this);
        listaColaboradoresRecycler.setAdapter(adapter);
    }

    private void preencheTotalDeColaboradores(TextView totalComercial, TextView totalDev, TextView totalSuporte, TextView totalAdmin) {
        totalComercial.setText(ColaboradoresDAO.getComercialDAO());
        totalDev.setText(ColaboradoresDAO.getDesenvolvimentoDAO());
        totalSuporte.setText(ColaboradoresDAO.getSuporteDAO());
        totalAdmin.setText(ColaboradoresDAO.getAdministrativoDAO());
        totalColaboradores.setText("Colaboradores " + dao.todos().size());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (verificaCodigoRequisicaoRecebido(requestCode)) {
            if (verificaCodigoDeResultado(resultCode)) {
                if (data.hasExtra(CHAVE_COLABORADOR)) {
                    Colaborador colaborador = (Colaborador) data.getSerializableExtra(CHAVE_COLABORADOR);
                    adapter.insere(colaborador);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean verificaCodigoDeResultado(int resultCode) {
        return resultCode == RESULT_OK;
    }

    private boolean verificaCodigoRequisicaoRecebido(int requestCode) {
        return requestCode == CODIGO_REQUISICAO_NOVO_COLABORADOR;
    }

    @Override
    protected void onResume() {
        preencheTotalDeColaboradores(totalComercial, totalDev, totalSuporte, totalAdmin);
        preencheDadosMediaExperiencia();
        super.onResume();
    }

    private void preencheDadosMediaExperiencia() {
        mediaStars.setRating((float) dao.retornaMedia());
        mediaText.setText(dao.retornaMediaString());
    }

    private void configuraFabNovoColaborador() {
        FloatingActionButton FabNovoColaborador = findViewById(R.id.Fab_telaInicial_add_colaborador);
        Intent intent = new Intent(this, CadastroColaboradoresActivity.class);
        FabNovoColaborador.setOnClickListener(view -> startActivityForResult(intent, CODIGO_REQUISICAO_NOVO_COLABORADOR));
    }
}