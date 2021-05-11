package controlador;

import arquivo.ArquivoTextoEscrita;

public class BuscaPorPermutacao {
    private int vertices[];
    private int visitados[];
    private int tamanhoAtual;
    private int ciclos[];
    private ArquivoTextoEscrita arquivoTextoEscrita;
    
    private void init(int[] vertices) {
        this.vertices = vertices;
        this.visitados = new int[this.vertices.length];
        this.ciclos = new int[this.vertices.length];
        
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
}