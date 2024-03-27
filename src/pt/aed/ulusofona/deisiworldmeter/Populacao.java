package pt.aed.ulusofona.deisiworldmeter;

public class Populacao {
    int id;
    int ano;
    int populacaoMasculina;
    int populacaoFeminina;
    float densidade;
    int linhaCount;

    public Populacao(int id, int ano, int populacaoMasculina, int populacaoFeminina, float densidade, int linhaCount) {
        this.id = id;
        this.ano = ano;
        this.populacaoMasculina = populacaoMasculina;
        this.populacaoFeminina = populacaoFeminina;
        this.densidade = densidade;
        this.linhaCount = linhaCount;
    }

    public String toString(){
        return id + " | " + ano + " | " + populacaoMasculina + " | " + populacaoFeminina + " | " + densidade;
    }
}
