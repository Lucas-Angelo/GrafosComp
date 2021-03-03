package com.lucasangelo;

import com.lucasangelo.grafos.componentes.Grafo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        /*
            Arquivo: grafo.in

            Primeira linha - Metadados do grafo, sendo 3 divididos por espaços
            Primeiro dado: 0 para não direcionado, 1 para direcionado
            Segundo dado: 0 para não ponderado, 1 para ponderado
            Terceiro dado: quantidade de vértices

            Demais linhas - Vertices
            Quantidade de linhas é a quantida de arestas
            Exemplo da linha de arco entre os vértices 1 e 2: 1,2
            Exemplo da linha de arco entre os vértices ponderados 1 e 2 com peso 5: 1,2,5

        */
        System.out.println("Menu de opções para grafos:");
        System.out.println("1 - Para Direcionado Não-Ponderado");
        System.out.println("2 - Não-Direcionado Não-Ponderado");
        System.out.println("3 - Direcionado Ponderado");
        System.out.println("4 - Não-Direcionado Ponderado");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Informe uma opção: ");
        int opcao = Integer.parseInt(in.readLine());

        Grafo graph;
        switch (opcao) {
            case 1:
                graph = new Grafo("exemploDirecionadoNaoPonderado.in");
                break;
            case 2:
                graph = new Grafo("exemploNaoDirecionadoNaoPonderado.in");
                break;
            case 3:
                graph = new Grafo("exemploDirecionadoPonderado.in");
                break;
            case 4:
                graph = new Grafo("exemploNaoDirecionadoPonderado.in");
                break;
            default:
                graph = new Grafo();
        }
        graph.exibir();

    }

}
