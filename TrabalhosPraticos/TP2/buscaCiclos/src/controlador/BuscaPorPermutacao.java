package controlador;

import arquivo.ArquivoTextoEscrita;
import arquivo.ArquivoTextoLeitura;
import modelo.Ciclo;

public class BuscaPorPermutacao {
    private int vertices[];
    private int visitados[];
    private int tamanhoAtual;
    private int ciclos[];
    private ArquivoTextoEscrita arquivoTextoEscrita;

    private Ciclo Ciclos;
    
    private void init(int[] vertices) {
        this.vertices = vertices;
        this.visitados = new int[this.vertices.length];
        this.ciclos = new int[this.vertices.length];
        
        this.Ciclos = new Ciclo();
        this.arquivoTextoEscrita = new ArquivoTextoEscrita();
    }

    public BuscaPorPermutacao(int[] vertices) {
        init(vertices);
    }

    public void permutar() {
        this.arquivoTextoEscrita.abrirArquivo("ciclosPermutados.out");

        // Para permutar todos os tamanhos de ciclos
        for(int i =3; i<=this.vertices.length; i++) { // O tamanho atual tem que ser no mínimo 3 para formar um ciclo
            tamanhoAtual=i;
            permutar(0);
        }
        
        arquivoTextoEscrita.fecharArquivo();
    }
    
    private void permutar(int contador) {
        if(contador == tamanhoAtual) {
            // Quer dizer que encontrou uma permutação já que é do tamanho atual desejado do ciclo
            for(int i=0;i<tamanhoAtual;i++) 
                this.arquivoTextoEscrita.escrever(ciclos[i] + "");
            this.arquivoTextoEscrita.escrever("\n");
            return;
        }    
    
        for(int i=0;i<this.vertices.length;i++) {
            if(visitados[i]==0) {
                visitados[i]=1;
                ciclos[contador] = vertices[i];
                permutar(contador+1);
                visitados[i]=0;
            }    
        }
    }
    public int[] getCiclos() {
        return ciclos;
    }

    public void encontrarCicloPermutado(ListaDeAdjacencia grafo){
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();
        leitura.abrirArquivo("ciclosPermutados.out");
        String linha = "";
        while (linha != null){
            linha = leitura.ler();
            if(linha!=null){
                int last = -1;
                boolean hasTheCicle = true;
                Ciclo cicle = new Ciclo();
                //itera o ciclo
                for ( int i=0; i<=linha.length(); i++ ){
                    if (last>=0){
                        boolean hasTheEgde = false;
                        int index = i==linha.length() ? 0 : i ;
                        //itera os adajcentes do vertice
                        for (int adjacente : grafo.getAdjacentes(Integer.parseInt(linha.substring(index, index+1)))) {
                            if (adjacente == last){
                                hasTheEgde = true;
                                int[] newAresta = {Integer.parseInt(linha.substring(index, index+1)),last};
                                cicle.add(newAresta);
                                break;
                            }
                        }
                        if (!hasTheEgde){
                            hasTheCicle = false;
                            break;
                        }

                    }
                    if (i<linha.length())
                        last = Integer.parseInt(linha.substring(i, i+1));
                }
                if (hasTheCicle){
                    String linhaInt[] = linha.split("", linha.length());
                    int caminho[] = new int[linha.length()];
                    for(int i=0; i<linha.length(); i++){
                        caminho[i] = Integer.parseInt(linhaInt[i]);
                    }
                    // Colocar o menor vertice no inicio e verifica se é um ciclo repetido, se não for, adiciona ciclo
                    int[] caminhoOrdenado = ordenarCaminho(caminho);
                    int[] caminhoOrdenadoEInvertido = inverterCaminho(caminhoOrdenado);
                    if (caminhoNovo(caminhoOrdenado) && caminhoNovo(caminhoOrdenadoEInvertido)) { // Se tanto o ciclo encontrado tanto o ciclo ordenado for novo, adiciona.
                        this.Ciclos.add(caminhoOrdenado);
                    }
                }
            }
        }
        leitura.fecharArquivo();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nCiclos por permutação:\n");
        for (int i=0; i<this.Ciclos.getArco().size(); i++) {
            int ciclo[] = this.Ciclos.getArco().get(i);
            stringBuilder.append(i+1 + "º" + " ciclo: " + ciclo[0]);

            for (int j = 1; j < ciclo.length; j++) {
                stringBuilder.append(" - " + ciclo[j]);
            }
            
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString()); 
        
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

    //  Função que compara um caminho com os ciclos já encontrados e retorna true se for novo
    private boolean caminhoNovo(int[] caminho) {
        boolean novo = true;

        for(int i = 0; i< this.Ciclos.getArco().size() && novo==true; i++) {
            if (equals(this.Ciclos.getArco().get(i), caminho)) {
                novo = false;
            }
        }

        return novo;
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
}