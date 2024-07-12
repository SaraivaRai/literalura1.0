package br.com.alura.literalura10.principal;

import br.com.alura.literalura10.model.Autor;
import br.com.alura.literalura10.model.DadosResultado;
import br.com.alura.literalura10.model.Livro;
import br.com.alura.literalura10.repository.AutorRepository;
import br.com.alura.literalura10.repository.LivroRepository;
import br.com.alura.literalura10.service.ConsumoApi;
import br.com.alura.literalura10.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    Scanner scanner = new Scanner(System.in);

    private final String URL = "https://gutendex.com/books?search=";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private List<Livro> livros = new ArrayList<>();

    private List<Autor> autores = new ArrayList<>();

    @Autowired
    private LivroRepository livroRepositorio;

    @Autowired
    private AutorRepository autorRepositorio;

    public Principal(LivroRepository livroRepositorio, AutorRepository autorRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    \n
                   
                    ****************LITERALURA****************
                    ------------------ Menu ------------------

                    1 - Busca de livro por título
                    2 - Listagem de todos os livros
                    3 - Listagem com base no idioma
//                    4 - Listagem de todos os autores
//                    5 - Listagem de autores por livro
//                    6 - Listagem de autores vivos por ano
//                    7 - Listagem de quantidade de livros por idioma
                    8 - Listagem de autores vivos por ano DATABASE
//                    9 - Top 10 com mais downloads
                   10 - Busca de autores por nomes

                    0 - Sair
                    """;

            System.out.println(menu);
            System.out.println("O que vamos buscar? ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    buscaLivroWeb();
                    break;
                case 2:
                    listagemDeTodosOsLivros();
                    break;
                case 3:
                    listagemPorIdioma();
                    break;
//                case 4:
//                    listagemDeTodosOsAutores();
//                    break;
//                case 5:
//                    listagemDeAutoresPorLivro();
//                    break;
//                case 6:
//                    listagemDeAutoresVivosPorAno();
//                    break;
//                case 7:
//                    listagemQuantidadeDeLivrosPorIdioma();
//                    break;
//                case 8:
//                    listagemDeAutoresVivosPorAnoBD();
//                    break;
//                case 9:
//                    top10MaisDownloads();
//                    break;
                case 10:
                    buscaAutoresPorNome();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    public DadosResultado buscaDadosLivro() {
        System.out.println("Digite o título do livro desejado. ");
        var nomeLivro = scanner.nextLine();
        var json = consumo.obterDados(URL + nomeLivro.replace(" ", "%20"));

        DadosResultado dados = conversor.obterDados(json, DadosResultado.class);
        return dados;
    }

    public void buscaLivroWeb() {
        DadosResultado dados = buscaDadosLivro();
        Livro livro = new Livro(dados.resultado().get(0));
        livroRepositorio.save(livro);

        var autor = livro.getAutor().get(0).getAutor();
        System.out.println("\n");
        System.out.println("    ***** LIVRO *****    ");
        System.out.println("Título : " + livro.getTitulo());
        System.out.println("Autor : " + autor);
        System.out.println("Idioma : " + livro.getIdioma());
        System.out.println("Downloads : " + livro.getDownloads());
        System.out.println("*************************");
    }

    public void listagemDeTodosOsLivros() {
        System.out.println("Lista de livros");
        System.out.println("--------------------------------");

        livroRepositorio.findAll().forEach(l ->
                System.out.println(
                        "\n------------------------------------------" +
                                "\nTítulo: " + l.getTitulo() + "\n" +
                                "Autor: " + l.getAutor().get(0).getAutor() + "\n" +
                                "Idioma: " + l.getIdioma() + "\n" +
                                "Downloads: " + l.getDownloads() +
                                "\n------------------------------------------"
                )
        );
    }

    public void listagemPorIdioma() {
        System.out.println("Escolha o idioma: ");
        var idioma = scanner.nextLine();
//        List<Livro> livroIdioma = livroRepositorio.findByIdioma(idioma);
//        livroIdioma.forEach(l ->
//                System.out.println(
//                        "\n------------------------------------------" +
//                                "\nTítulo: " + l.getTitulo() + "\n" +
//                                "Autor: " + l.getAutor().get(0).getNome() + "\n" +
//                                "Idioma: " + l.getIdioma() + "\n" +
//                                "Downloads: " + l.getNumeroDownloads() +
//                                "\n------------------------------------------"
//                )
//        );

        if(idioma.equalsIgnoreCase("portugues")) {
            List<Livro> livroIdioma = livroRepositorio.findByIdioma("pt");
            livroIdioma.forEach(l ->
                    System.out.println(
                            "\n------------------------------------------" +
                                    "\nTítulo: " + l.getTitulo() + "\n" +
                                    "Autor: " + l.getAutor().get(0).getAutor() + "\n" +
                                    "Idioma: " + l.getIdioma() + "\n" +
                                    "Downloads: " + l.getDownloads() +
                                    "\n------------------------------------------"
                    )
            );
        } else if (idioma.equalsIgnoreCase("ingles")) {
            List<Livro> livroIdioma = livroRepositorio.findByIdioma("en");
            livroIdioma.forEach(l ->
                    System.out.println(
                            "\n------------------------------------------" +
                                    "\nTítulo: " + l.getTitulo() + "\n" +
                                    "Autor: " + l.getAutor().get(0).getAutor() + "\n" +
                                    "Idioma: " + l.getIdioma() + "\n" +
                                    "Downloads: " + l.getDownloads() +
                                    "\n------------------------------------------"
                    )
            );
        } else if (idioma.equalsIgnoreCase("espanhol")) {
            List<Livro> livroIdioma = livroRepositorio.findByIdioma("es");
            livroIdioma.forEach(l ->
                    System.out.println(
                            "\n------------------------------------------" +
                                    "\nTítulo: " + l.getTitulo() + "\n" +
                                    "Autor: " + l.getAutor().get(0).getAutor() + "\n" +
                                    "Idioma: " + l.getIdioma() + "\n" +
                                    "Downloads: " + l.getDownloads() +
                                    "\n------------------------------------------"
                    )
            );
        } else if (idioma.equalsIgnoreCase("italiano")) {
            List<Livro> livroIdioma = livroRepositorio.findByIdioma("it");
            livroIdioma.forEach(l ->
                    System.out.println(
                            "\n------------------------------------------" +
                                    "\nTítulo: " + l.getTitulo() + "\n" +
                                    "Autor: " + l.getAutor().get(0).getAutor() + "\n" +
                                    "Idioma: " + l.getIdioma() + "\n" +
                                    "Downloads: " + l.getDownloads() +
                                    "\n------------------------------------------"
                    )
            );
        } else if (idioma.equalsIgnoreCase("frances")) {
            List<Livro> livroIdioma = livroRepositorio.findByIdioma("fr");
            livroIdioma.forEach(l ->
                    System.out.println(
                            "\n------------------------------------------" +
                                    "\nTítulo: " + l.getTitulo() + "\n" +
                                    "Autor: " + l.getAutor().get(0).getAutor() + "\n" +
                                    "Idioma: " + l.getIdioma() + "\n" +
                                    "Downloads: " + l.getDownloads() +
                                    "\n------------------------------------------"
                    )
            );
        } else {
            System.out.println("Idioma inválido");
        }
    }

//    public void listagemDeTodosOsAutores() {
//        System.out.println("Lista de autores");
//        System.out.println("--------------------------------");
//        autores = autorRepositorio.findAll();
//
//        autores.forEach(a -> System.out.println(
//                "\n------------------------------------------" +
//                        "\nNome: " + a.getNome() + "\n" +
//                        "Data de Nascimento: " + a.getAnoNascimento() + "\n" +
//                        "Data de Falecimento: " + a.getAnoFalecimento() + "\n" +
//                        "------------------------------------------"
//        ));
//    }

//    public void listagemDeAutoresPorLivro() {
//        System.out.println("Escolha o livro: ");
//        var titulo = scanner.nextLine();
//        livros = livroRepositorio.findAll();
//
//        livros.stream()
//                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
//                .forEach(l -> l.getAutor().forEach(a -> System.out.println(
//                        "\nAutor: " + a.getNome() + "\n" +
//                                "Data de Nascimento: " + a.getAnoNascimento() + "\n" +
//                                "Data de Falecimento: " + a.getAnoFalecimento()
//                )));
//    }
//
//    public void listagemDeAutoresVivosPorAno() {
//        System.out.println("Digite o ano:");
//        var ano = scanner.nextInt();
//        autores = autorRepositorio.findAll();
//
//        autores.stream()
//                .filter(a -> a.getAnoFalecimento() != null && ano <= a.getAnoFalecimento())
//                .forEach(a -> System.out.println(
//                        "\nNome: " + a.getNome() + "\n" +
//                                "Data de Nascimento: " + a.getAnoNascimento() + "\n" +
//                                "Data de Falecimento: " + a.getAnoFalecimento()
//                ));
//    }

 //   public void listagemQuantidadeDeLivrosPorIdioma(){
//        System.out.println("Digite o idioma: ");
//        var idioma = scanner.nextLine();
//        //Integer quantidadeLivroIdioma = livroRepositorio.countByIdioma(idioma);
//
//        if(idioma.equalsIgnoreCase("portugues")) {
//            Integer quantidadeLivroIdioma = livroRepositorio.countByIdioma("pt");
//            System.out.println("Quantidade de livros com o idioma " + idioma + ": " + quantidadeLivroIdioma);
//        } else if (idioma.equalsIgnoreCase("ingles")) {
//            Integer quantidadeLivroIdioma = livroRepositorio.countByIdioma("en");
//            System.out.println("Quantidade de livros com o idioma " + idioma + ": " + quantidadeLivroIdioma);
//        } else if (idioma.equalsIgnoreCase("espanhol")) {
//            Integer quantidadeLivroIdioma = livroRepositorio.countByIdioma("es");
//            System.out.println("Quantidade de livros com o idioma " + idioma + ": " + quantidadeLivroIdioma);
//        } else if (idioma.equalsIgnoreCase("italiano")) {
//            Integer quantidadeLivroIdioma = livroRepositorio.countByIdioma("it");
//            System.out.println("Quantidade de livros com o idioma " + idioma + ": " + quantidadeLivroIdioma);
//        } else if (idioma.equalsIgnoreCase("frances")) {
//            Integer quantidadeLivroIdioma = livroRepositorio.countByIdioma("fr");
//            System.out.println("Quantidade de livros com o idioma " + idioma + ": " + quantidadeLivroIdioma);
//        } else {
//            System.out.println("Idioma inválido");
//        }
//    }

//    public void listagemDeAutoresVivosPorAnoBD() {
//        System.out.println("Digite o ano:");
//        var ano = scanner.nextInt();
//        List<Autor> autores = autorRepositorio.findByAnoDeFalecimentoLessThanEqual(ano);
//        autores.forEach(a -> System.out.println(
//                "\nNome: " + a.getNome() + "\n" +
//                        "Data de Nascimento: " + a.getAnoNascimento() + "\n" +
//                        "Data de Falecimento: " + a.getAnoFalecimento()
//        ));
//    }
//
//    public void top10MaisDownloads() {
//        List<Livro> livros = livroRepositorio.findTop10ByOrderByNumeroDownloadsDesc();
//        livros.forEach(l -> System.out.println(
//                "\n------------------------------------------" +
//                        "\nTítulo: " + l.getTitulo() + "\n" +
//                        "Autor: " + l.getAutor().get(0).getNome() + "\n" +
//                        "Idioma: " + l.getIdioma() + "\n" +
//                        "Downloads: " + l.getDownloads() +
//                        "\n------------------------------------------"
//        ));
//    }

    public void buscaAutoresPorNome() {
        System.out.println("Digite o autor do autor: ");
        var nomeAutor = scanner.nextLine();
        List<Autor> autores = autorRepositorio.findByNomeContainingIgnoreCase(nomeAutor);
        autores.forEach(a -> System.out.println(
                "\nNome: " + a.getAutor() + "\n" +
                        "Data de Nascimento: " + a.getAnoDeNascimento() + "\n" +
                        "Data de Falecimento: " + a.getAnoDeFalecimento()
        ));
    }
}