import modelo.Grafo;

public class App {

    public static void main(String[] args) throws Exception {
        /*
            Arquivos. Exemplo: grafo.in

            Primeira linha - Metadados do grafo, sendo 3 divididos por espaços
            Primeiro dado: 0 para não direcionado, 1 para direcionado
            Segundo dado: 0 para não ponderado, 1 para ponderado
            Terceiro dado: quantidade de vértices

            Demais linhas - Vertices
            Quantidade de linhas é a quantida de arestas
            Exemplo da linha de arco entre os vértices 1 e 2: 1,2
            Exemplo da linha de arco entre os vértices ponderados 1 e 2 com peso 5: 1,2,5

        */
        
        Grafo graph = new Grafo("grafo.in");
        System.out.println(graph);
        //graph.ciclosEmProfundidade();
        graph.buscarEmProfundidade();

    }
}
