package modelo;

public class Aresta {
    private int origem;
    private int destino;
    
    private void init(int origem, int destino){
        this.origem = origem;
        this.destino = destino;
    }

    public Aresta (int origem, int destino) {
        init(origem, destino);
    }
    public Aresta (int origem, int destino, int peso) {
        init(origem, destino);
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
