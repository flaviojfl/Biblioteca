package br.com.biblioteca;

public class CalculoMultaAluno implements CalculoMultaStrategy {
    
    private static final double MULTA_POR_DIA = 1.00;

    @Override
    public double calcularMulta(long diasAtraso) {
        if (diasAtraso > 0) {
            return diasAtraso * MULTA_POR_DIA;
        }
        return 0;
    }
}