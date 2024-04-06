package pt.ulusofona.aed.deisiworldmeter;

import static pt.ulusofona.aed.deisiworldmeter.Main.dataPopulacao;

public class Pais {

    int id;
    String alfa2;
    String alfa3;
    String nome;
    boolean linhaInvalida;

    int indicadoresEstatisticos;


    public Pais(int id, String alfa2, String alfa3, String nome, boolean linhaInvalida, int indicadoresEstatisticos) {
        this.id = id;
        this.alfa2 = alfa2;
        this.alfa3 = alfa3;
        this.nome = nome;
        this.linhaInvalida = linhaInvalida;
        this.indicadoresEstatisticos = indicadoresEstatisticos;
    }

    public String toString(){
        if (id > 700){
            return nome + " | " + id + " | " + alfa2.toUpperCase() + " | " + alfa3.toUpperCase() + " | " + indicadoresEstatisticos;
        }
        else {
            return nome + " | " + id + " | " + alfa2.toUpperCase() + " | " + alfa3.toUpperCase();
        }
    }
}
