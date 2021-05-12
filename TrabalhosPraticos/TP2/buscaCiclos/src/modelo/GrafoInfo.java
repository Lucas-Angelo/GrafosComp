package modelo;

public class GrafoInfo {

    private int qtdVertices;

    private void init(int qtdVertices) throws Exception {
        this.qtdVertices = qtdVertices;

        if(qtdVertices<=0)
            throw new Exception("Não é possível gerar grafos sem vértices");
        
        System.out.print("\nGrafo simples e não direcionado.");
        System.out.println("Quantidade de vértices: " + qtdVertices + ".");
    }

    public GrafoInfo(int qtdVertices) throws Exception {
        init(qtdVertices);
    }

    public int getQtdVertices() {
        return qtdVertices;
    }

    public void setQtdVertices(int qtdVertices) {
        this.qtdVertices = qtdVertices;
    }
}
