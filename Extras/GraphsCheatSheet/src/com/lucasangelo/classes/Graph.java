package com.lucasangelo.classes;

public class Graph {

    protected int vertices;
    protected int edges;

    protected boolean complete;
    protected boolean directed;
    protected boolean bipartite;

    public Graph(int vertices, boolean complete) {
        this.vertices = vertices;
        this.complete = complete;
    }

    public Graph(int vertices, boolean complete, boolean directed) {
        this.vertices = vertices;
        this.complete = complete;
        this.directed = directed;
    }

    public Graph(boolean complete) {
        this.complete = complete;
        this.bipartite = bipartite;
    }

    public int getMaxEdgesQuantityCalculator() throws Exception {
        int quantity;
        if(this.complete)
            quantity = ( ((this.vertices-1) * this.vertices)/2 );
        else
            throw new Exception("The graph needs to be complete to calculate the maximum edges!");
        return quantity;
    }

}
