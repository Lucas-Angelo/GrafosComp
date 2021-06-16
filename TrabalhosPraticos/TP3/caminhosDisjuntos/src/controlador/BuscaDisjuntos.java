package controlador;

import java.util.*;

import modelo.GrafoInfo;

public class BuscaDisjuntos {

    private int vertices; // Number of vertices in graph
    private int[][] matrizDeAdjacencia;
    private List<int[]> caminhosDisjuntos;

    // Driver program to test above functions
    public BuscaDisjuntos(MatrizDeAdjacencia matriz, GrafoInfo info) {
        // Let us create a graph shown in the above example
        this.matrizDeAdjacencia = matriz.getMatriz();
        this.vertices = info.getQtdVertices();
        this.caminhosDisjuntos = new LinkedList<int[]>();
    }

    public void getMaximoCaminhosDisjuntos(int vOrigem, int vDestino) {
        System.out.println("A quantidade máxima de caminhos disjuntos são " + fordFulkerson(this.matrizDeAdjacencia, vOrigem, vDestino));
    }
 
    /* Returns true if there is a path from source 's' to
      sink 't' in residual graph. Also fills parent[] to
      store the path */
    boolean bfs(int rGraph[][], int s, int t, int parent[])
    {
        // Create a visited array and mark all vertices as
        // not visited
        boolean visited[] = new boolean[this.vertices];
        for (int i = 0; i < this.vertices; ++i)
            visited[i] = false;
 
        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue
            = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;
 
        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < this.vertices; v++) {
                if (visited[v] == false
                    && rGraph[u][v] > 0) {
                    // If we find a connection to the sink
                    // node, then there is no point in BFS
                    // anymore We just have to set its parent
                    // and can return true
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
 
        // We didn't reach sink in BFS starting from source,
        // so return false
        return false;
    }
 
    // Retorna o fluxo máximo de s para t no dado
     // gráfico
    int fordFulkerson(int graph[][], int s, int t)
    {
        int u, v;
 
        // Cria um gráfico residual e preenche o resíduo
         // gráfico com as capacidades fornecidas no gráfico original
         // como capacidades residuais no gráfico residual
 
         // Gráfico residual onde rGraph [i] [j] indica
         // capacidade residual da aresta de i a j (se houver
         // é uma vantagem. Se rGraph [i] [j] é 0, então há
         // não) 
        int rGraph[][] = new int[this.vertices][this.vertices];
 
        for (u = 0; u < this.vertices; u++)
            for (v = 0; v < this.vertices; v++)
                rGraph[u][v] = graph[u][v];
 
        // Este array é preenchido por BFS e para armazenar o caminho
        int parent[] = new int[this.vertices];
 
        int max_flow = 0; // Não há fluxo inicialmente
 
        // Aumente o fluxo enquanto houver o caminho da fonte
         // afundar
        while (bfs(rGraph, s, t, parent)) {
            // Encontre a capacidade residual mínima das arestas
             // ao longo do caminho preenchido pelo BFS. Ou podemos dizer
             // encontre o fluxo máximo pelo caminho encontrado.
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }
 
            List<Integer> caminhoDisjunto = new LinkedList<Integer>();
            // atualiza as capacidades residuais das bordas e
             // reversa as bordas ao longo do caminho
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;

                caminhoDisjunto.add(v);
                // System.out.print(v + " ");
                if(parent[v]==s) {
                    caminhoDisjunto.add(parent[v]);
                    // System.out.print(parent[v]);
                    int[] caminhoDisjunto2D = new int[caminhoDisjunto.size()];
                    for(int o=caminhoDisjunto2D.length-1; o>=0; o--)
                        caminhoDisjunto2D[(caminhoDisjunto2D.length-1)-o] = caminhoDisjunto.get(o);
                    
                    this.caminhosDisjuntos.add(caminhoDisjunto2D);
                }
                
            }
            // System.out.println();
            
 
            // Adicionar fluxo de caminho ao fluxo geral
            max_flow += path_flow;
        }

        for (int y = 0; y < this.vertices; y++) {
            for (int z = 0; z < this.vertices; z++) {
                System.out.print(rGraph[y][z] + "\t");
            }
            System.out.println();
        }

        for (int[] caminhoD : this.caminhosDisjuntos) {
            for(int y=0; y<caminhoD.length; y++)
                System.out.print(caminhoD[y] + " ");
            System.out.println();
        }
 
        // Return the overall flow
        return max_flow;
    }

}
