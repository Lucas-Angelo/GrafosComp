package com.lucasangelo.classes;

public class SubGraph extends Graph {

    public SubGraph(int vertices, boolean complete, boolean directed) {
        super(vertices, complete, directed);
    }

    public int getSubGraphsQuantity() throws Exception {
        return subGraphsCalculator();
    }

    private int subGraphsCalculator() throws Exception {
        if(this.complete && !this.directed) {
            double quantity = 0;
            int n = this.vertices;

            for (int k = 2; k <= n; k++) {
                double cal = expression(n, k);
                quantity += cal * Math.pow(2, expression(k, 2));
            }
            quantity += n / 1;
            return ( (int) quantity);
        } else {
            throw new Exception("The graph needs to be complete and undirected to calculate the number of subgraphs!");
        }
    }

    // Expression: n! / k!(n-k)!
    // This expression work for only undirected and complete graphs
    private double expression(int n, int k) {
        return (factorial (n)/(factorial (k)*factorial (n-k)));
    }

    private double factorial (double num) {
        if (num >= 1.0)
            return num * factorial (num - 1.0);
        else
            return 1.0;
    }

}
