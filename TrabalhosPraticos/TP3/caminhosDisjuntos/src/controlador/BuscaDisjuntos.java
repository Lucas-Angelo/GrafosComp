package controlador;

import java.util.*;

import modelo.GrafoInfo;

public class BuscaDisjuntos {

    private int vertices;
    private int[][] matrizDeAdjacencia; // É o grafo original em forma de matriz de adjacencia
    private int qtdCaminhosDisjuntos;
    private List<int[]> caminhosDisjuntos; // Onde vai salvar os caminhos disjuntos

    // Construtor que busca a matriz de adjacencia que fizemos no trabalho 1 
    // Também captura quantidade de vertices e inicia o vetor pra salvar os caminhos disjuntos
    public BuscaDisjuntos(MatrizDeAdjacencia matriz, GrafoInfo info) {
        // Let us create a graph shown in the above example
        this.matrizDeAdjacencia = matriz.getMatriz();
        this.vertices = info.getQtdVertices();
        this.qtdCaminhosDisjuntos = 0;
        this.caminhosDisjuntos = new LinkedList<int[]>();
    }

    public void imprimirCaminhosDisjuntos() {
        
    }

    // Depois do construtor pode chamar a busca por caminhos disjuntos
    public void buscarMaximoCaminhosDisjuntos(int vOrigem, int vDestino) {
        fordEfulkerson(vOrigem, vDestino);
    }

    // Fork e Fulkerson adaptado para encontrar os caminhos disjuntos no grafo entre um vértice origem e destino
    // Utiliza a busca em lagura para encontrar os caminhos se existirem e contar
    private void fordEfulkerson(int origem, int destino) {
        int verticeOrigemResidual, verticeDestinoResidual;

        // Cria um gráfico residual com o mesmo tamanho que o original
        int grafoResidual[][] = new int[this.vertices][this.vertices];

        // Preenche o grafo residual inicialmente com as arestas direcionado do original
            // (pode-se dizer que o fluxo de cada aresta aqui é 1)
        // gráfico com as capacidades fornecidas no gráfico original
        // como capacidades residuais no gráfico residual
        for (verticeOrigemResidual = 0; verticeOrigemResidual < this.vertices; verticeOrigemResidual++)
            for (verticeDestinoResidual = 0; verticeDestinoResidual < this.vertices; verticeDestinoResidual++)
                grafoResidual[verticeOrigemResidual][verticeDestinoResidual] = this.matrizDeAdjacencia[verticeOrigemResidual][verticeDestinoResidual];

        // Este vetor é preenchido pela busca em lagura para armazenar o caminho se existir
        int caminhoNaMatrizEncontrado[] = new int[this.vertices];

        // Enquanto existir o caminhos vai incrementando a quantidade
        int qtdCaminhos = 0;
        int caminho = 0;
        // Faz a primeira busca em largura pra verificar se existe um caminho entre esses dois
        boolean existeCaminho = buscaEmLargura(grafoResidual, origem, destino, caminhoNaMatrizEncontrado);
        for (qtdCaminhos = 0; existeCaminho; qtdCaminhos += caminho) {
            // Encontre a capacidade residual mínima das arestas
            // ao longo do caminho preenchido pelo BFS. Ou podemos dizer
            // encontre o fluxo máximo pelo caminho encontrado.
            caminho = 1; // Considerando se existe o caminho
            for (verticeDestinoResidual = destino; verticeDestinoResidual != origem; verticeDestinoResidual = caminhoNaMatrizEncontrado[verticeDestinoResidual]) {
                verticeOrigemResidual = caminhoNaMatrizEncontrado[verticeDestinoResidual];
                // Dai verifico se continuo considerando que existe o caminho ou capturo 0 do grafoResidual já que não existe o caminho
                    // é o mínimo entre o 1 e possível 0
                caminho = Math.min(caminho, grafoResidual[verticeOrigemResidual][verticeDestinoResidual]);
            }

            // Lista para salvar o identificador dos vértices do caminho disjunto atual possivelmente encontrado
            List<Integer> caminhoDisjunto = new LinkedList<Integer>();
            // Atualiza a existencia das arestas no grafo residual direcionando ida e vinda
            for (verticeDestinoResidual = destino; verticeDestinoResidual != origem; verticeDestinoResidual = caminhoNaMatrizEncontrado[verticeDestinoResidual]) {
                verticeOrigemResidual = caminhoNaMatrizEncontrado[verticeDestinoResidual];
                // Aqui atualiza a existencia da aresta
                grafoResidual[verticeOrigemResidual][verticeDestinoResidual] -= caminho;
                grafoResidual[verticeDestinoResidual][verticeOrigemResidual] += caminho;

                // Salva o identificador do vértice no caminhoDisjunto
                caminhoDisjunto.add(verticeDestinoResidual);
                // Se o caminho encontrado na busca em largura no grafo residual (vem invertido)
                // for igual a origem, ou seja, caminho entre sair do destino e ir para origem existe
                // invertido pois é o residual
                if (caminhoNaMatrizEncontrado[verticeDestinoResidual] == origem) {
                    // Com isso, adiciona o destino
                    caminhoDisjunto.add(caminhoNaMatrizEncontrado[verticeDestinoResidual]);

                    int[] caminhoDisjunto2D = new int[caminhoDisjunto.size()];
                    for (int o = caminhoDisjunto2D.length - 1; o >= 0; o--)
                        caminhoDisjunto2D[(caminhoDisjunto2D.length - 1) - o] = caminhoDisjunto.get(o);

                    // Salva o caminhoDisjunto encontrado e agora não mais invertido nos caminhosDisjuntos
                    this.caminhosDisjuntos.add(caminhoDisjunto2D);
                }

            }

            // Verifica se existe caminho ainda passando o grafoResidual atualizado
            // Se existir loop continua verificando tudo novamente
            existeCaminho = buscaEmLargura(grafoResidual, origem, destino, caminhoNaMatrizEncontrado);
        }
        this.qtdCaminhosDisjuntos = qtdCaminhos;

        // Imprimi os caminhos disjuntos salvos
        System.out.println("\nCaminhos disjuntos: ");
        int numDisjunto = 1;
        for (int[] caminhoD : this.caminhosDisjuntos) {
        System.out.print(numDisjunto++ + "º: ");
            for (int y = 0; y < caminhoD.length; y++)
                if(y == caminhoD.length-1)
                    System.out.print(caminhoD[y]);
                else
                    System.out.print(caminhoD[y] + " -> ");
            System.out.println();
        }

        // Imprimi a quantidade de destinos
        System.out.println("A quantidade máxima de caminhos disjuntos entre " + origem + " e " + destino + " são " + this.qtdCaminhosDisjuntos + ".");
    }

    // Essa função vai fazer o caminhamento em busca em largura de um vértice origem até um desitno
    // Caso o caminho for encontrado será atualizado no caminhoNaMatrizEncontrado[] e retornará true pois foi encontrado
    private boolean buscaEmLargura(int grafoResidual[][], int origem, int destino, int caminhoNaMatrizEncontrado[]) {
        // Marcar todos os vertices como não visitados ainda
        boolean visitado[] = new boolean[this.vertices];
        for (int i = 0; i < this.vertices; ++i)
            visitado[i] = false;

        // Fila com os vértices que serão adicionados e retirados enquanto procura o caminho disjunto
        Queue<Integer> listaDosVertices = new LinkedList<Integer>();
        // Adicionado na fila a origem de onde quer caminhar e marcando-a como visitada
        listaDosVertices.add(origem);
        visitado[origem] = true;

        // Ainda não foi encontrado o caminho
        caminhoNaMatrizEncontrado[origem] = -1;

        // Começa o loop da busca em largura enquanto a lista de vértices não estiver vazia
        while (listaDosVertices.size() != 0) {
            // Tirar o último adicionado na fila
            // É o vértice que foi possível chegar no caminhamento até o momento
            // Ex: no início esse vérticeOrigemAtual é o mesmo que o origem, possivelmente trocando para o próximo vértice visitado
            int verticeOrigemAtual = listaDosVertices.poll();

            // Loop saindo para verificar todos os possíveis vértices de destinos para o verticeOrigemAtual que foi retirado da fila
            for (int verticeDestinoAtual = 0; verticeDestinoAtual < this.vertices; verticeDestinoAtual++) {
                // Se o vértice de destino ainda não foi visitado
                // E TAMBÉM a aresta do grafoResidual da origemAtual para o destinoAtual não foi utilizada
                // Se atender as duas condições, pode:
                    // adicionar o vérticeDestino na fila de vértices 
                    // adicionar no caminhoNaMatrizAtual para ir salvando o caminho
                    // marca o verticeDestinoAtual como visitado
                if (visitado[verticeDestinoAtual] == false && grafoResidual[verticeOrigemAtual][verticeDestinoAtual] > 0) {
                    // Se o verticeDestinoAtual que chegou é igual ao destino:
                        // salva o fim da caminho
                        // e retorna que encontrou o caminho disjunto
                    if (verticeDestinoAtual == destino) {
                        caminhoNaMatrizEncontrado[verticeDestinoAtual] = verticeOrigemAtual;
                        return true;
                    }
                    listaDosVertices.add(verticeDestinoAtual);
                    caminhoNaMatrizEncontrado[verticeDestinoAtual] = verticeOrigemAtual;
                    visitado[verticeDestinoAtual] = true;
                }
            }
        }

        // Não encontrou mais caminhos disjuntos
        return false;
    }

}
