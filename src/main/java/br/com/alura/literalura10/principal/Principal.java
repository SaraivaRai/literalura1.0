package br.com.alura.literalura10.principal;

import br.com.alura.literalura10.model.*;
import br.com.alura.literalura10.repository.LivroRepository;
import br.com.alura.literalura10.service.ConsumoApi;
import br.com.alura.literalura10.service.ConverteDados;

import java.time.Year;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private final LivroRepository repositorio;
    private final String URL = "https://gutendex.com/books?search=";
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    public Principal(LivroRepository repositorio) {
        this.repositorio = repositorio;

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
                    3 - Busca de autores por nomes                    
                    4 - Listagem de autores vivos no ano                    
                    5 - Listagem com base no lingua                   

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
                    todosOsLivrosPesquisados();
                    break;
                case 3:
                    autoresDosLivrosPesquisados();
                    break;
                case 4:
                    autoresVivosNoAno();
                    break;
                case 5:
                    BuscarPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    public void buscaLivroWeb() {
        System.out.println("Digite o título do livro desejado. ");
        var busca = scanner.nextLine();
        var json = consumo.obterDados(URL + busca.replace(" ", "+"));
        String objetoEncontrado = conversor.descompacta(json, "results");
        List<DadosLivro> dadosLivro = conversor.obterLista(objetoEncontrado, DadosLivro.class);
        if (dadosLivro.size() > 0) {
            Livro livro = new Livro(dadosLivro.get(0));
            repositorio.save(livro);
            System.out.println(livro);

        } else {
            System.out.println("Verifique se digitou corretamente.");
        }
    }

    public void todosOsLivrosPesquisados() {
        List<Livro> livros = repositorio.findAll();
        livros.forEach(System.out::println);
    }

    public void autoresDosLivrosPesquisados() {
        List<Autor> autores = repositorio.listaDeAutores();
        autores.forEach(System.out::println);
    }

    public void autoresVivosNoAno() {
        System.out.println("Digite o ano Desejado");
        var ano = scanner.nextInt();
        scanner.nextLine();

        List<Autor> autores = repositorio.findAutoresEmDeterminadoAno(Year.of(ano));
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor vivo neste ano de " + ano);
        } else {
            autores.forEach(System.out::println);
        }
    }


    private void BuscarPorIdioma() {
        System.out.println("""
                Digite o idioma para busca
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);
        String idioma = scanner.nextLine();
        List<Livro> livros = repositorio.findByIdioma(idioma);
        if (!livros.isEmpty()) {
            livros.forEach(System.out::println);
        } else {
            System.out.println("Não exite livros nesse idioma cadastrado");
        }
    }
}

