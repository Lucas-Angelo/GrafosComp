package grafo.grafos.componentes;

import grafo.grafos.estruturas.*;

import java.util.ArrayList;
import java.util.LinkedList;

import grafo.arquivo.*;

public class Grafo {

    private Aresta[] arestas;
    private DadosDeArquivos dados;
    private GrafoInfo info;
    
    private ListaDeAdjacencia estrutura;

    private void init(String arquivoStr) throws Exception{
        this.dados = new DadosDeArquivos(arquivoStr);

        this.arestas = dados.getArestas();
        this.info = dados.getGrafoInfo();

        this.estrutura = new ListaDeAdjacencia(info, arestas);
    }

    public Grafo() throws Exception{
        init("grafo.in");
    }
    public Grafo(String arquivoStr) throws Exception{
        init(arquivoStr);
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

    public Aresta[] getArestas() {
        return this.arestas;
    }
    public GrafoInfo getInfo() {
        return this.info;
    }

    public String toString(){
        return this.estrutura.toString();
    }

}
