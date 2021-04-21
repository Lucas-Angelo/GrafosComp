package com.lucasangelo.arquivo.utilitarios;

import java.io.*;

public class ArquivoTextoEscrita {

    private BufferedWriter saida;

    public void abrirArquivo(String nomeArquivo) {

        try {
            saida = new BufferedWriter(new FileWriter(nomeArquivo));
        } catch (FileNotFoundException excecao) {

        } catch (IOException excecao) {

        }
    }

    public void fecharArquivo() {

        try {
            saida.close();
        } catch (IOException excecao) {

        }
    }

    public void escrever(String textoEntrada) {

        try {
            saida.write(textoEntrada);
        } catch (IOException excecao) {

        }
    }
}