package com.lucasangelo;

import com.lucasangelo.classes.*;

public class Main {

    public static void main(String[] args) throws Exception {

        /*
            Arquivo: grafo.in
            Primeira linha: 0 para não direcionado, 1 para direcionado
            Segunda linha: 0 para não ponderado, 1 para ponderado
            Terceira linha: quantidade de vértices
        */

        int[] dadosGrafo = capturarDadosGrafo();
        boolean direcionado = (dadosGrafo[0]==1) ? true : false;
        boolean ponderado = (dadosGrafo[1]==1) ? true : false;
        int qtdVertices = dadosGrafo[2];

        if(qtdVertices<=0)
            throw new Exception("Não é possível gerar grafos sem vértices");

        if (direcionado) System.out.println("Grafo direcionado.");
        else System.out.println("Grafo não direcionado.");
        if (ponderado) System.out.println("Grafo ponderado.");
        else System.out.println("Grafo não ponderado.");
        System.out.println("Quantidade de vértices: " + qtdVertices + ".");

        /*
            Arquivo: arestas.in
            Quantidade de linhas é a quantida de arestas
            Exemplo da linha de arco entre os vértices 1 e 2: 1,2
        */
        Aresta arestas[] = capturarArestas(qtdVertices, direcionado);

        // Melhor uma matriz adjacente para nao direcionado
        if(!direcionado && !ponderado) {
            gerarGrafo(qtdVertices, arestas, 2);
        }

    }

    private static int[] capturarDadosGrafo () {
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();
        leitura.abrirArquivo("grafo.in");

        // Capturando se é direcionado e/ou ponderado; e a quantidade de vértices
        String linha = new String("");
        int qtdDados=0;
        while (linha != null){
            linha = leitura.ler();
            if(linha!=null)
                qtdDados++;
        }
        leitura.fecharArquivo();

        leitura.abrirArquivo("grafo.in");
        String dadosStr[] = new String[qtdDados];
        int i=0;
        while (i<qtdDados){
            dadosStr[i] = leitura.ler();
            i++;
        }
        leitura.fecharArquivo();

        int dados[] = new int[qtdDados];
        for(i=0; i<qtdDados; i++){
            dados[i] = Integer.parseInt(dadosStr[i]);
        }

        return dados;
    }

    private static Aresta[] capturarArestas (int vertices, boolean direcionado) throws Exception {
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();
        leitura.abrirArquivo("arestas.in");

        String linha = new String("");
        int qtdArestas=0;
        while (linha != null){
            linha = leitura.ler();
            if(linha!=null)
                qtdArestas++;
        }
        leitura.fecharArquivo();

        int maximoDeArestas = ((vertices -1) * vertices)/2;
        if(qtdArestas>maximoDeArestas && !direcionado)
            throw new Exception(String.format("Quantidade de arestas inválidas! Máximo para um grafo não direcionado de %d vertices é %d", vertices, maximoDeArestas));

        leitura.abrirArquivo("arestas.in");
        String arestasStr[] = new String[qtdArestas];
        int i=0;
        while (i<qtdArestas){
            arestasStr[i] = leitura.ler();
            i++;
        }
        leitura.fecharArquivo();

        Aresta arestas[] = new Aresta[qtdArestas];

        int dados[] = new int[qtdArestas];
        for(i=0; i<qtdArestas; i++){
            String[] dadosLinha = arestasStr[i].split(",", 2);
            int origem = Integer.parseInt(dadosLinha[0])-1;
            int destino = Integer.parseInt(dadosLinha[1])-1;
            arestas[i] = new Aresta(origem, destino);
        }

        return arestas;
    }

    private static void gerarGrafo (int qtdVertices, Aresta[] arestas, int opcao) {

        if (opcao==1){ // Matriz de incidencia
            criarMatrizIncidencia(qtdVertices, arestas);
        }
        else if(opcao==2) { // Matriz de adjacencia
            criarMatrizAdjacencia(qtdVertices, arestas);
        }
        else if (opcao==3) { // Lista de adjacencia
            criarListaDeAdjacencia(qtdVertices, arestas);
        }

    }

    private static void criarMatrizAdjacencia(int qtdVertices, Aresta[] arestas) {
        System.out.println("Matriz de Adjacência:");
        int matrizAdjacente[][] = new int[qtdVertices][qtdVertices];
        for (int i = 0; i < qtdVertices; i++) {
            for (int j = 0; j < qtdVertices; j++) {
                boolean insere = false;
                for (int k = 0; k < arestas.length; k++) {
                    if (arestas[k].getOrigem() == i && arestas[k].getDestino() == j) {
                        insere = true;
                    }
                }
                if (insere)
                    matrizAdjacente[i][j] = 1;
                else
                    matrizAdjacente[i][j] = 0;
            }
        }
        for (int i = 0; i < qtdVertices; i++) {
            for (int j = 0; j < qtdVertices; j++) {
                System.out.print(matrizAdjacente[i][j] + "  ");
            }
            System.out.println();
        }
    }

    private static void criarMatrizIncidencia(int qtdVertices, Aresta[] arestas) {
//        System.out.println("Matriz de Incidencia:");
//        int matrizAdjacente[][] = new int[qtdVertices][qtdVertices];
//        for (int i = 0; i < qtdVertices; i++) {
//            for (int j = 0; j < qtdVertices; j++) {
//                boolean insere = false;
//                for (int k = 0; k < arestas.length; k++) {
//                    if (arestas[k].getOrigem() == i && arestas[k].getDestino() == j) {
//                        insere = true;
//                    }
//                }
//                if (insere)
//                    matrizAdjacente[i][j] = 1;
//                else
//                    matrizAdjacente[i][j] = 0;
//            }
//        }
//        for (int i = 0; i < qtdVertices; i++) {
//            for (int j = 0; j < qtdVertices; j++) {
//                System.out.print(matrizAdjacente[i][j] + "  ");
//            }
//            System.out.println();
//        }
    }

    private static void criarListaDeAdjacencia(int qtdVertices, Aresta[] arestas) {
        System.out.println("Lista de Adjacência:");
    }

}
