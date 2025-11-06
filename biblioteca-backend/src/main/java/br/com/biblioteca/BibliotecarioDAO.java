package br.com.biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BibliotecarioDAO implements IBibliotecarioDAO {

    @Override
    public void cadastrarBibliotecario(Bibliotecario bibliotecario) {
        String sql = "INSERT INTO bibliotecario (matricula, nome, cargo) VALUES (?, ?, ?)";
        
        Connection conexao = null;
        PreparedStatement pstmt = null;
        
        try {
            conexao = ConexaoPostgreSQL.obterConexao();
            pstmt = conexao.prepareStatement(sql);
            
            pstmt.setString(1, bibliotecario.getMatricula());
            pstmt.setString(2, bibliotecario.getNome());
            pstmt.setString(3, bibliotecario.getCargo());

            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar bibliotecário:");
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}