package br.com.biblioteca;

public class Usuario extends Pessoa {

    private String tipo;

    public Usuario(String matricula, String nome, String tipo) {
        super(matricula, nome);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}