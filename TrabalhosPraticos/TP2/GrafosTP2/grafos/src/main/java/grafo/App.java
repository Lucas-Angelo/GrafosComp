package grafo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.lucasangelo.arquivo.*;
import com.lucasangelo.grafos.componentes.*;
import com.lucasangelo.grafos.estruturas.*;

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
        
        Grafo graph = new Grafo();
        System.out.println(graph.toString());
        graph.ciclosEmProfundidade();

    }
}
