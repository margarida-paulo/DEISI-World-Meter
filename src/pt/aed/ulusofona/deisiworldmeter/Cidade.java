package pt.aed.ulusofona.deisiworldmeter;

public class Cidade {
    String alfa2;
    String cidade;
    int regiao;
    float populacao;
    float latitude;
    float longitude;
    int linhaCount;
    boolean linhaInvalida;



    static int primeiraLinhaInvalida = 0;
    static int linhasCorretas = 0;
    static int linhasInvalidas = 0;


    public Cidade(String alfa2, String cidade, int regiao, float populacao, float latitude, float longitude, int linhaCount, boolean linhaInvalida) {
        this.alfa2 = alfa2;
        this.cidade = cidade;
        this.regiao = regiao;
        this.populacao = populacao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.linhaCount = linhaCount;
        this.linhaInvalida = linhaInvalida;
    }

    public String toString(){
        return alfa2 + " | " + cidade + " | " + regiao + " | " + populacao + " | " + latitude + " | " + longitude;
    }
}
