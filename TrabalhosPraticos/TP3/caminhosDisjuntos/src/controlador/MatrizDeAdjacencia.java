package controlador;

import modelo.Aresta;
import modelo.GrafoInfo;

public class MatrizDeAdjacencia {

    private int qtdVertices;
    private int matrizAdjacente[][];
    private Aresta arestas[];

    private void init(GrafoInfo grafoInfo, Aresta arestas[]){
        this.qtdVertices = grafoInfo.getQtdVertices();
        this.matrizAdjacente = new int[grafoInfo.getQtdVertices()][grafoInfo.getQtdVertices()];
        this.arestas = arestas;

        gerarMatrizDeAdjacencia();
    }

    public MatrizDeAdjacencia(GrafoInfo info, Aresta arestas[]){
        init(info, arestas);
    }

    private void gerarMatrizDeAdjacencia() {
        for (int i = 0; i < this.qtdVertices; i++) {
            for (int j = 0; j < this.qtdVertices; j++) {
                boolean insere = false;
                for (int k = 0; k < arestas.length; k++) {
                    if ((arestas[k].getOrigem() == i && arestas[k].getDestino() == j)) {
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

    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("Matriz de Adjacência:\n");
        for (int i = 0; i < qtdVertices; i++) {
            for (int j = 0; j < qtdVertices; j++) {
                sb.append(matrizAdjacente[i][j] + "  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
