package pt.ulusofona.aed.deisiworldmeter;
/*
Elementos:

        - boolean success
        - String error
        - String result

        Comando válido → error = null

        Comando inválido → error = “Comando invalido”

 */
public class Result {
    boolean success;
    String error;
    String result;

    public Result(boolean success, String error, String result) {
        this.success = success;
        this.error = error;
        this.result = result;
    }

    public Result() {
    }
}

