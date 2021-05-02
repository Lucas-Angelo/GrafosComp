public class Aresta {
    private int origem;
    private int destino;
    private int peso;
    
    private void init(int origem, int destino, int peso){
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public Aresta (int origem, int destino) {
        init(origem, destino, 1);
    }
    public Aresta (int origem, int destino, int peso) {
        init(origem, destino, peso);
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

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    // @Override
    // public int compareTo(Aresta aresta){
    //     if (this.equals(aresta))
    //         return 0; 
    //     if (this.origem > aresta.origem)
    //         return 1;
    //     else if (this.origem < aresta.origem)
    //         return -1;
    //     else{
    //         if (this.destino > aresta.destino)
    //             return 1;
    //         else if (this.destino < aresta.destino)
    //             return -1;
    //         else
    //             return 0;
    //     }
    // }

}
