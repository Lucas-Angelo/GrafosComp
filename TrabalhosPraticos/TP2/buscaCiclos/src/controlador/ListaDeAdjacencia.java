package controlador;

import arquivo.ArquivoTextoLeitura;
import modelo.Aresta;
import modelo.Ciclo;
import modelo.GrafoInfo;

public class ListaDeAdjacencia {

    private int vertices;
    private boolean direcionado;
    private boolean ponderado;

    private int[] arcoOrigem;
    private int[] arcoDestino;
    private int[] arcoPeso;

    private Ciclo ciclos;

    private void init(GrafoInfo grafoInfo, Aresta[] arestas){
        this.vertices = grafoInfo.getQtdVertices();
        this.direcionado = grafoInfo.isDirecionado();
        this.ponderado = grafoInfo.isPonderado();

        int arcosLength = direcionado ? arestas.length : arestas.length*2 ;

        this.arcoDestino = new int[arcosLength];
        this.arcoPeso = new int[arcosLength];
        this.arcoOrigem = new int[this.vertices+1];
        int indexArcoDestino = 0;
        for ( int i=1; i<=this.vertices; i++ ){
            arcoOrigem[i-1] = indexArcoDestino;
            for ( int j=0; j<arestas.length; j++ ){
                if (arestas[j].getOrigem() == i || (arestas[j].getDestino() == i && !this.direcionado) ){
                    int destino = (arestas[j].getOrigem() == i) ? arestas[j].getDestino() : arestas[j].getOrigem(); 
                    arcoDestino[indexArcoDestino] = destino;
                    arcoPeso[indexArcoDestino] = arestas[j].getPeso();
                    indexArcoDestino++;
                }
            }
        }
        arcoOrigem[this.vertices] = indexArcoDestino;
        this.ciclos = new Ciclo();
    }

    public ListaDeAdjacencia(GrafoInfo info, Aresta[] arestas) {
        this.init(info, arestas);
    }

    public int[] getAdjacentes(int vertice){
        vertice -= 1;
        if (vertice>=this.vertices)
            throw new Error("Vertice nao existe no grafo");
        int inicio = this.arcoOrigem[vertice];
        int fim = this.arcoOrigem[vertice+1];

        int size = fim - inicio;
        int[] adjacentes;

        if (size > 0){
            adjacentes = new int[size];

            for (int arco=inicio, index=0; arco<fim ; arco++, index++)
                adjacentes[index] = this.arcoDestino[arco];
        }
        else 
            adjacentes = null;

        return adjacentes;
        
    }

    public void encontrarCicloPermutado(){
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
                        for (int adjacente : this.getAdjacentes(Integer.parseInt(linha.substring(index, index+1)))) {
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
                    int[] caminhoOrdenado = ordenarCaminho(caminho);
                    int[] caminhoOrdenadoEInvertido = inverterCaminho(caminhoOrdenado);
                    if (caminhoNovo(caminhoOrdenado) && caminhoNovo(caminhoOrdenadoEInvertido)) { // Se tanto o ciclo encontrado tanto o ciclo ordenado for novo, adiciona.
                        this.ciclos.add(caminhoOrdenado);
                    }
                    //System.out.println(linha);
                    //se for um caminho novo
                    //ciclos.add(ciclo);
                }
            }
        }
        leitura.fecharArquivo();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nCiclos por permutação:\n");
        for (int i=0; i<this.ciclos.getArco().size(); i++) {
            int ciclo[] = this.ciclos.getArco().get(i);
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

        for(int i = 0; i< this.ciclos.getArco().size() && novo==true; i++) {
            if (equals(this.ciclos.getArco().get(i), caminho)) {
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

    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append("\nLista de adjacência\n");
        for ( int i=0; i<arcoOrigem.length-1;i++ ){
            builder.append(i+1 + " |");
            for (int j=arcoOrigem[i]; j<arcoOrigem[i+1]; j++){
                if(this.direcionado)
                    builder.append(" -> " + (arcoDestino[j]));
                else
                    builder.append(" - " + (arcoDestino[j]));
                if (this.ponderado)
                    builder.append(" (Peso: " + arcoPeso[j] + ")");
                
            }
            builder.append("\n");
        }
        
        return builder.toString();
    }

    public static String arrayToString(int[] array) {
        StringBuilder builder = new StringBuilder();
        for(int i : array) {
            builder.append(i);
        }
        return builder.toString();
    }
}
