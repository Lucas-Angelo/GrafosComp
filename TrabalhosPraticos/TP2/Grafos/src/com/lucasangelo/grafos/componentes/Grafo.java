package com.lucasangelo.grafos.componentes;

import com.lucasangelo.arquivo.DadosDeArquivos;
import com.lucasangelo.grafos.estruturas.ListaDeAdjacencia;
import com.lucasangelo.grafos.estruturas.MatrizDeAdjacencia;
import com.lucasangelo.grafos.estruturas.MatrizDeIncidencia;

public class Grafo {

    private Aresta[] arestas;
    private GrafoInfo info;

    public Grafo() throws Exception{
        init("grafo.in");
    }
    public Grafo(String arquivoStr) throws Exception{
        init(arquivoStr);
    }

    private void init(String arquivoStr) throws Exception{
        DadosDeArquivos dados = new DadosDeArquivos(arquivoStr);
        info = dados.getGrafoInfo();
        arestas = dados.getArestas();
    }

    public void exibir(){
        // a lista de adjacência (2) é o método que vimos como mais recomendados para todos os 4 tipos de grafos
        exibir(2);
    }
    public void exibir(int modo){
        //essa função existe para que seja possível visualizar o grafo em qualquer estrutura, mesmo que não seja a que consideramos mais viável
        switch (modo){
            case 0:
                new MatrizDeAdjacencia(this).imprimir();
                break;
            case 1:
                new MatrizDeIncidencia(this).imprimir();
                break;
            case 2:
                new ListaDeAdjacencia(this).imprimir();
            
        }
    }

    public Aresta[] getArestas() {
        return arestas;
    }
    public GrafoInfo getInfo() {
        return info;
    }

}
