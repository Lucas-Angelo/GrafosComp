package com.lucasangelo.grafos.estruturas;

import com.lucasangelo.grafos.componentes.Aresta;
import com.lucasangelo.grafos.componentes.GrafoInfo;

import java.util.ArrayList;

public class ListaDeAdjacencia {

    private int vertices;
    private ArrayList<ArrayList<Integer> > listaDeAdjacencia;
    private boolean direcionado;

    private void init(GrafoInfo grafoInfo, Aresta arestas[]){
        this.vertices = grafoInfo.getQtdVertices();
        this.direcionado = grafoInfo.isDirecionado();
        // Criando a lista com a quantidade de vértices
        this.listaDeAdjacencia = new ArrayList<>(vertices);

        // Criando a lista de possíveis arcos para cada vértice
        for (int i = 0; i < vertices; i++)
            listaDeAdjacencia.add(new ArrayList<>());

        // Gerando as relações dos arcos inseridos no arquivo
        for (int i=0; i<arestas.length; i++){
            criarArco(listaDeAdjacencia, arestas[i].getOrigem(), arestas[i].getDestino());
        }
    }

    public ListaDeAdjacencia(GrafoInfo grafoInfo, Aresta arestas[]) {
        init(grafoInfo, arestas);
    }

    // Adiciona os arcos/arestas
    private void criarArco(ArrayList<ArrayList<Integer> > adj, int origem, int destino)
    {
        if(this.direcionado) {
            adj.get(origem).add(destino);
            adj.get(destino).add(origem);
        } else {  // Sucessor
            adj.get(destino).add(origem);
        }
    }

    public void imprimir()
    {
        System.out.println();
        for (int i = 0; i < this.listaDeAdjacencia.size(); i++) {
            System.out.println("Lista de adjacência para o vértice " + i);
            for (int j = 0; j < this.listaDeAdjacencia.get(i).size(); j++) {
                if (j!=0)
                    if(direcionado)
                        System.out.print(" -> " + this.listaDeAdjacencia.get(i).get(j));
                    else
                        System.out.print(" - " + this.listaDeAdjacencia.get(i).get(j));
                else
                    System.out.print(this.listaDeAdjacencia.get(i).get(j));
            }
            System.out.println();
        }
    }
}
