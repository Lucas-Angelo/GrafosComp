package com.lucasangelo.classes;

public class BipartiteGraph extends Graph{

    private int quantityVerticesSetOne;
    private int quantityVerticesSetTwo;


    public BipartiteGraph(int quantityVerticesSetOne, int quantityVerticesSetTwo, boolean complete, boolean bipartite) {
        super(complete);
        this.quantityVerticesSetOne = quantityVerticesSetOne;
        this.quantityVerticesSetTwo = quantityVerticesSetTwo;
        this.bipartite=bipartite;
    }

    public int getMaxEdgesQuantityCalculator() throws Exception {
        if(this.complete && this.bipartite)
            return this.quantityVerticesSetOne * this.quantityVerticesSetTwo;
        else
            throw new Exception("The graph needs to be complete and bipartite for this!");
    }
}
