package com.lucasangelo.grafos.estruturas;

import com.lucasangelo.grafos.componentes.Aresta;
import com.lucasangelo.grafos.componentes.GrafoInfo;

public class MatrizDeIncidencia {

    private int qtdVertices;
    private int matrizDeIncidencia[][];
    private Aresta arestas[];
    private boolean ponderada;
    // private boolean direcionada; // Não tem como definir direção em matriz de adjacência

    private void init(GrafoInfo grafoInfo, Aresta arestas[]){
        this.qtdVertices = grafoInfo.getQtdVertices();
        this.matrizDeIncidencia = new int[grafoInfo.getQtdVertices()][arestas.length];
        this.ponderada = grafoInfo.isPonderado();
        this.arestas = arestas;

        gerarMatrizDeAdjacencia();
    }

    public MatrizDeIncidencia(GrafoInfo grafoInfo, Aresta arestas[]){
        init(grafoInfo, arestas);
    }

    private void gerarMatrizDeAdjacencia() {
        int a, b;
        for (int i = 0; i < this.arestas.length; i++) {
            matrizDeIncidencia[this.arestas[i].getOrigem()][i] = 1;
            matrizDeIncidencia[this.arestas[i].getDestino()][i] = -1;
        }
    }

    public void imprimir() {
        System.out.println("\nMatriz de Incidência:");
        for (int i = 0; i < qtdVertices; i++) {
            for (int j = 0; j < arestas.length; j++) {
                System.out.print(matrizDeIncidencia[i][j] + "     ");
            }
            System.out.println();
        }
    }


}
