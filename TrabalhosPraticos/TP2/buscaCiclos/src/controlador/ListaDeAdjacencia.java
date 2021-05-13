package controlador;

import modelo.Aresta;
import modelo.Ciclo;
import modelo.GrafoInfo;

public class ListaDeAdjacencia {

    private int vertices;

    private int[] arcoOrigem;
    private int[] arcoDestino;

    private Ciclo ciclos;

    private void init(GrafoInfo grafoInfo, Aresta[] arestas){
        this.vertices = grafoInfo.getQtdVertices();

        int arcosLength = arestas.length*2;

        this.arcoDestino = new int[arcosLength];
        this.arcoOrigem = new int[this.vertices+1];
        int indexArcoDestino = 0;
        for ( int i=1; i<=this.vertices; i++ ){
            arcoOrigem[i-1] = indexArcoDestino;
            for ( int j=0; j<arestas.length; j++ ){
                if (arestas[j].getOrigem() == i || (arestas[j].getDestino() == i) ){
                    int destino = (arestas[j].getOrigem() == i) ? arestas[j].getDestino() : arestas[j].getOrigem(); 
                    arcoDestino[indexArcoDestino] = destino;
                    indexArcoDestino++;
                }
            }
        }
        arcoOrigem[this.vertices] = indexArcoDestino;
        this.ciclos = new Ciclo();
    }

    public ListaDeAdjacencia(GrafoInfo info, Aresta[] arestas) {
        this.init(info, arestas);
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append("\nLista de adjacência\n");
        for ( int i=0; i<arcoOrigem.length-1;i++ ){
            builder.append(i+1 + " |");
            for (int j=arcoOrigem[i]; j<arcoOrigem[i+1]; j++){
                builder.append(" - " + (arcoDestino[j]));
            }
            builder.append("\n");
        }
        
        return builder.toString();
    }

    public int[] getAdjacentes(int vertice){
        vertice -= 1;
        if (vertice>=this.vertices)
            throw new Error("Vertice nao existe no grafo");
        int inicio = this.arcoOrigem[vertice];
        int fim = this.arcoOrigem[vertice+1];

        int size = fim - inicio;
        int[] adjacentes = new int[size];

        if (size > 0){
            for (int arco=inicio, index=0; arco<fim ; arco++, index++)
                adjacentes[index] = this.arcoDestino[arco];
        }

        return adjacentes;
        
    }

    public Ciclo getCiclos() {
        return ciclos;
    }

}
