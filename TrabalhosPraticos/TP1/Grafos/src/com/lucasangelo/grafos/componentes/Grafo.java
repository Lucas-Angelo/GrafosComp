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
        int modo = 0;
        if(this.info.isPonderado()){ // Lista adjacente para poder ponderar
            modo=2;
        } else {
            if(this.info.isDirecionado()) { // Matriz de indicência consegue mostrar direção
                modo=1;
            } else { // Matriz de adjacência não consegue direcionar pois espelha
                modo=0;
            }
        }
        exibir(modo);
    }
    public void exibir(int modo){
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
