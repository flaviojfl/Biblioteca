package br.com.biblioteca;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmprestimoDAO implements IEmprestimoDAO {
    
    @Override
    public void realizarEmprestimo(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (livro_isbn, usuario_matricula, data_emprestimo, data_devolucao_prevista, status) VALUES (?, ?, ?, ?, ?)";
        
        Connection conexao = null;
        PreparedStatement pstmt = null;
        
        try {
            conexao = ConexaoPostgreSQL.obterConexao();
            pstmt = conexao.prepareStatement(sql);
            
            pstmt.setString(1, emprestimo.getLivro().getIsbn());
            pstmt.setString(2, emprestimo.getUsuario().getMatricula());
            pstmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            pstmt.setDate(4, Date.valueOf(emprestimo.getDataDevolucaoPrevista()));
            pstmt.setString(5, emprestimo.getStatus());

            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao realizar empréstimo:");
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