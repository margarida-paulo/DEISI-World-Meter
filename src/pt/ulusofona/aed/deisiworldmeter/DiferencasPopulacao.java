package pt.ulusofona.aed.deisiworldmeter;

public class DiferencasPopulacao {

    String nome;

    int anoInicial;
    int anoFinal;

    float diferencaPopulacao;

    public DiferencasPopulacao(String nome, int anoInicial, int anoFinal, float diferencaPopulacao) {
        this.nome = nome;
        this.anoInicial = anoInicial;
        this.anoFinal = anoFinal;
        this.diferencaPopulacao = diferencaPopulacao;
    }
}
