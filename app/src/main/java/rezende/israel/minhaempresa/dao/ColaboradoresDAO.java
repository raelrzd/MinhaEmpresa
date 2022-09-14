package rezende.israel.minhaempresa.dao;

import java.util.ArrayList;
import java.util.List;

import rezende.israel.minhaempresa.modelo.Colaborador;

public class ColaboradoresDAO {

    private final static ArrayList<Colaborador> colaboradores = new ArrayList<>();
    private static int comercialDAO = 0;
    private static int desenvolvimentoDAO = 0;
    private static int suporteDAO = 0;
    private static int administrativoDAO = 0;
    private final static ArrayList<Double> mediaColaboradores = new ArrayList<>();

    public void adicionaAvaliacao(double valor){
        mediaColaboradores.add(valor);
    }

    public double retornaMedia(){
        double totalSum = 0;
        double media = 0;
        int n = mediaColaboradores.size();
        for (int i=0; i<n;i++){
            totalSum+=mediaColaboradores.get(i);
            media = (totalSum/n);
        }
        return media;
    }

    public String retornaMediaString(){
        String valor = String.valueOf(retornaMedia());
        return valor.substring(0,3);
    }

    public void addComercial() {
        comercialDAO++;
    }

    public void addDev() {
        desenvolvimentoDAO++;
    }

    public void addSuporte() {
        suporteDAO++;
    }

    public void addAdmin() {
        administrativoDAO++;
    }

    public static String getComercialDAO() {
        return String.valueOf(comercialDAO);
    }

    public static String getDesenvolvimentoDAO() {
        return String.valueOf(desenvolvimentoDAO);
    }

    public static String getSuporteDAO() {
        return String.valueOf(suporteDAO);
    }

    public static String getAdministrativoDAO() {
        return String.valueOf(administrativoDAO);
    }

    public List<Colaborador> todos() {
        return (List<Colaborador>) colaboradores.clone();
    }

    public void insere(Colaborador colaborador) {
        ColaboradoresDAO.colaboradores.add(colaborador);
    }


}
