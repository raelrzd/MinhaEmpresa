package rezende.israel.minhaempresa.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rezende.israel.minhaempresa.modelo.Colaborador;

public class ColaboradoresDAO {

    private final static ArrayList<Colaborador> colaboradores = new ArrayList<>();

    public List<Colaborador> todos() {
        return (List<Colaborador>) colaboradores.clone();
    }

    public void insere(Colaborador colaborador){
        ColaboradoresDAO.colaboradores.add(colaborador);

    }





}
