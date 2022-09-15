package rezende.israel.minhaempresa.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import rezende.israel.minhaempresa.R;
import rezende.israel.minhaempresa.modelo.Colaborador;

public class ListaColaboradoresAdapter extends RecyclerView.Adapter<ListaColaboradoresAdapter.ColaboradorViewHolder> {

    private final List<Colaborador> colaboradores;
    private final Context context;

    public ListaColaboradoresAdapter(List<Colaborador> colaboradores, Context context) {
        this.colaboradores = colaboradores;
        this.context = context;
    }

    @NonNull
    @Override
    public ListaColaboradoresAdapter.ColaboradorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_colaborador, parent, false);
        return new ColaboradorViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaColaboradoresAdapter.ColaboradorViewHolder holder, int position) {
        Colaborador colaborador = colaboradores.get(position);
        holder.preencheCampo(colaborador);

    }

    @Override
    public int getItemCount() {
        return colaboradores.size();
    }

    public void insere(Colaborador colaborador) {
        colaboradores.add(colaborador);
        notifyDataSetChanged();
    }

    public void remove(int posicao) {
        colaboradores.remove(posicao);
        notifyDataSetChanged();
    }

    public void trocaColaborador(int posicaoInicial, int posicaoFinal) {
        Collections.swap(colaboradores,posicaoInicial,posicaoFinal);
        notifyItemMoved(posicaoInicial,posicaoFinal);
    }

    static class ColaboradorViewHolder extends RecyclerView.ViewHolder {

        private final TextView nome;

        public ColaboradorViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textView15);
        }

        public void preencheCampo(Colaborador colaborador) {
            String nomeCompleto = colaborador.getNome() + " " + colaborador.getSobrenome();
            nome.setText(nomeCompleto);
        }

    }

}
