package com.lucasangelo;

public class SubGrafos {

    private boolean direcionado;
    private boolean completo;
    private int vertices;

    public SubGrafos(boolean direcionado, boolean completo, int vertices) {
        this.direcionado=direcionado;
        this.completo=completo;
        this.vertices=vertices;
    }

    public SubGrafos(int vertices) {
        this.direcionado=false;
        this.completo=true;
        this.vertices=vertices;
    }

    public double calcular() {
        double qtd=0;

        int n=this.vertices;

        for(int k=2; k<=n; k++) {
            qtd += calcular(k, n);
        }
        qtd += fatorial(n/1);

        return qtd;
    }

    private double calcular(int k, int n) {
        double result = (fatorial(n))/( (fatorial(k)) * (fatorial(n-k)) ) * Math.pow(2, (k));
        return result;
    }

    private double fatorial(double num)
    {
        if (num >= 1.0)
            return num * fatorial(num - 1.0);
        else
            return 1.0;
    }

}
