package rezende.israel.minhaempresa.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import rezende.israel.minhaempresa.R;
import rezende.israel.minhaempresa.dao.ColaboradoresDAO;
import rezende.israel.minhaempresa.modelo.Colaborador;
import rezende.israel.minhaempresa.recyclerciew.helper.callback.ColaboradorItemTouchHelper;
import rezende.israel.minhaempresa.ui.adapter.ListaColaboradoresAdapter;

public class TelaInicial extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Minha Empresa";
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

        dao = new ColaboradoresDAO();
        RecyclerView listaColaboradoresRecycler = findViewById(R.id.recycleview);
        adapter = new ListaColaboradoresAdapter(dao.todos(), this);
        listaColaboradoresRecycler.setAdapter(adapter);

        totalComercial = findViewById(R.id.totalComercial);
        totalDev = findViewById(R.id.totalDev);
        totalSuporte = findViewById(R.id.totalSuporte);
        totalAdmin = findViewById(R.id.totalAdmin);
        totalColaboradores = findViewById(R.id.totalColaboradores);
        mediaStars = findViewById(R.id.ratingBar2);
        mediaText = findViewById(R.id.textView10);

        preencheTotalDeColaboradores(totalComercial, totalDev, totalSuporte, totalAdmin);


//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ColaboradorItemTouchHelper(adapter));
//        itemTouchHelper.attachToRecyclerView(listaColaboradoresRecycler);


    }

    private void preencheTotalDeColaboradores(TextView totalComercial, TextView totalDev, TextView totalSuporte, TextView totalAdmin) {
        totalComercial.setText(ColaboradoresDAO.getComercialDAO());
        totalDev.setText(ColaboradoresDAO.getDesenvolvimentoDAO());
        totalSuporte.setText(ColaboradoresDAO.getSuporteDAO());
        totalAdmin.setText(ColaboradoresDAO.getAdministrativoDAO());
        totalColaboradores.setText("Colaboradores "+dao.todos().size());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 50) {
            if (resultCode == RESULT_OK) {
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
        preencheTotalDeColaboradores(totalComercial, totalDev, totalSuporte, totalAdmin);
        mediaStars.setRating((float) dao.retornaMedia());
        mediaText.setText(dao.retornaMediaString());
        super.onResume();
    }

    private void configuraFabNovoColaborador() {
        FloatingActionButton FabNovoColaborador = findViewById(R.id.floatingActionButton3);
        Intent intent = new Intent(this, CadastroColaboradoresActivity.class);
        FabNovoColaborador.setOnClickListener(view -> startActivityForResult(intent, 50));
    }
}