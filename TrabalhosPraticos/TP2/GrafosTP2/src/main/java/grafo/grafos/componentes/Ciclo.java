import java.util.ArrayList;

public class Ciclo {
    private Aresta[] arestas;

    public Ciclo() {
    }

    public Ciclo(Aresta[] arestas) {
        this.arestas = arestas;
    }

    public Aresta[] getArestas() {
        return this.arestas;
    }

    public void setArestas(Aresta[] arestas) {
        this.arestas = arestas;
    }

    @Override
    public boolean equals(Object o) {
        Aresta arestasOrdenadas = new Aresta();
        arestasOrdenadas.setArestas(Arrays.sort(this.arestas));
        if (o == this)
            return true;
        if (!(o instanceof Ciclo)) {
            return false;
        }
        Ciclo ciclo = (Ciclo) o;
        return Objects.equals(this.arestas, ciclo.arestas);
        
    }

    @Override
    public String toString() {
        return "{" +
            " arestas='" + getArestas() + "'" +
            "}";
    }
    
    
    
}
