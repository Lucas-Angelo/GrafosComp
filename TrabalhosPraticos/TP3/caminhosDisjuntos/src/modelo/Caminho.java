package modelo;

import java.util.ArrayList;

public class Caminho {
    private ArrayList<Aresta> arco;

    public Caminho() {
        this.arco = new ArrayList<Aresta>();
    }
    
    public ArrayList<Aresta> getArco() {
        return arco;
    }
    public void add(Aresta arco) {
        this.arco.add(arco);
    }
    
}
