package controlador;

import arquivo.ArquivoTextoLeitura;
import modelo.Aresta;
import modelo.GrafoInfo;

public class DadosDeArquivos{

    private final String arquivoStr;
    private ArquivoTextoLeitura leitura;
    private GrafoInfo grafoInfo;
    private Aresta[] arestas;

    private void init() throws Exception{
        this.leitura = new ArquivoTextoLeitura();
        this.leitura.abrirArquivo(this.arquivoStr);

        capturarDadosGrafo();
        capturarArestas();
    }

    public DadosDeArquivos(String arquivoStr) throws Exception{
        this.arquivoStr = arquivoStr;
        init();
    }

    private void capturarDadosGrafo() throws Exception{
        String info;

        try {
            info = this.leitura.ler();
        } catch (Exception e) {
            throw new Exception("A formatação da primeira linha deve seguir o seguinte padrão: ( 0 0 5 ), sendo respectivamente se é direcionado, ponderado e a quantidade de vertices");
        }
        int vertices = Integer.parseInt(info);
        grafoInfo = new GrafoInfo(vertices);
    }

    private void capturarArestas() throws Exception {
        String linha = "";
        int qtdArestas=0;
        while (linha != null){
            linha = this.leitura.ler();
            if(linha!=null)
                qtdArestas++;
        }
        this.leitura.fecharArquivo();

        int qtdVertices = grafoInfo.getQtdVertices();
        int maximoDeArestas = ((qtdVertices -1) * qtdVertices)/2;
        if(qtdArestas>maximoDeArestas)
            throw new Exception(String.format("Quantidade de arestas inválidas! Máximo para um grafo não direcionado de %d vertices é %d", qtdVertices, maximoDeArestas));

        this.leitura.abrirArquivo(arquivoStr);
        this.leitura.ler(); //pulando linha de informações
        String[] arestasStr = new String[qtdArestas];
        int i=0;
        while (i<qtdArestas){
            arestasStr[i] = this.leitura.ler();
            i++;
        }
        this.leitura.fecharArquivo();

        arestas = new Aresta[qtdArestas];

        for(i=0; i<qtdArestas; i++){
            String[] dadosLinha = arestasStr[i].split(",");
            int origem = Integer.parseInt(dadosLinha[0]);
            int destino = Integer.parseInt(dadosLinha[1]);
            arestas[i] = new Aresta(origem, destino);
        }
    
    }

    public Aresta[] getArestas() {
        return arestas;
    }
    public GrafoInfo getGrafoInfo() {
        return grafoInfo;
    }

}
