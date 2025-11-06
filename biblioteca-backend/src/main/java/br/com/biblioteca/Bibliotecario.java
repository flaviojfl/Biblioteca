package br.com.biblioteca;

public class Bibliotecario extends Pessoa {

    private String cargo;

    public Bibliotecario(String matricula, String nome, String cargo) {
        super(matricula, nome);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}