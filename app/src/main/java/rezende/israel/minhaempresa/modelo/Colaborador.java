package rezende.israel.minhaempresa.modelo;

public class Colaborador {
    private final String nome;
    private final String sobrenome;
    private final String data;

    public Colaborador(String nome, String sobrenome, String data) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getData() {
        return data;
    }
}
