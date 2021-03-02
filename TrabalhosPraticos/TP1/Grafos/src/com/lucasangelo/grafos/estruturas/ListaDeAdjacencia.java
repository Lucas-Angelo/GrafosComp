package com.lucasangelo.grafos.estruturas;

import java.util.ArrayList;

class GraphRep {
    //function to add the edge in Adj list
    static void addEdge(ArrayList<ArrayList<Integer> > adj,
                        int u, int v)
    {
        adj.get(u).add(v);
        adj.get(v).add(u);
        // if the graph is directed then only one statement is needed here
    }

    static void printGraph(ArrayList<ArrayList<Integer>> adj)
    {
        for (int i = 0; i < adj.size(); i++) {
            System.out.println("Adjacency list for vertex " + i);
            for (int j = 0; j < adj.get(i).size(); j++) {
                if (j!=0)
                    System.out.print(" -> "+adj.get(i).get(j));
                else
                    System.out.print(adj.get(i).get(j));
            }
            System.out.println();
        }
    }
    public static void main(String[] args)
    {
        int V = 5;
        ArrayList<ArrayList<Integer> > graph
                = new ArrayList<>(V);

        //creating array lists for storing lists
        for (int i = 0; i < V; i++)
            graph.add(new ArrayList<>());

        addEdge(graph, 0, 1);
        addEdge(graph, 0, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 1, 2);
        addEdge(graph, 3, 4);


        printGraph(graph);
    }
}
