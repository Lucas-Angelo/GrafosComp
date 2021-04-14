package com.lucasangelo.grafos.estruturas;

import com.lucasangelo.grafos.componentes.Aresta;
import com.lucasangelo.grafos.componentes.Grafo;
import com.lucasangelo.grafos.componentes.GrafoInfo;

public class ListaDeAdjacencia {

    private int vertices;
    private boolean direcionado;
    private boolean ponderado;

    private int[] arcoOrigem;
    private int[] arcoDestino;
    private int[] arcoPeso;

    private void init(GrafoInfo grafoInfo, Aresta arestas[]){
        this.vertices = grafoInfo.getQtdVertices();
        this.direcionado = grafoInfo.isDirecionado();
        this.ponderado = grafoInfo.isPonderado();

        arcoDestino = new int[arestas.length];
        arcoPeso = new int[arestas.length];
        arcoOrigem = new int[this.vertices+1];
        int indexArcoDestino = 0;
        for ( int i=0; i<this.vertices; i++ ){
            arcoOrigem[i] = indexArcoDestino;
            for ( int j=0; j<arestas.length; j++ ){
                if (arestas[j].getOrigem() == i){
                    arcoDestino[indexArcoDestino] = arestas[j].getDestino();
                    arcoPeso[indexArcoDestino] = arestas[j].getPeso();
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

        System.out.println("\nLista de adjacência");
        for ( int i=0; i<arcoOrigem.length-1;i++ ){
            System.out.print(i+1 + " |");
            for (int j=arcoOrigem[i]; j<arcoOrigem[i+1]; j++){
                if(this.direcionado)
                    System.out.print(" -> " + (arcoDestino[j]+1));
                else
                    System.out.print(" - " + (arcoDestino[j]+1));
                if (this.ponderado)
                    System.out.print(" (Peso: " + arcoPeso[j] + ")");
                
            }
            System.out.println();
        }
    }
}
