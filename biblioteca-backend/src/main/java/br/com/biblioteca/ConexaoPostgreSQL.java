package br.com.biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {

    private static final String URL = "jdbc:postgresql://localhost:5432/sistema_biblioteca";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "utfpr";

    public static Connection obterConexao() {
        Connection conexao = null;
        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            System.out.println("O driver do PostgreSQL não foi encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados.");
            e.printStackTrace();
        }
        return conexao;
    }
}