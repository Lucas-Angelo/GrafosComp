package com.lucasangelo;

import com.lucasangelo.arquivo.*;
import com.lucasangelo.grafos.componentes.Aresta;
import com.lucasangelo.grafos.componentes.GrafoInfo;
import com.lucasangelo.grafos.estruturas.*;

public class Main {

    public static void main(String[] args) throws Exception {
        /*
            Arquivo: grafo.in
            Primeira linha: 0 para não direcionado, 1 para direcionado
            Segunda linha: 0 para não ponderado, 1 para ponderado
            Terceira linha: quantidade de vértices
        */
        GrafoInfo grafoInfo = new GrafoInfo();

        /*
            Arquivo: arestas.in
            Quantidade de linhas é a quantida de arestas
            Exemplo da linha de arco entre os vértices 1 e 2: 1,2
        */
        Aresta arestas[] = DadosDeArquivos.capturarArestas(grafoInfo.getQtdVertices(), grafoInfo.isDirecionado());

        // Procurando a melhor estrutura
        if(!grafoInfo.isDirecionado()) {
            if(!grafoInfo.isPonderado()) { // 2 - Não-direcionado e não-ponderado. Matriz de adjacência.
                /*
                    Pois uma matriz de adjacência não tem
                    como mostrar direção e nem o ponderar as arestas direto na matriz.
                    Consegue cumprir a função, porém, tem muitos zeros...
                */
                MatrizDeAdjacencia matrizDeAdjacencia = new MatrizDeAdjacencia(grafoInfo, arestas);
                matrizDeAdjacencia.imprimir();
            } else { // 4 - Não-direcionado e ponderado.
                ListaDeAdjacencia listaDeAdjacencia = new ListaDeAdjacencia(grafoInfo, arestas);
                listaDeAdjacencia.imprimir();
            }
        } else {
            if(grafoInfo.isPonderado()){ // 3 - Direcionado e ponderado.
                ListaDeAdjacencia listaDeAdjacencia = new ListaDeAdjacencia(grafoInfo, arestas);
                listaDeAdjacencia.imprimir();
            } else { // 1 - Direcionado e não-ponderado. Matriz de indicência.

            }
        }

    }

}