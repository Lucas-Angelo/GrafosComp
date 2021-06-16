import controlador.BuscaDisjuntos;
import modelo.Grafo;

public class App { 

    public static void main(String[] args) throws Exception {
        /*
            Arquivos. Exemplo: grafo.in

            Primeira linha: quantidade de vértices

            Demais linhas são os arcos
            Quantidade de linhas é a quantida de arestas
            Exemplo da linha de arco entre os vértices 1 e 2: 1,2

        */
        
        Grafo graph = new Grafo("grafo.in");
        BuscaDisjuntos bd = new BuscaDisjuntos(graph.getMatrizDeAdjacencia(), graph.getInfo());
        bd.buscarMaximoCaminhosDisjuntos(0, 7);
    }
}
