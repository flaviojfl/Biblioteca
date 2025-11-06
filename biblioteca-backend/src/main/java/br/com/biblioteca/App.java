package br.com.biblioteca;

public class App {
    public static void main(String[] args) {
        
        System.out.println("========= CARREGANDO DADOS INICIAIS NO BANCO =========\n");

        
        ILivroDAO livroDAO = new LivroDAO();
        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        IEmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        IBibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();

        
        BibliotecaController controller = new BibliotecaController(
            livroDAO, 
            usuarioDAO, 
            emprestimoDAO, 
            bibliotecarioDAO
        );

        
        System.out.println(">>> Cadastrando 10 Livros de Eng. de Software...");
        controller.cadastrarLivro("978-0-13-235088-4", "Clean Code", "Robert C. Martin", "Eng. de Software", 2008);
        controller.cadastrarLivro("978-0-13-475759-9", "Refactoring", "Martin Fowler", "Eng. de Software", 2018);
        controller.cadastrarLivro("978-0-7356-1967-8", "Code Complete", "Steve McConnell", "Eng. de Software", 2004);
        controller.cadastrarLivro("978-0-201-63361-0", "Design Patterns (GoF)", "Erich Gamma, et al.", "Eng. de Software", 1994);
        controller.cadastrarLivro("978-0-201-83595-3", "The Mythical Man-Month", "Fred Brooks", "Gerência de Projetos", 1995);
        controller.cadastrarLivro("978-0-13-449416-6", "Clean Architecture", "Robert C. Martin", "Eng. de Software", 2017);
        controller.cadastrarLivro("978-0-321-12521-7", "Domain-Driven Design (DDD)", "Eric Evans", "Eng. de Software", 2003);
        controller.cadastrarLivro("978-0-13-595705-9", "The Pragmatic Programmer", "Andrew Hunt, David Thomas", "Programação", 2019);
        controller.cadastrarLivro("978-0-13-708107-3", "The Clean Coder", "Robert C. Martin", "Eng. de Software", 2011);
        controller.cadastrarLivro("978-0-321-33487-9", "Implementing Domain-Driven Design", "Vaughn Vernon", "Eng. de Software", 2013);


       
        System.out.println("\n>>> Cadastrando 4 Usuários...");
        controller.cadastrarUsuario("202401", "Flavio", "aluno");
        controller.cadastrarUsuario("202402", "Gabriel", "aluno");
        controller.cadastrarUsuario("202403", "HUgo", "aluno");
        controller.cadastrarUsuario("P1001", "Prof. Gisele Santana", "professor");

        
        System.out.println("\n>>> Cadastrando 1 Bibliotecário...");
        controller.cadastrarBibliotecario("B123", "Carlão", "Bibliotecário Chefe");

        System.out.println("\n========= CARGA DE DADOS FINALIZADA ==========");
    }
}