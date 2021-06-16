package modelo;

import controlador.DadosDeArquivos;
import controlador.ListaDeAdjacencia;
import controlador.MatrizDeAdjacencia;

public class Grafo {

    private Aresta[] arestas;
    private GrafoInfo info;
    
    private ListaDeAdjacencia estrutura;
    private MatrizDeAdjacencia matrizDeAdjacencia;

    private void init( Aresta[] a, GrafoInfo gf){
        this.arestas = a;
        this.info = gf;

        this.estrutura = new ListaDeAdjacencia(this.info, this.arestas);
        this.matrizDeAdjacencia = new MatrizDeAdjacencia(this.info, arestas);
    }
    private void initArquivo(String arquivoStr) throws Exception{
        DadosDeArquivos dados = new DadosDeArquivos(arquivoStr);
        init(dados.getArestas(), dados.getGrafoInfo());
    }

    public Grafo() throws Exception{
        initArquivo("grafo.in");
    }
    public Grafo(String arquivoStr) throws Exception{
        initArquivo(arquivoStr);
    }
    public Grafo(Aresta[] a, GrafoInfo gf){
        init(a.clone(), gf);
    }

    public Aresta[] getArestas() {
        return this.arestas;
    }
    public GrafoInfo getInfo() {
        return this.info;
    }
    public ListaDeAdjacencia getEstrutura() {
        return estrutura;
    }

    public MatrizDeAdjacencia getMatrizDeAdjacencia() {
        return this.matrizDeAdjacencia;
    }

    public String toString(){
        return this.estrutura.toString();
    }

    
    
}
