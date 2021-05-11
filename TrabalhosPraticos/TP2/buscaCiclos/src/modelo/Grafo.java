package modelo;

import controlador.BuscaEmProfundidade;
import controlador.BuscaPorPermutacao;
import controlador.DadosDeArquivos;
import controlador.ListaDeAdjacencia;

public class Grafo {

    private Aresta[] arestas;
    private GrafoInfo info;
    
    private ListaDeAdjacencia estrutura;

    private void init(String arquivoStr) throws Exception{
        DadosDeArquivos dados = new DadosDeArquivos(arquivoStr);

        this.arestas = dados.getArestas();
        this.info = dados.getGrafoInfo();

        this.estrutura = new ListaDeAdjacencia(info, arestas); // Gerar a lista de adjacencia para esse grafo, pra poder imprimir
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

    public void buscarEmProfundidade() {
        BuscaEmProfundidade buscaEmProfundidade = new BuscaEmProfundidade(arestas);
        System.out.println(buscaEmProfundidade.toString());
    }

    public void buscarPorPermutacao() {
        int qtdVertices = info.getQtdVertices();
        int vertices[] = new int[qtdVertices];

        for(int i=0; i<qtdVertices; i++)
            vertices[i] = i+1; // Poderia passar um new int[]{1, 2, 3, 4, 5, 6} no contrutor também

        BuscaPorPermutacao buscaPorPermutacao = new BuscaPorPermutacao(vertices);
        buscaPorPermutacao.permutar();
        
        estrutura.encontrarCicloPermutado();
    }

    public void encontrarCicloPermutado() {
        estrutura.encontrarCicloPermutado();
    }

}
