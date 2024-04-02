package pt.ulusofona.aed.deisiworldmeter;
public class TipoInvalido {
    String nomeDoFicheiro;
    int numeroLinhasOk;
    int numeroLinhasNaoOk;
    int primeiraLinhaNaoOK;
    public TipoInvalido(String nomeDoFicheiro, int numeroLinhasOk, int numeroLinhasNaoOk, int primeiraLinhaNaoOK) {
        this.nomeDoFicheiro = nomeDoFicheiro;
        this.numeroLinhasOk = numeroLinhasOk;
        this.numeroLinhasNaoOk = numeroLinhasNaoOk;
        this.primeiraLinhaNaoOK = primeiraLinhaNaoOK;
    }
    public String toString() {
        return nomeDoFicheiro + " | " + numeroLinhasOk + " | " + numeroLinhasNaoOk + " | " + primeiraLinhaNaoOK;
    }
}
