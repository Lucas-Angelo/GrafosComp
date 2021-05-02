public class BuscaEmProfundidade {
    
    private int[][] arcos;
    private Ciclo ciclos;
    
    private void init(Aresta arestas[]) {
        this.arcos = new int[arestas.length][2];
        for(int i=0; i<arestas.length; i++) {
            this.arcos[i][0] = arestas[i].getOrigem();
            this.arcos[i][1] = arestas[i].getDestino();
        }
        this.ciclos = new Ciclo();

        for (int i = 0; i < this.arcos.length; i++) {
            for (int j = 0; j < this.arcos[i].length; j++) {
                buscarCiclos(new int[]{this.arcos[i][j]});
            }
        }
    }
    
    public BuscaEmProfundidade(Aresta[] arestas) {
        init(arestas);
    }

    private void buscarCiclos(int[] caminho) {
            int verticeUm = caminho[0];
            int verticeTratado;
            int[] caminhoDerivado= new int[caminho.length + 1];

            for (int i = 0; i < this.arcos.length; i++) {
                for (int y = 0; y <= 1; y++) {
                    if (this.arcos[i][y] == verticeUm) { // se o vértice do grafo atual é o mesmo que o primeiro do caminho
                        verticeTratado = this.arcos[i][(y + 1) % 2];
                        if (!foiVisitado(verticeTratado, caminho)) { // se o vértice vizinho/adjacente não está no caminho
                            caminhoDerivado[0] = verticeTratado;
                            // A posição 0 ocupado com o vértice tratado e o resto inserido a partir do index 1
                            for (int j = 0; j < caminho.length; j++) {
                                caminhoDerivado[j + 1] = caminho[j];
                            }
                            // Recursividade para buscar os caminhos dentro do caminho atual
                            buscarCiclos(caminhoDerivado);
                        } else if ((caminho.length > 2) && (verticeTratado == caminho[caminho.length - 1])) { // Se essa condição for verdadeiro: ciclo entrado
                            int[] caminhoOrdenado = ordenarCaminho(caminho);
                            int[] caminhoOrdenadoEInvertido = inverterCaminho(caminhoOrdenado);
                            if (caminhoNovo(caminhoOrdenado) && caminhoNovo(caminhoOrdenadoEInvertido)) { // Se tanto o ciclo encontrado tanto o ciclo ordenado for novo, adiciona.
                                this.ciclos.add(caminhoOrdenado);
                            }
                        }
                    }
                }
            }
    }

    // Função que verifica se dois caminhos são iguais
    private boolean equals(int[] caminhoUm, int[] caminhoDois) {
        boolean igual = (caminhoUm[0] == caminhoDois[0]) && (caminhoUm.length == caminhoDois.length);

        for (int i = 1; i < caminhoUm.length && igual; i++) {
            if (caminhoUm[i] != caminhoDois[i]) {
                igual = false;
            }
        }

        return igual;
    }

    // Função que cria um array inverso de um caminho, colocar na primeira posição o menor identificador de vértice
    private int[] inverterCaminho(int[] caminho) {
        int[] caminhoInvertido = new int[caminho.length];

        for (int i = 0; i < caminho.length; i++) {
            caminhoInvertido[i] = caminho[caminho.length - i - 1];
        }

        return ordenarCaminho(caminhoInvertido);
    }

    // Ordena/gira o caminho para que o primeiro vértice dele seja o de menor identificador
    private int[] ordenarCaminho(int[] caminho) {
        int[] caminhoOrdenado = new int[caminho.length];
        int menorDoCaminho = procurarMenor(caminho);
        int n;

        for(int i=0; i<caminho.length; i++) {
            caminhoOrdenado[i] = caminho[i];
        }

        while (caminhoOrdenado[0] != menorDoCaminho) {
            n = caminhoOrdenado[0];
            // Copia o array do caminho uma posição para trás toda vez que faz a repetição.
            for(int i=0; i<caminhoOrdenado.length-1; i++) {
                caminhoOrdenado[i] = caminhoOrdenado[i+1];
            }
            // Já que o primeiro não é o menor, coloca ele no final do array
            caminhoOrdenado[caminhoOrdenado.length - 1] = n;
        }

        return caminhoOrdenado;
    }

    //  Função que compara um caminho com os ciclos já encontrados e retorna true se for novo
    private boolean caminhoNovo(int[] caminho) {
        boolean novo = true;

        for(int i = 0; i< this.ciclos.getArco().size() && novo==true; i++) {
            if (equals(this.ciclos.getArco().get(i), caminho)) {
                novo = false;
            }
        }

        return novo;
    }

    // Função que procura o menor identificador de vértice em um caminho, usado para ordenar pelo menor
    private int procurarMenor(int[] caminho) {
        int menor = caminho[0];

        for (int i=0; i<caminho.length; i++) {
            if (caminho[i] < menor) {
                menor = caminho[i];
            }
        }
        
        return menor;
    }
    
    // Função que verifica se um vértice qualquer já faz parte de algum caminho, ou seja, já foi visitado.
    private boolean foiVisitado(int vertice, int[] caminho) {
        boolean visitado = false;
        
        for (int i=0; i<caminho.length && visitado==false; i++) {
            if (caminho[i] == vertice) {
                visitado = true;
            }
        }
        
        return visitado;
    }
    
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Ciclos por busca em profundidade:\n");
        for (int i=0; i<this.ciclos.getArco().size(); i++) {
            int ciclo[] = this.ciclos.getArco().get(i);
            stringBuilder.append(i+1 + "º" + " ciclo: " + ciclo[0]);

            for (int j = 1; j < ciclo.length; j++) {
                stringBuilder.append(" - " + ciclo[j]);
            }
            
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}