import grafo.grafos.componentes.*;
import grafo.grafos.estruturas.*;

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

    // public void ciclosEmProfundidade() {
    //     Profundidade ciclos = new Profundidade(arestas);
    // }

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
