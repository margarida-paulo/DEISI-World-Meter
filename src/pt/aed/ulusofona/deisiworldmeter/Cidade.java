package pt.aed.ulusofona.deisiworldmeter;

public class Cidade {
    // alfa2,cidade,regiao,populacao,latitude,longitude
    //ad,andorra la vella,07,20430.0,42.5,1.5166667

    String alfa2;
    String cidade;
    int regiao;
    float populacao;
    float latitude;
    float longitude;
    int linhaCount;

    public Cidade(String alfa2, String cidade, int regiao, float populacao, float latitude, float longitude, int linhaCount) {
        this.alfa2 = alfa2;
        this.cidade = cidade;
        this.regiao = regiao;
        this.populacao = populacao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.linhaCount = linhaCount;
    }

    public String toString(){
        return alfa2 + " | " + cidade + " | " + regiao + " | " + populacao + " | " + latitude + " | " + longitude;
    }
}
