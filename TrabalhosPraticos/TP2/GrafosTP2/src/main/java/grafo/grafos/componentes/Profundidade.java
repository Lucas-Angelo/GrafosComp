// package main.java.grafo.grafos.componentes;

// import com.lucasangelo.grafos.componentes.*;

// public class Profundidade {
//     public final int N = 100000;
 
//     // variables to be used
//     // in both functions
//     @SuppressWarnings("unchecked")
//     public Vector<Integer>[] graph = new Vector[N];
//     @SuppressWarnings("unchecked")
//     public Vector<Integer>[] cycles = new Vector[N];
//     public int cyclenumber;
 
//     // Function to mark the vertex with
//     // different colors for different cycles
//     public void dfs_cycle(int u, int p, int[] color,
//                        int[] mark, int[] par)
//     {
 
//         // already (completely) visited vertex.
//         if (color[u] == 2)
//         {
//             return;
//         }
 
//         // seen vertex, but was not completely visited -> cycle detected.
//         // backtrack based on parents to find the complete cycle.
//         if (color[u] == 1)
//         {
 
//             cyclenumber++;
//             int cur = p;
//             mark[cur] = cyclenumber;
 
//             // backtrack the vertex which are
//             // in the current cycle thats found
//             while (cur != u)
//             {
//                 cur = par[cur];
//                 mark[cur] = cyclenumber;
//             }
//             return;
//         }
//         par[u] = p;
 
//         // partially visited.
//         color[u] = 1;
 
//         // simple dfs on graph
//         for (int v : graph[u])
//         {
 
//             // if it has not been visited previously
//             if (v == par[u])
//             {
//                 continue;
//             }
//             dfs_cycle(v, u, color, mark, par);
//         }
 
//         // completely visited.
//         color[u] = 2;
//     }
 
//     // add the edges to the graph
//     public void addEdge(int u, int v)
//     {
//         graph[u].add(v);
//         graph[v].add(u);
//     }
 
//     // Function to print the cycles
//     public void printCycles(int edges, int mark[])
//     {
 
//         // push the edges that into the
//         // cycle adjacency list
//         for (int i = 1; i <= edges; i++)
//         {
//             if (mark[i] != 0)
//                 cycles[mark[i]].add(i);
//         }
 
//         // print all the vertex with same cycle
//         for (int i = 1; i <= cyclenumber; i++)
//         {
//             // Print the i-th cycle
//             System.out.printf("Cycle Number %d: ", i);
//             for (int x : cycles[i])
//                 System.out.printf("%d ", x);
//             System.out.println();
//         }
//     }
 
//     // Driver Code
//     public Profundidade(Aresta[] arestas)
//     {
 
//         for (int i = 0; i < N; i++)
//         {
//             graph[i] = new Vector<>();
//             cycles[i] = new Vector<>();
//         }
 
//         for(int i=0; i<arestas.length; i++) {
//             addEdge(arestas[i].getOrigem(), arestas[i].getDestino());
//         }
 
//         // arrays required to color the
//         // graph, store the parent of node
//         int[] color = new int[N];
//         int[] par = new int[N];
 
//         // mark with unique numbers
//         int[] mark = new int[N];
 
//         // store the numbers of cycle
//         cyclenumber = 0;
//         int edges = 13;
 
//         // call DFS to mark the cycles
//         dfs_cycle(1, 0, color, mark, par);
 
//         // function to print the cycles
//         printCycles(edges, mark);
//     }
// }
