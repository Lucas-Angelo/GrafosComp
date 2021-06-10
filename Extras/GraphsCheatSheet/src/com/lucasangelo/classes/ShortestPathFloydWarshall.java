package com.lucasangelo.classes;

public class ShortestPathFloydWarshall {
    // A Java program for Floyd Warshall All Pairs Shortest
    // Path algorithm.

	final static int INF = 999999999, V = 6;

	public void floydWarshall()
	{
		int graph[][] = 
        { 
          {0, 1, INF, INF, INF, INF},
          {INF, 0, 1, 3, 2, INF},
          {3, INF, 0, 2, INF, INF},
          {INF, INF, INF, 0, INF, 2},
          {INF, INF, INF, -3, 0, INF},
          {INF, INF, INF, INF, 3, 0}
        };

		int dist[][] = new int[V][V];
		int i, j, k;

		/* Initialize the solution matrix
		same as input graph matrix.
		Or we can say the initial values
		of shortest distances
		are based on shortest paths
		considering no intermediate
		vertex. */
		for (i = 0; i < V; i++)
			for (j = 0; j < V; j++)
				dist[i][j] = graph[i][j];


    System.out.println("Original: ");
    printSolution(dist);
    System.out.println();
    
		/* Add all vertices one by one
		to the set of intermediate
		vertices.
		---> Before start of an iteration,
			we have shortest
			distances between all pairs
			of vertices such that
			the shortest distances consider
			only the vertices in
			set {0, 1, 2, .. k-1} as
			intermediate vertices.
		----> After the end of an iteration,
				vertex no. k is added
				to the set of intermediate
				vertices and the set
				becomes {0, 1, 2, .. k} */
		for (k = 0; k < V; k++)
		{
      System.out.println("Versão: " + (k+1));
      printSolution(dist);
      System.out.println();
			// Pick all vertices as source one by one
			for (i = 0; i < V; i++)
			{
				// Pick all vertices as destination for the
				// above picked source
				for (j = 0; j < V; j++)
				{
					// If vertex k is on the shortest path from
					// i to j, then update the value of dist[i][j]
					if (dist[i][k] + dist[k][j] < dist[i][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}

    System.out.println("Final: ");
    printSolution(dist);
    System.out.println();
	}

	void printSolution(int dist[][])
	{
		//System.out.println("The following matrix shows the shortest "+
		//				"distances between every pair of vertices");
		for (int i=0; i<V; ++i)
		{
			for (int j=0; j<V; ++j)
			{
				if (dist[i][j]==INF || dist[i][j]>INF-100)
					System.out.print("INF\t\t");
				else
					System.out.print(dist[i][j]+"\t\t");
			}
			System.out.println();
		}
	}

}
