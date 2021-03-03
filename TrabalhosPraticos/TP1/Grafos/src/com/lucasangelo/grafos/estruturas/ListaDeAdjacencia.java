package com.lucasangelo.grafos.estruturas;

import com.lucasangelo.grafos.componentes.Aresta;
import com.lucasangelo.grafos.componentes.Grafo;
import com.lucasangelo.grafos.componentes.GrafoInfo;

import java.util.ArrayList;

public class ListaDeAdjacencia {

    private int vertices;
    private ArrayList<ArrayList<Integer> > listaDeAdjacencia;
    private boolean direcionado;
    private boolean ponderado;

    private int[] arcoOrigem;
    private int[][] arcoDestino;

    private void init(GrafoInfo grafoInfo, Aresta arestas[]){
        this.vertices = grafoInfo.getQtdVertices();
        this.direcionado = grafoInfo.isDirecionado();
        this.ponderado = grafoInfo.isPonderado();

        arcoDestino = new int[arestas.length][2];
        arcoOrigem = new int[this.vertices+1];
        int indexArcoDestino = 0;
        for ( int i=0; i<this.vertices; i++ ){
            arcoOrigem[i] = indexArcoDestino;
            for ( int j=0; j<arestas.length; j++ ){
                if (arestas[i].getOrigem() == j){
                    arcoDestino[indexArcoDestino][0] = arestas[j].getDestino();
                    arcoDestino[indexArcoDestino][1] = arestas[j].getPeso();
                    indexArcoDestino++;
                }
            }
        }
        arcoOrigem[this.vertices] = indexArcoDestino;

    }

    public ListaDeAdjacencia(Grafo grafo) {
        init(grafo.getInfo(), grafo.getArestas());
    }

    public void imprimir(){

        System.out.println();
        for (int i=0;i<arcoOrigem.length;i++){
            System.out.print(arcoOrigem[i] + " - ");
        }
        System.out.println();
        for (int i=0;i<arcoDestino.length;i++){
            System.out.print(arcoDestino[i][0] + " - ");
        }

        System.out.println("\nLista de adjacência");
        for ( int i=0; i<arcoOrigem.length-1;i++ ){
            System.out.print(i+1 + " |");
            for (int j=arcoOrigem[i]; j<arcoOrigem[i+1]; j++){
                System.out.print(" -> " + arcoDestino[j][0]);
                if (this.ponderado)
                    System.out.print(" (" + arcoDestino[j][1] + ")");
                
            }
            System.out.println();
        }
    }
}
