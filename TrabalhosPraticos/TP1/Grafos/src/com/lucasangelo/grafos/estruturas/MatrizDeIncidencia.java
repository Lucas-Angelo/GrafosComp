package com.lucasangelo.grafos.estruturas;

import com.lucasangelo.grafos.componentes.Aresta;
import com.lucasangelo.grafos.componentes.Grafo;
import com.lucasangelo.grafos.componentes.GrafoInfo;

public class MatrizDeIncidencia {

    private int qtdVertices;
    private int matrizDeIncidencia[][];
    private int pesos[];
    private Aresta arestas[];
    private boolean ponderada;
    private boolean direcionado;

    private void init(GrafoInfo grafoInfo, Aresta arestas[]){
        this.qtdVertices = grafoInfo.getQtdVertices();
        this.matrizDeIncidencia = new int[grafoInfo.getQtdVertices()][arestas.length];
        this.pesos = new int[arestas.length];
        this.ponderada = grafoInfo.isPonderado();
        this.direcionado = grafoInfo.isDirecionado();
        this.arestas = arestas;

        gerarMatrizDeIncidencia();
    }

    public MatrizDeIncidencia(Grafo grafo){
        init(grafo.getInfo(), grafo.getArestas());
    }

    private void gerarMatrizDeIncidencia() {
        int negativo = direcionado ? -1 : 1 ;
        for (int i = 0; i < this.arestas.length; i++) {
            matrizDeIncidencia[this.arestas[i].getOrigem()][i] = 1;
            matrizDeIncidencia[this.arestas[i].getDestino()][i] = negativo;
            pesos[i] = this.arestas[i].getPeso();
        }
    }

    public void imprimir() {
        System.out.println("\nMatriz de Incidência:");
        if (this.ponderada){
                System.out.print("Peso-> ");
            for (int i=0;i<pesos.length;i++){
                System.out.print(pesos[i] + "     ");
            }
            System.out.println();
        }
        for (int i = 0; i < qtdVertices; i++) {
            if (this.ponderada)
                System.out.print("       ");
            for (int j = 0; j < arestas.length; j++) {
                System.out.print(matrizDeIncidencia[i][j] + "     ");
            }
            System.out.println();
        }
    }


}
