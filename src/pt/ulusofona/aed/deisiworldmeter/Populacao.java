package pt.ulusofona.aed.deisiworldmeter;

public class Populacao {
    int id;
    int ano;
    int populacaoMasculina;
    int populacaoFeminina;
    float densidade;
    int linhaCount;
    boolean linhaInvalida;



    static int primeiraLinhaInvalida = 0;
    static int linhasCorretas = 0;
    static int linhasInvalidas = 0;

    public Populacao(int id, int ano, int populacaoMasculina, int populacaoFeminina, float densidade, int linhaCount, boolean linhaInvalida) {
        this.id = id;
        this.ano = ano;
        this.populacaoMasculina = populacaoMasculina;
        this.populacaoFeminina = populacaoFeminina;
        this.densidade = densidade;
        this.linhaCount = linhaCount;
        this.linhaInvalida = linhaInvalida;
    }

    public String toString(){
        return id + " | " + ano + " | " + populacaoMasculina + " | " + populacaoFeminina + " | " + densidade;
    }
}
