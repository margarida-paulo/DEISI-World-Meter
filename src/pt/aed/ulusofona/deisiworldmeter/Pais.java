package pt.aed.ulusofona.deisiworldmeter;

public class Pais {

    int id;
    String alfa2;
    String alfa3;
    String nome;

    int linhaCount;

    public Pais(int id, String alfa2, String alfa3, String nome, int linhaCount) {
        this.id = id;
        this.alfa2 = alfa2;
        this.alfa3 = alfa3;
        this.nome = nome;
        this.linhaCount = linhaCount;
    }

    public String toString(){
        return nome + " | " + id + " | " + alfa2.toUpperCase() + " | " + alfa3.toUpperCase();
    }
}
