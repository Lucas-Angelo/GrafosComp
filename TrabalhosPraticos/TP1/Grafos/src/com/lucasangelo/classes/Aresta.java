package com.lucasangelo.classes;

public class Aresta {
    int origem;
    int destino;

    public Aresta (int origem, int destino) {
        this.origem = origem;
        this.destino = destino;
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

}
