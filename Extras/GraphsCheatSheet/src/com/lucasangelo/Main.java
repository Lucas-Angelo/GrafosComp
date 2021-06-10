package com.lucasangelo;

import com.lucasangelo.classes.BipartiteGraph;
import com.lucasangelo.classes.Graph;
import com.lucasangelo.classes.ShortestPathFloydWarshall;
import com.lucasangelo.classes.SubGraph;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int option;
        System.out.println("Options menu:");
        System.out.println("1 - To calculate the number of subgraphs of an undirected and complete graph");
        System.out.println("2 - To calculate the number of edges of a complete graph");
        System.out.println("3 - To calculate the number of edges in a complete and bipartite graph");
        System.out.println("4 - To find a shortest path with floyd warshall"); // classes/ShortestPathFloydWarshall.java

        System.out.print("Enter your option: ");
        option = Integer.parseInt(in.readLine());

        Graph graph;
        SubGraph subGraph;
        BipartiteGraph bipartiteGraph;
        int quantityVertices;
        System.out.println();
        switch (option) {
            case 1:
                System.out.println("Calculating the number of subgraphs in a complete and undirected graph.");
                System.out.print("Enter the number of vertices: ");
                quantityVertices = Integer.parseInt(in.readLine());
                subGraph = new SubGraph(quantityVertices, true, false);
                System.out.println("Subgraphs quantity: " + subGraph.getSubGraphsQuantity());
                break;
            case 2:
                System.out.println("Calculating the number of edges of a complete graph.");
                System.out.print("Enter the number of vertices: ");
                quantityVertices = Integer.parseInt(in.readLine());
                graph = new Graph(quantityVertices, true);
                System.out.println("Edges quantity: " + graph.getMaxEdgesQuantityCalculator());
                break;
            case 3:
                System.out.println("Calculate the number of edges in a complete and bipartite graph.");
                System.out.print("Enter the number of vertices on set one: ");
                int quantityVerticesSetOne =  Integer.parseInt(in.readLine());
                System.out.print("Enter the number of vertices on set two: ");
                int quantityVerticesSetTwo =  Integer.parseInt(in.readLine());
                bipartiteGraph = new BipartiteGraph(quantityVerticesSetOne, quantityVerticesSetTwo, true, true);
                System.out.println("Edges quantity: " + bipartiteGraph.getMaxEdgesQuantityCalculator());
                break;
            case 4:
                ShortestPathFloydWarshall a = new ShortestPathFloydWarshall();
                a.floydWarshall();
                break;
            default:
                System.out.println("Finish.");
        }

    }
}
