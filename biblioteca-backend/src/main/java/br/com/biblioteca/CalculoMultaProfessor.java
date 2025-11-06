package br.com.biblioteca;

public class CalculoMultaProfessor implements CalculoMultaStrategy {

    private static final double MULTA_POR_DIA = 0.50;
    
    @Override
    public double calcularMulta(long diasAtraso) {
        if (diasAtraso > 0) {
            return diasAtraso * MULTA_POR_DIA;
        }
        return 0;
    }
}