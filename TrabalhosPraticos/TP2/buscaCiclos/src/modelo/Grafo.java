package modelo;

import arquivo.ArquivoTextoLeitura;
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

        ArquivoTextoLeitura file = new ArquivoTextoLeitura();
        file.abrirArquivo("ciclosPermutados.out");

        String line;
        int ciclos=0;

        do {
            line = file.ler();
            //System.out.println(line);
            if (line!=null){
                int last = -1;
                boolean hasTheCicle = true;
                //itera o ciclo
                for ( int i=0; i<=line.length(); i++ ){
                    if (last>=0){
                        boolean hasTheEgde = false;
                        int index = i==line.length() ? 0 : i ;
                        //itera os adajcentes do vertice
                        for (int adjacente : this.estrutura.getAdjacentes(Integer.parseInt(line.substring(index, index+1)))) {
                            if (adjacente == last){
                                hasTheEgde = true;
                                break;
                            }
                        }
                        if (!hasTheEgde){
                            hasTheCicle = false;
                            break;
                        }

                    }
                    if (i<line.length())
                        last = Integer.parseInt(line.substring(i, i+1));
                }
                if (hasTheCicle)
                    System.out.println(line);

            }
        } while (line!=null);
        
    }

    public void encontrarCicloPermutado() {
        estrutura.encontrarCicloPermutado();
    }

}
