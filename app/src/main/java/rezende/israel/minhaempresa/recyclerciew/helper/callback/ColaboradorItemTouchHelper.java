package rezende.israel.minhaempresa.recyclerciew.helper.callback;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import rezende.israel.minhaempresa.dao.ColaboradoresDAO;
import rezende.israel.minhaempresa.ui.adapter.ListaColaboradoresAdapter;

public class ColaboradorItemTouchHelper extends ItemTouchHelper.Callback {

    private ListaColaboradoresAdapter adapter;
    private ColaboradoresDAO dao;

    public ColaboradorItemTouchHelper(ListaColaboradoresAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int marcacoesDeDeslize = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        int marcacoesDeArrastar = ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        return makeMovementFlags(marcacoesDeArrastar, marcacoesDeDeslize);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
    //arrastar
        int posicaoInicial = viewHolder.getAdapterPosition();
        int posicaoFinal = target.getAdapterPosition();
        dao.troca(posicaoInicial,posicaoFinal);
        adapter.trocaColaborador(posicaoInicial, posicaoFinal);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
    //deslise
        int posicaoColaboradorSelecionado = viewHolder.getAdapterPosition();
        dao = new ColaboradoresDAO();
        dao.removeColaborador(posicaoColaboradorSelecionado);
        adapter.remove(posicaoColaboradorSelecionado);
    }
}
