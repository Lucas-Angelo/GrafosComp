package com.lucasangelo.grafos.estruturas;

import com.lucasangelo.grafos.componentes.Aresta;
import com.lucasangelo.grafos.componentes.GrafoInfo;

public class MatrizDeAdjacencia {

    private int qtdVertices;
    private int matrizAdjacente[][];
    private Aresta arestas[];
    // private boolean direcionada; // Não tem como definir direção em matriz de adjacência

    private void init(GrafoInfo grafoInfo, Aresta arestas[]){
        this.qtdVertices = grafoInfo.getQtdVertices();
        this.matrizAdjacente = new int[grafoInfo.getQtdVertices()][grafoInfo.getQtdVertices()];
        this.arestas = arestas;

        gerarMatrizDeAdjacencia();
    }

    public MatrizDeAdjacencia(GrafoInfo grafoInfo, Aresta arestas[]){
        init(grafoInfo, arestas);
    }

    private void gerarMatrizDeAdjacencia() {
        for (int i = 0; i < this.qtdVertices; i++) {
            for (int j = 0; j < this.qtdVertices; j++) {
                boolean insere = false;
                for (int k = 0; k < arestas.length; k++) {
                    if (arestas[k].getOrigem() == i && arestas[k].getDestino() == j) {
                        insere = true;
                    }
                }

                if (insere)
                    this.matrizAdjacente[i][j] = 1;
                else
                    this.matrizAdjacente[i][j] = 0;
            }
        }
    }

    public void imprimir() {
        System.out.println();
        System.out.println("Matriz de Adjacência:");
        for (int i = 0; i < qtdVertices; i++) {
            for (int j = 0; j < qtdVertices; j++) {
                System.out.print(matrizAdjacente[i][j] + "  ");
            }
            System.out.println();
        }
    }


}
