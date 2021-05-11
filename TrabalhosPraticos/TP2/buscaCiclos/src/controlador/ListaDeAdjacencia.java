package controlador;

import java.util.ArrayList;

import arquivo.ArquivoTextoLeitura;
import modelo.Aresta;
import modelo.GrafoInfo;

public class ListaDeAdjacencia {

    private int vertices;
    private boolean direcionado;
    private boolean ponderado;

    private int[] arcoOrigem;
    private int[] arcoDestino;
    private int[] arcoPeso;

    private void init(GrafoInfo grafoInfo, Aresta[] arestas){
        this.vertices = grafoInfo.getQtdVertices();
        this.direcionado = grafoInfo.isDirecionado();
        this.ponderado = grafoInfo.isPonderado();

        int arcosLength = direcionado ? arestas.length : arestas.length*2 ;

        this.arcoDestino = new int[arcosLength];
        this.arcoPeso = new int[arcosLength];
        this.arcoOrigem = new int[this.vertices+1];
        int indexArcoDestino = 0;
        for ( int i=1; i<=this.vertices; i++ ){
            arcoOrigem[i-1] = indexArcoDestino;
            for ( int j=0; j<arestas.length; j++ ){
                if (arestas[j].getOrigem() == i || (arestas[j].getDestino() == i && !this.direcionado) ){
                    int destino = (arestas[j].getOrigem() == i) ? arestas[j].getDestino() : arestas[j].getOrigem(); 
                    arcoDestino[indexArcoDestino] = destino;
                    arcoPeso[indexArcoDestino] = arestas[j].getPeso();
                    indexArcoDestino++;
                }
            }
        }
        arcoOrigem[this.vertices] = indexArcoDestino;

    }

    public ListaDeAdjacencia(GrafoInfo info, Aresta[] arestas) {
        this.init(info, arestas);
    }

    public int[] getAdjacentes(int vertice){
        vertice -= 1;
        if (vertice>=this.vertices)
            throw new Error("Vertice nao existe no grafo");
        int inicio = this.arcoOrigem[vertice];
        int fim = this.arcoOrigem[vertice+1];

        int size = fim - inicio;
        int[] adjacentes;

        if (size > 0){
            adjacentes = new int[size];

            for (int arco=inicio, index=0; arco<fim ; arco++, index++)
                adjacentes[index] = this.arcoDestino[arco];
        }
        else 
            adjacentes = null;

        return adjacentes;
        
    }

    public void encontrarCicloPermutado(){
        ArrayList<Integer> caminhos = new ArrayList<Integer>();

        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();
        leitura.abrirArquivo("ciclosPermutados.out");
        String linha = "";
        while (linha != null){
            linha = leitura.ler();
            if(linha!=null)
                caminhos.add(Integer.parseInt(linha));
        }
        leitura.fecharArquivo();

        
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append("\nLista de adjacência\n");
        for ( int i=0; i<arcoOrigem.length-1;i++ ){
            builder.append(i+1 + " |");
            for (int j=arcoOrigem[i]; j<arcoOrigem[i+1]; j++){
                if(this.direcionado)
                    builder.append(" -> " + (arcoDestino[j]));
                else
                    builder.append(" - " + (arcoDestino[j]));
                if (this.ponderado)
                    builder.append(" (Peso: " + arcoPeso[j] + ")");
                
            }
            builder.append("\n");
        }
        
        return builder.toString();
    }

    public static String arrayToString(int[] array) {
        StringBuilder builder = new StringBuilder();
        for(int i : array) {
            builder.append(i);
        }
        return builder.toString();
    }
}
