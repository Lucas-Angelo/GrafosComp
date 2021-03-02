package com.lucasangelo.grafos.componentes;

import com.lucasangelo.arquivo.DadosDeArquivos;

public class GrafoInfo {

    private boolean direcionado;
    private boolean ponderado;
    private int qtdVertices;

    private void init() throws Exception {
        int dadosGrafo[] = DadosDeArquivos.capturarDadosGrafo();
        this.direcionado = (dadosGrafo[0]==1) ? true : false;
        this.ponderado = (dadosGrafo[1]==1) ? true : false;
        this.qtdVertices = dadosGrafo[2];

        if(qtdVertices<=0)
            throw new Exception("Não é possível gerar grafos sem vértices");

        if (direcionado) System.out.println("\nGrafo direcionado.");
        else System.out.println("\nGrafo não direcionado.");
        if (ponderado) System.out.println("Grafo ponderado.");
        else System.out.println("Grafo não ponderado.");
        System.out.println("Quantidade de vértices: " + qtdVertices + ".");

    }

    public GrafoInfo() throws Exception {
        init();
    }

    public boolean isDirecionado() {
        return direcionado;
    }

    public void setDirecionado(boolean direcionado) {
        this.direcionado = direcionado;
    }

    public boolean isPonderado() {
        return ponderado;
    }

    public void setPonderado(boolean ponderado) {
        this.ponderado = ponderado;
    }

    public int getQtdVertices() {
        return qtdVertices;
    }

    public void setQtdVertices(int qtdVertices) {
        this.qtdVertices = qtdVertices;
    }
}
