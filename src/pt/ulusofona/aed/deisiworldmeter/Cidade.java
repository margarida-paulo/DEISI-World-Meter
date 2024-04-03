package pt.ulusofona.aed.deisiworldmeter;

public class Cidade {
    String alfa2;
    String cidade;
    String regiao;
    float populacao;
    float latitude;
    float longitude;
    boolean linhaInvalida;



    public Cidade(String alfa2, String cidade, String regiao, float populacao, float latitude, float longitude, boolean linhaInvalida) {
        this.alfa2 = alfa2;
        this.cidade = cidade;
        this.regiao = regiao;
        this.populacao = populacao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.linhaInvalida = linhaInvalida;
    }

    public String toString(){
        return cidade + " | " + alfa2.toUpperCase() + " | " + regiao + " | " + (int)populacao + " | (" + latitude + "," + longitude + ")";
    }
}
