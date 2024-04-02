package pt.ulusofona.aed.deisiworldmeter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

enum TipoEntidade {
    PAIS, CIDADE, INPUT_INVALIDO
}

public class Main {
    public static ArrayList<Pais> dataPaises = new ArrayList<>();
    public static ArrayList<Cidade> dataCidades = new ArrayList<>();
    public static ArrayList<Populacao> dataPopulacao = new ArrayList<>();
    public static ArrayList<TipoInvalido> dataInvalidos = new ArrayList<>();

    /**
     * @param ficheiro Ficheiro .csv a ser lido
     * @param tipoData 0 -> Ficheiro de Países ; 1 -> Ficheiro de cidades ; 2 -> Ficheiro de População
     * @return False se a leitura do ficheiro falhar ou este estiver desformatado, true se estiver tudo correto.
     */
    static Boolean parseEachFile(String ficheiro, int tipoData) {
        File ficheiroLido = new File(ficheiro);
        boolean primeiraLinha = true;
        int linhaCount = 0;
        Scanner scanner;

        try {
            scanner = new Scanner(ficheiroLido);
        } catch (FileNotFoundException e) {
            return false;
        }

        while (scanner.hasNext()){
            if (primeiraLinha){
                scanner.nextLine();
                primeiraLinha = false;
            }
            else {
                boolean erro = false;
                String linha = scanner.nextLine();
                String[] linhaDividida = linha.split(",");
                switch (tipoData){
                    case 0 -> {
                        if (newPais(linhaDividida, erro, linhaCount)){
                            return false;
                        }
                    }
                    case 1 -> {
                        if (newCidade(linhaDividida, erro, linhaCount)){
                            return false;
                        }
                    }
                    case 2 -> {
                        if (newPopulacao(linhaDividida, erro, linhaCount)){
                            return false;
                        }
                    }
                }

            }
            linhaCount++;
        }
        return true;
    }

    private static boolean newPopulacao(String[] linhaDividida, boolean erro, int linhaCount) {
        if (linhaDividida.length != 5){
            Populacao.primeiraLinhaInvalida = linhaCount;
            return false;
        }

        try {
            Integer.parseInt(linhaDividida[0]);
        } catch (NumberFormatException e) {
            erro = true;
            linhaDividida[0] = "-1";
        }

        try {
            Integer.parseInt(linhaDividida[1]);
        } catch (NumberFormatException f) {
            erro = true;
            linhaDividida[1] = "-1";
        }

        try {
            Integer.parseInt(linhaDividida[2]);
        } catch (NumberFormatException g) {
            erro = true;
            linhaDividida[2] = "-1";
        }

        try {
            Integer.parseInt(linhaDividida[3]);
        } catch (NumberFormatException h) {
            erro = true;
            linhaDividida[3] = "-1";
        }

        try {
            Float.parseFloat(linhaDividida[4]);
        } catch (NumberFormatException h) {
            erro = true;
            linhaDividida[4] = "-1.0";
        }
        if (Populacao.primeiraLinhaInvalida == 0 && erro){
            Populacao.primeiraLinhaInvalida = linhaCount;
        }
        if (erro){
            Populacao.linhasInvalidas++;
        } else {
            Populacao.linhasCorretas++;
        }
        dataPopulacao.add(new Populacao(Integer.parseInt(linhaDividida[0]), Integer.parseInt(linhaDividida[1]), Integer.parseInt(linhaDividida[2]), Integer.parseInt(linhaDividida[3]), Float.parseFloat(linhaDividida[4]), linhaCount, erro));
        return false;
    }

    private static boolean newCidade(String[] linhaDividida, boolean erro, int linhaCount) {
        if (linhaDividida.length != 6){
            Cidade.primeiraLinhaInvalida = linhaCount;
            return false;
        }

        try {
            Integer.parseInt(linhaDividida[2]);
        } catch (NumberFormatException e) {
            erro = true;
            linhaDividida[2] = "-1";
        }

        try {
            Float.parseFloat(linhaDividida[3]);
        } catch (NumberFormatException f) {
            erro = true;
            linhaDividida[3] = "-1.0";
        }

        try {
            Float.parseFloat(linhaDividida[4]);
        } catch (NumberFormatException g) {
            erro = true;
            linhaDividida[4] = "-1.0";
        }

        try {
            Float.parseFloat(linhaDividida[5]);
        } catch (NumberFormatException h) {
            erro = true;
            linhaDividida[5] = "-1.0";
        }
        if (Cidade.primeiraLinhaInvalida == 0 && erro){
            Cidade.primeiraLinhaInvalida = linhaCount;
        }
        if (erro){
            Cidade.linhasInvalidas++;
        } else {
            Cidade.linhasCorretas++;
        }
        dataCidades.add(new Cidade(linhaDividida[0], linhaDividida[1], Integer.parseInt(linhaDividida[2]), Float.parseFloat(linhaDividida[3]), Float.parseFloat(linhaDividida[4]), Float.parseFloat(linhaDividida[5]), linhaCount, erro));
        return false;
    }

    private static boolean newPais(String[] linhaDividida, boolean erro, int linhaCount) {
        if (linhaDividida.length != 4){
            Pais.primeiraLinhaInvalida = linhaCount;
            return false;
        }
        try {
            Integer.parseInt(linhaDividida[0]);
        } catch (NumberFormatException e) {
            erro = true;
            linhaDividida[0] = "-1";
        }
        if (Pais.primeiraLinhaInvalida == 0 && erro){
            Pais.primeiraLinhaInvalida = linhaCount;
        }
        if (erro){
            Pais.linhasInvalidas++;
        } else {
            Pais.linhasCorretas++;
        }
        dataPaises.add(new Pais(Integer.parseInt(linhaDividida[0]), linhaDividida[1], linhaDividida[2], linhaDividida[3], linhaCount, erro));
        return false;
    }

    /**
     * @param pasta Pasta que contém os ficheiros .csv
     * @return True, caso tenha conseguido ler todos os ficheiros corretamente, ou false, caso não tenha.
     */
    static Boolean parseFiles(File pasta) {
        if (!parseEachFile(pasta + "/paises.csv", 0)) {
            return false;
        }
        if (!parseEachFile(pasta + "/cidades.csv", 1)) {
            return false;
        }
        if (!parseEachFile(pasta + "/populacao.csv", 2)) {
            return false;
        }
        return true;
    }

    static ArrayList<String> getObjects(TipoEntidade tipo) {
        ArrayList<String> novaInformacao = new ArrayList<>();

        if (tipo == TipoEntidade.INPUT_INVALIDO) {
            for (TipoInvalido dataInvalido : dataInvalidos) {
                novaInformacao.add(dataInvalido.toString());
            }
        }

        if (tipo == TipoEntidade.PAIS) {
            for (Pais dataPais : dataPaises) {
                novaInformacao.add(dataPais.toString());
            }
        }

        if (tipo == TipoEntidade.CIDADE) {
            for (Cidade dataCidade : dataCidades) {
                novaInformacao.add(dataCidade.toString());
            }
        }
        return novaInformacao;
    }

    public static void main(String[] args) {
        dataInvalidos.add(new TipoInvalido("paises.csv", 0, 0, 0)); // dataInvalidos.get(0)
        dataInvalidos.add(new TipoInvalido("cidades.csv", 0, 0, 0)); // dataInvalidos.get(1)
        dataInvalidos.add(new TipoInvalido("populacao.csv", 0, 0, 0)); // dataInvalidos.get(2)

        System.out.println("Bem vindo ao DEISI World Meter");
        System.out.println();
        if (parseFiles(new File("Data"))) {
            int i = 0;

            ArrayList country = getObjects(TipoEntidade.PAIS);
            while (i < country.size()) {
                System.out.println(country.get(i).toString());
                i++;
            }

            ArrayList city = getObjects(TipoEntidade.CIDADE);
            while (i < city.size()) {
                System.out.println(city.get(i).toString());
                i++;
            }

            ArrayList inavlideType = getObjects(TipoEntidade.INPUT_INVALIDO);
            while (i < inavlideType.size()) {
                System.out.println(inavlideType.get(i).toString());
                i++;
            }
        }
    }
}
