package modelo;

import java.util.ArrayList;

public class Ciclo {
    private ArrayList<int[]> arco;

    public Ciclo() {
        this.arco = new ArrayList<int[]>();
    }
    
    public ArrayList<int[]> getArco() {
        return arco;
    }
    public void add(int[] arco) {
        this.arco.add(arco);
    }
    
}
