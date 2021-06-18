import java.util.Scanner;

import controlador.BuscaDisjuntos;
import modelo.Grafo;

public class App { 

    public static void main(String[] args) throws Exception {
        /*
            Arquivos. Exemplo: grafo.in

            Primeira linha: quantidade de vértices

            Demais linhas são os arcos
            Quantidade de linhas é a quantida de arestas
            Exemplo da linha de arco entre os vértices 1 e 2: 1,2

        */


        System.out.println("Dados do grafo capturados do arquivo grafo.in");
        System.out.println("Programa para encontrar o máximo caminhos disjuntos de um vértice de origem e um vértice de destino.");
        Grafo graph = new Grafo("grafo.in");
        BuscaDisjuntos bd = new BuscaDisjuntos(graph.getMatrizDeAdjacencia(), graph.getInfo());
        int origem = 0;
        int destino = 5;
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Digite a origem (Entre 0 e a quantidade de vértices-1): ");
            origem = Integer.parseInt(sc.nextLine());
            System.out.print("Digite o destino (Entre 0 e a quantidade de vértices-1): ");
            destino = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Insira apenas números inteiros positivos!");
        }
        sc.close();

        bd.buscarMaximoCaminhosDisjuntos(origem, destino);
    }
}
