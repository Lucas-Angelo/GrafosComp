// package controlador;

// import modelo.Grafo;

// import java.util.Optional;

// import modelo.Aresta;
// import modelo.Caminho;

// public class CaminhosDisjutos {
    
//     static public int[] getCaminhosDisjuntos( Grafo graph, int source, int sink ){
//         //Grafo maxFlow = fordFulkersonAlg( graph, source, sink );
        
//         return new int[5];
//     }

//     static private Grafo fordFulkersonAlg(  Grafo graph, int source, int sink  ){
//         // inicializa fluxo com grafo
//         Grafo flow = new Grafo( graph.getArestas(), graph.getInfo() );

//         //executa enquanto tiver caminho no grafo residual
//         Optional<Caminho> caminhoResidual = Optional.empty();
//         do {
//             caminhoResidual = getCaminho(flow, source, sink);
//             caminhoResidual.ifPresent(
//                 caminho -> AtualizarResidual(flow, caminho)
//             );
//         } while (caminhoResidual.isPresent());
//         return flow;
//     }

//     static private Optional<Caminho> getCaminho( Grafo residual, int source, int sink ){
//         Caminho caminho = new Caminho();

//         // inicializa vetor de visitados
//         boolean[] visitados = new boolean[residual.getInfo().getQtdVertices()];
//         for (int i=0; i<visitados.length; i++) 
//             visitados[i] = false;   

//         int origem = source;
//         boolean finished = false;
//         boolean uncompleted = false;
//         do {
//             int[] adjacentes = residual.getEstrutura().getAdjacentes(origem);
//             uncompleted = true;
//             for (int destino : adjacentes) {
//                 if ( visitados [ destino ] ){
//                     uncompleted = false;
//                     if (destino == sink) {
//                         Aresta arco = new Aresta( origem, destino );
//                         caminho.add(arco);
//                         finished = true;
//                     }
//                     else{
//                         Aresta arco = new Aresta( origem, destino );
//                         caminho.add(arco);
//                         visitados[destino] = true;
//                     }
                    
//                 }
//             }
            
//         } while (finished || uncompleted);

//         return uncompleted ? Optional.empty() : Optional.of(caminho) ;
//     }

//     static private void AtualizarResidual(Grafo graph, Caminho path){
        
//     }

// }

