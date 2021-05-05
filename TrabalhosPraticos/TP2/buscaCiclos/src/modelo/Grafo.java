package modelo;

import controlador.BuscaEmProfundidade;
import controlador.BuscaPorPermutacao;
import controlador.DadosDeArquivos;
import controlador.ListaDeAdjacencia;

public class Grafo {

    private Aresta[] arestas;
    private GrafoInfo info;
    
    private ListaDeAdjacencia estrutura;

    private void init(String arquivoStr) throws Exception{
        DadosDeArquivos dados = new DadosDeArquivos(arquivoStr);

        this.arestas = dados.getArestas();
        this.info = dados.getGrafoInfo();

        this.estrutura = new ListaDeAdjacencia(info, arestas); // Gerar a lista de adjacencia para esse grafo, pra poder imprimir
    }

    public Grafo() throws Exception{
        init("grafo.in");
    }
    public Grafo(String arquivoStr) throws Exception{
        init(arquivoStr);
    }

    public Aresta[] getArestas() {
        return this.arestas;
    }
    public GrafoInfo getInfo() {
        return this.info;
    }

    // public void ciclosPorPermutacao() {
    //     int qtdVertices = info.getQtdVertices();


    //     List<Ciclo> ciclos;
    //     //pra cada vertice
    //     for(int i=0; i<qtdVertices; i++) {
    //         //encontrar todos os ciclos possíveis com outros vertices
            
    //         for(int j=i+1;j<=qtdVertices;j++){
                
    //         }
    //         //verificar se existe cada ciclo no grafo real
    //         //se exite add, sen faz nada
            
    //     }
        
    // }

    public void ciclosEmProfundidade() {
        boolean[] acessados = new boolean[this.info.getQtdVertices()];
        for (int i = 0; i < acessados.length; i++) 
            acessados[i] = false;
        ciclosEmProfundidade(0, -1, acessados);
    }
    private void ciclosEmProfundidade(int vertice, int pai, boolean[] acessados){
        System.out.println(pai+1+"-"+(vertice+1));
        if (acessados[vertice]){
            //add ciclo
            System.out.println();
            return;
        }
        boolean[] cpyAcessados = acessados.clone();
        cpyAcessados[vertice] = true;
        int[] adjacentes = this.estrutura.getAdjacentes(vertice);
        for (int adjacente : adjacentes) {
            if (adjacente != pai){
                ciclosEmProfundidade(adjacente, vertice, cpyAcessados);
            }
            
        }
    }

    public String toString(){
        return this.estrutura.toString();
    }

    public void buscarEmProfundidade() {
        BuscaEmProfundidade buscaEmProfundidade = new BuscaEmProfundidade(arestas);
        System.out.println(buscaEmProfundidade.toString());
    }

    public void buscarPorPermutacao(int n) {
        int vertices[] = new int[n];

        for(int i=0; i<n; i++)
            vertices[i] = i+1; // Poderia passar um new int[]{1, 2, 3, 4, 5, 6} no contrutor também

        BuscaPorPermutacao buscaPorPermutacao = new BuscaPorPermutacao(vertices);
        buscaPorPermutacao.permutar();
    }

    

}
