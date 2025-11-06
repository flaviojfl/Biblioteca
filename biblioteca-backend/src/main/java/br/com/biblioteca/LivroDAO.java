package br.com.biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivroDAO implements ILivroDAO {

    @Override
    public void cadastrarLivro(Livro livro) {
        String sql = "INSERT INTO livro (isbn, titulo, autor, genero, ano, disponivel) VALUES (?, ?, ?, ?, ?, ?)";
        
        Connection conexao = null;
        PreparedStatement pstmt = null;
        
        try {
            conexao = ConexaoPostgreSQL.obterConexao();
            pstmt = conexao.prepareStatement(sql);
            
            pstmt.setString(1, livro.getIsbn());
            pstmt.setString(2, livro.getTitulo());
            pstmt.setString(3, livro.getAutor());
            pstmt.setString(4, livro.getGenero());
            pstmt.setInt(5, livro.getAno());
            pstmt.setBoolean(6, livro.isDisponivel());

            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar livro:");
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

    @Override
    public Livro buscarLivroPorIsbn(String isbn) {
        String sql = "SELECT * FROM livro WHERE isbn = ?";
        
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Livro livro = null;
        
        try {
            conexao = ConexaoPostgreSQL.obterConexao();
            pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, isbn);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                livro = new Livro(
                    rs.getString("isbn"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("genero"),
                    rs.getInt("ano")
                );
                livro.setDisponivel(rs.getBoolean("disponivel"));
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livro:");
            e.printStackTrace();
        } finally {
             try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return livro;
    }
    
    @Override
    public void atualizarStatusLivro(String isbn, boolean disponivel) {
        String sql = "UPDATE livro SET disponivel = ? WHERE isbn = ?";
        
        Connection conexao = null;
        PreparedStatement pstmt = null;
        
        try {
            conexao = ConexaoPostgreSQL.obterConexao();
            pstmt = conexao.prepareStatement(sql);

            pstmt.setBoolean(1, disponivel);
            pstmt.setString(2, isbn);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar status do livro:");
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