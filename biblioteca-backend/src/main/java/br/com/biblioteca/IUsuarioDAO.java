package br.com.biblioteca;

public interface IUsuarioDAO {
    void cadastrarUsuario(Usuario usuario);
    Usuario buscarUsuarioPorMatricula(String matricula);
}