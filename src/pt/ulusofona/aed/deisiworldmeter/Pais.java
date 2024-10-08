package pt.ulusofona.aed.deisiworldmeter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

import static pt.ulusofona.aed.deisiworldmeter.Main.dataPopulacao;

public class Pais {

    int id;
    String alfa2;
    String alfa3;
    String nome;
    boolean linhaInvalida;

    HashMap<Integer, Populacao> dadosPopulacao = new HashMap<>();
    ArrayList<Cidade> cidades = new ArrayList<>();

    TreeSet<HaversineDistances> haversines = new TreeSet<>(new Comparator<HaversineDistances>() {
        @Override
        public int compare(HaversineDistances c1, HaversineDistances c2) {
            return Float.compare(c2.haversineDistance, c1.haversineDistance);
        }
    });

    int nrLinha; // Para quando removermos uma linha após o parse each file, podermos colocar no primeiraLinhaNaoOk

    int indicadoresEstatisticos;


    public Pais(int id, String alfa2, String alfa3, String nome, boolean linhaInvalida, int indicadoresEstatisticos, int nrLinha) {
        this.id = id;
        this.alfa2 = alfa2;
        this.alfa3 = alfa3;
        this.nome = nome;
        this.linhaInvalida = linhaInvalida;
        this.indicadoresEstatisticos = indicadoresEstatisticos;
        this.nrLinha = nrLinha;
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
