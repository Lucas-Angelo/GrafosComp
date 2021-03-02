package com.lucasangelo.grafos.componentes;

public class Aresta {
    private int origem;
    private int destino;
    private String nomeArco;

    public Aresta (int origem, int destino) {
        this.origem = origem;
        this.destino = destino;
        this.nomeArco = String.format("a%d-%d", origem, destino);
    }

    public int getOrigem() {
        return origem;
    }

    public void setOrigem(int origem) {
        this.origem = origem;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    public String getNomeArco() { return this.nomeArco; }

    public void setNomeArco(String nomeArco) {
        this.nomeArco = nomeArco;
    }

}
