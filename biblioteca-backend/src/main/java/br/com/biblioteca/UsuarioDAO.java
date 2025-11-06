package br.com.biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO implements IUsuarioDAO {
    
    @Override
    public void cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (matricula, nome, tipo) VALUES (?, ?, ?)";
        
        Connection conexao = null;
        PreparedStatement pstmt = null;

        try {
            conexao = ConexaoPostgreSQL.obterConexao();
            pstmt = conexao.prepareStatement(sql);
            
            pstmt.setString(1, usuario.getMatricula());
            pstmt.setString(2, usuario.getNome());
            pstmt.setString(3, usuario.getTipo());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário:");
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
    public Usuario buscarUsuarioPorMatricula(String matricula) {
        String sql = "SELECT * FROM usuario WHERE matricula = ?";
        
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            conexao = ConexaoPostgreSQL.obterConexao();
            pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, matricula);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                    rs.getString("matricula"),
                    rs.getString("nome"),
                    rs.getString("tipo")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário:");
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
        return usuario;
    }
}