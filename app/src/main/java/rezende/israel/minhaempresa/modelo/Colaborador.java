package rezende.israel.minhaempresa.modelo;

import java.io.Serializable;

public class Colaborador implements Serializable {
    private final String nome;
    private final String sobrenome;

    public Colaborador(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }
}
