package pt.aed.ulusofona.deisiworldmeter;

public class Populacao {
    int id;
    int ano;
    int populacaoMasculina;
    int populacaoFeminina;
    float densidade;
    boolean linhaInvalida;

    public Populacao(int id, int ano, int populacaoMasculina, int populacaoFeminina, float densidade, boolean linhaInvalida) {
        this.id = id;
        this.ano = ano;
        this.populacaoMasculina = populacaoMasculina;
        this.populacaoFeminina = populacaoFeminina;
        this.densidade = densidade;
        this.linhaInvalida = linhaInvalida;
    }

    public String toString() {
        return id + " | " + ano + " | " + populacaoMasculina + " | " + populacaoFeminina + " | " + densidade;
    }
}
