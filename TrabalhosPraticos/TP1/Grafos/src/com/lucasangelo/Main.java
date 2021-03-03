package com.lucasangelo;

import com.lucasangelo.grafos.componentes.Grafo;

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
        Grafo graph = new Grafo("grafo.in");
        graph.exibirGrafo();
        graph.exibirGrafo(0);
        graph.exibirGrafo(1);
        graph.exibirGrafo(2);

    }

}

// Explicação da condicional procurando a melhor estrutura:

/*
*
*   Grafo não-direcionado e não-ponderado, foi esolhida a Matriz de Adjacência:
*       Pois uma matriz de adjacência não tem como mostrar
*       direção e nem o ponderar as arestas direto na matriz.
*       Consegue cumprir a função, sendo a estrutura mais simples, porém, tem muitos zeros...
*
*   Grafo não-direcional e ponderado, foi escolhida a Lista de Adjacência:
*       Pois com a lista é possível imprimir a ponderação
*       além dela funcionar normalmente sem direcionamento,
*       colocando - ou -> pra diferenciar.
*
*   Grafo direcionado e ponderado, foi escolhida a Lista de Adjacência:
*       Pois, a lista ela funciona normalmente
*       com direcionamento devido ao fato de
*       poder colocar a representação ->
*       além, de poder especificar o
*       nome para o arco, poderando.
*
*   Grafo direcionado e não ponderado, foi esolhida a Matriz de Adjacência:
*       Pois com ela é possível representar
*       o direcionamento por meio de 1 e -1,
*       porém não é possível representar os
*       arcos ponderando.
*
*/