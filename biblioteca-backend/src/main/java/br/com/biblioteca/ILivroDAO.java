package br.com.biblioteca;

public interface ILivroDAO {
    void cadastrarLivro(Livro livro);
    Livro buscarLivroPorIsbn(String isbn);
    void atualizarStatusLivro(String isbn, boolean disponivel);
}