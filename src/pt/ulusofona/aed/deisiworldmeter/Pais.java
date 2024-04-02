package pt.ulusofona.aed.deisiworldmeter;

public class Pais {

    int id;
    String alfa2;
    String alfa3;
    String nome;
    int linhaCount;
    boolean linhaInvalida;


    static int primeiraLinhaInvalida = 0;
    static int linhasCorretas = 0;
    static int linhasInvalidas = 0;

    public Pais(int id, String alfa2, String alfa3, String nome, int linhaCount, boolean linhaInvalida) {
        this.id = id;
        this.alfa2 = alfa2;
        this.alfa3 = alfa3;
        this.nome = nome;
        this.linhaCount = linhaCount;
        this.linhaInvalida = linhaInvalida;
    }

    public String toString(){
        return nome + " | " + id + " | " + alfa2.toUpperCase() + " | " + alfa3.toUpperCase();
    }
}
