package com.lucasangelo.arquivo;

import com.lucasangelo.arquivo.utilitarios.ArquivoTextoLeitura;
import com.lucasangelo.grafos.componentes.Aresta;

public class DadosDeArquivos {

    public static int[] capturarDadosGrafo() {
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();
        leitura.abrirArquivo("grafo.in");

        // Capturando se é direcionado e/ou ponderado; e a quantidade de vértices
        String linha = new String("");
        int qtdDados=0;
        while (linha != null){
            linha = leitura.ler();
            if(linha!=null)
                qtdDados++;
        }
        leitura.fecharArquivo();

        leitura.abrirArquivo("grafo.in");
        String dadosStr[] = new String[qtdDados];
        int i=0;
        while (i<qtdDados){
            dadosStr[i] = leitura.ler();
            i++;
        }
        leitura.fecharArquivo();

        int dados[] = new int[qtdDados];
        for(i=0; i<qtdDados; i++){
            dados[i] = Integer.parseInt(dadosStr[i]);
        }

        return dados;
    }

    public static Aresta[] capturarArestas(int vertices, boolean direcionado) throws Exception {
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();
        leitura.abrirArquivo("arestas.in");

        String linha = new String("");
        int qtdArestas=0;
        while (linha != null){
            linha = leitura.ler();
            if(linha!=null)
                qtdArestas++;
        }
        leitura.fecharArquivo();

        int maximoDeArestas = ((vertices -1) * vertices)/2;
        if(qtdArestas>maximoDeArestas && !direcionado)
            throw new Exception(String.format("Quantidade de arestas inválidas! Máximo para um grafo não direcionado de %d vertices é %d", vertices, maximoDeArestas));

        leitura.abrirArquivo("arestas.in");
        String arestasStr[] = new String[qtdArestas];
        int i=0;
        while (i<qtdArestas){
            arestasStr[i] = leitura.ler();
            i++;
        }
        leitura.fecharArquivo();

        Aresta arestas[] = new Aresta[qtdArestas];

        int dados[] = new int[qtdArestas];
        for(i=0; i<qtdArestas; i++){
            String[] dadosLinha = arestasStr[i].split(",", 2);
            int origem = Integer.parseInt(dadosLinha[0])-1;
            int destino = Integer.parseInt(dadosLinha[1])-1;
            arestas[i] = new Aresta(origem, destino);
        }

        return arestas;
    }

}
