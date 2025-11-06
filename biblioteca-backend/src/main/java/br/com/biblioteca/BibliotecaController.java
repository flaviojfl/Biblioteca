package br.com.biblioteca;

import java.time.LocalDate;

public class BibliotecaController {

    private final ILivroDAO livroDAO;
    private final IUsuarioDAO usuarioDAO;
    private final IEmprestimoDAO emprestimoDAO;
    private final IBibliotecarioDAO bibliotecarioDAO; // ADICIONADO

    // CONSTRUTOR MODIFICADO
    public BibliotecaController(ILivroDAO livroDAO, IUsuarioDAO usuarioDAO, IEmprestimoDAO emprestimoDAO, IBibliotecarioDAO bibliotecarioDAO) {
        this.livroDAO = livroDAO;
        this.usuarioDAO = usuarioDAO;
        this.emprestimoDAO = emprestimoDAO;
        this.bibliotecarioDAO = bibliotecarioDAO; // ADICIONADO
    }

    public void cadastrarLivro(String isbn, String titulo, String autor, String genero, int ano) {
        System.out.println("Controller: Cadastrando livro " + titulo);
        Livro novoLivro = new Livro(isbn, titulo, autor, genero, ano);
        livroDAO.cadastrarLivro(novoLivro);
    }

    public void cadastrarUsuario(String matricula, String nome, String tipo) {
        System.out.println("Controller: Cadastrando usuário " + nome);
        Usuario novoUsuario = new Usuario(matricula, nome, tipo);
        usuarioDAO.cadastrarUsuario(novoUsuario);
    }
    
    // MÉTODO NOVO
    public void cadastrarBibliotecario(String matricula, String nome, String cargo) {
        System.out.println("Controller: Cadastrando bibliotecário " + nome);
        Bibliotecario novoBibliotecario = new Bibliotecario(matricula, nome, cargo);
        bibliotecarioDAO.cadastrarBibliotecario(novoBibliotecario);
    }

    public void realizarEmprestimo(String isbn, String matricula) {
        System.out.println("Controller: Recebido pedido de empréstimo do livro " + isbn + " para " + matricula);
        Livro livro = livroDAO.buscarLivroPorIsbn(isbn);
        Usuario usuario = usuarioDAO.buscarUsuarioPorMatricula(matricula);

        if (livro == null || usuario == null) {
            System.err.println("Controller: Livro ou Usuário não encontrado.");
            return;
        }

        if (!livro.isDisponivel()) {
            System.err.println("Controller: O livro '" + livro.getTitulo() + "' não está disponível.");
            return;
        }

        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucaoPrevista = dataEmprestimo.plusDays(7);

        Emprestimo emprestimo = new Emprestimo(livro, usuario, dataEmprestimo, dataDevolucaoPrevista);
        
        emprestimoDAO.realizarEmprestimo(emprestimo);
        livroDAO.atualizarStatusLivro(isbn, false);
        
        System.out.println("Controller: Empréstimo de '" + livro.getTitulo() + "' realizado para '" + usuario.getNome() + "'.");
    }
}