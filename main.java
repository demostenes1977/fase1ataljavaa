import java.util.Comparator;
import java.util.Scanner;

class Livro {
    String titulo;
    String autor;
    int anoPublicacao;

    public Livro(String titulo, String autor, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }
}

class No {
    Livro livro;
    No proximo;

    public No(Livro livro) {
        this.livro = livro;
        this.proximo = null;
    }
}

class Biblioteca {
    No primeiro;

    public Biblioteca() {
        this.primeiro = null;
    }

    public void adicionarLivro(Livro livro) {
        No novoNo = new No(livro);
        novoNo.proximo = primeiro;
        primeiro = novoNo;
    }

    public void listarLivros() {
        No atual = primeiro;
        while (atual != null) {
            System.out.println("Título: " + atual.livro.titulo);
            System.out.println("Autor: " + atual.livro.autor);
            System.out.println("Ano de Publicação: " + atual.livro.anoPublicacao);
            System.out.println("---------------------");
            atual = atual.proximo;
        }
    }

    public void ordenarPorTitulo() {
        ordenar(primeiro, null, (l1, l2) -> l1.titulo.compareToIgnoreCase(l2.titulo));
    }

    public void ordenarPorAutor() {
        ordenar(primeiro, null, (l1, l2) -> l1.autor.compareToIgnoreCase(l2.autor));
    }

    private No ordenar(No inicio, No fim, Comparator<Livro> comparador) {
        if (inicio == fim || inicio == null || fim == null) {
            return inicio;
        }

        No pivo = particionar(inicio, fim, comparador);

        ordenar(inicio, pivo.proximo, comparador);
        ordenar(pivo.proximo, fim, comparador);

        return inicio;
    }

    private No particionar(No inicio, No fim, Comparator<Livro> comparador) {
        Livro pivo = fim.livro;
        No i = inicio;
        No j = inicio;

        while (j != fim) {
            if (comparador.compare(j.livro, pivo) <= 0) {
                swap(i, j);
                i = i.proximo;
            }
            j = j.proximo;
        }

        swap(i, fim);

        return i;
    }

    private void swap(No no1, No no2) {
        Livro temp = no1.livro;
        no1.livro = no2.livro;
        no2.livro = temp;
    }
}

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Adicionar livro");
            System.out.println("2 - Listar livros");
            System.out.println("3 - Ordenar por título");
            System.out.println("4 - Ordenar por autor");
            System.out.println("5 - Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir o caractere de nova linha

            switch (escolha) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Ano de publicação: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();
                    biblioteca.adicionarLivro(new Livro(titulo, autor, ano));
                    break;
                case 2:
                    biblioteca.listarLivros();
                    break;
                case 3:
                    biblioteca.ordenarPorTitulo();
                    break;
                case 4:
                    biblioteca.ordenarPorAutor();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    @Override
    public String toString() {
        return "Main []";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }
}