package modelo;

import controlador.DadosDeArquivos;
import controlador.MatrizDeAdjacencia;

public class Grafo {

    private Aresta[] arestas;
    private GrafoInfo info;
    
    private MatrizDeAdjacencia estrutura;

    private void init(String arquivoStr) throws Exception{
        DadosDeArquivos dados = new DadosDeArquivos(arquivoStr);

        this.arestas = dados.getArestas();
        this.info = dados.getGrafoInfo();

        this.estrutura = new MatrizDeAdjacencia(info, arestas); // Gerar a lista de adjacencia para esse grafo, pra poder imprimir
    }

    public Grafo() throws Exception{
        init("grafo.in");
    }
    public Grafo(String arquivoStr) throws Exception{
        init(arquivoStr);
    }

    public Aresta[] getArestas() {
        return this.arestas;
    }
    public GrafoInfo getInfo() {
        return this.info;
    }

    public String toString(){
        return this.estrutura.toString();
    }
    
}
