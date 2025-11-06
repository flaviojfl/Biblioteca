package br.com.biblioteca;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {

    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private String status;

    public Emprestimo(Livro livro, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.status = "ATIVO";
    }

    public double calcularMulta() {
        if (dataDevolucaoReal == null) {
            dataDevolucaoReal = LocalDate.now();
        }

        long diasAtraso = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal);

        CalculoMultaStrategy strategy;

        if ("professor".equalsIgnoreCase(usuario.getTipo())) {
            strategy = new CalculoMultaProfessor();
        } else {
            strategy = new CalculoMultaAluno();
        }

        return strategy.calcularMulta(diasAtraso);
    }

    public void finalizar() {
        this.status = "FINALIZADO";
        this.dataDevolucaoReal = LocalDate.now();
    }
    
    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public String getStatus() {
        return status;
    }
}