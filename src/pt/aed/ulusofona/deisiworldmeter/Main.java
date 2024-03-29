package pt.aed.ulusofona.deisiworldmeter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

enum TipoIdentidade { // por informação do Rodrigo deu-lhe erro com o enumerado dentro da classe Main, por isso meti fora !
    PAIS, CIDADE, TIPO_INVALIDO
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
        int linhaCount = 0;
        Scanner scanner;

        try {
            scanner = new Scanner(ficheiroLido);
        } catch (FileNotFoundException e) {
            return false;
        }

        if (scanner.hasNext()) { // não nos interessa a primeira linha de cada ficheiro
            scanner.nextLine();
            linhaCount++;
        }

        while (scanner.hasNext()) {
            boolean erro = false;
            String linha = scanner.nextLine();
            String[] linhaDividida = linha.split(",");
            switch (tipoData) {
                case 0 -> {
                    if (newPais(linhaDividida, erro, linhaCount)) return false;
                }
                case 1 -> {
                    if (newCidade(linhaDividida, erro, linhaCount)) return false;
                }
                case 2 -> {
                    if (newPopulacao(linhaDividida, erro, linhaCount)) return false;
                }
            }
            linhaCount++;
        }
        return true;
    }

    private static boolean newPopulacao(String[] linhaDividida, boolean erro, int linhaCount) {
        if (linhaDividida.length != 5) {
            erro = true;
            return true;
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
        if (dataInvalidos.get(2).primeiraLinhaNaoOK == 0 && erro) {
            dataInvalidos.get(2).primeiraLinhaNaoOK = linhaCount;
        }
        if (erro) {
            dataInvalidos.get(2).numeroLinhasNaoOk++;
        } else {
            dataInvalidos.get(2).numeroLinhasOk++;
        }
        dataPopulacao.add(new Populacao(Integer.parseInt(linhaDividida[0]), Integer.parseInt(linhaDividida[1]), Integer.parseInt(linhaDividida[2]), Integer.parseInt(linhaDividida[3]), Float.parseFloat(linhaDividida[4]), erro));
        return false;
    }

    private static boolean newCidade(String[] linhaDividida, boolean erro, int linhaCount) {
        if (linhaDividida.length != 6) {
            return true;
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
        if (dataInvalidos.get(1).primeiraLinhaNaoOK == 0 && erro) {
            dataInvalidos.get(1).primeiraLinhaNaoOK = linhaCount;
        }
        if (erro) {
            dataInvalidos.get(1).numeroLinhasNaoOk++;
        } else {
            dataInvalidos.get(1).numeroLinhasOk++;
        }
        dataCidades.add(new Cidade(linhaDividida[0], linhaDividida[1], Integer.parseInt(linhaDividida[2]), Float.parseFloat(linhaDividida[3]), Float.parseFloat(linhaDividida[4]), Float.parseFloat(linhaDividida[5]), erro));
        return false;
    }

    private static boolean newPais(String[] linhaDividida, boolean erro, int linhaCount) {
        if (linhaDividida.length != 4) {
            return true;
        }
        try {
            Integer.parseInt(linhaDividida[0]);
        } catch (NumberFormatException e) {
            erro = true;
            linhaDividida[0] = "-1";
        }
        if (dataInvalidos.get(0).primeiraLinhaNaoOK == 0 && erro) {
            dataInvalidos.get(0).primeiraLinhaNaoOK = linhaCount;
        }
        if (erro) {
            dataInvalidos.get(0).numeroLinhasNaoOk++;
        } else {
            dataInvalidos.get(0).numeroLinhasOk++;
        }
        dataPaises.add(new Pais(Integer.parseInt(linhaDividida[0]), linhaDividida[1], linhaDividida[2], linhaDividida[3], erro));
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

    static ArrayList<String> getObjects(TipoIdentidade tipo) {
        ArrayList<String> novaInformacao = new ArrayList<>();

        if (tipo == TipoIdentidade.TIPO_INVALIDO) {
            for (TipoInvalido dataInvalido : dataInvalidos) {
                novaInformacao.add(dataInvalido.toString());
            }
        }

        if (tipo == TipoIdentidade.PAIS) {
            for (Pais dataPais : dataPaises) {
                novaInformacao.add(dataPais.toString());
            }
        }

        if (tipo == TipoIdentidade.CIDADE) {
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
            /*
            ArrayList country = getObject(TipoIdentidade.PAIS);
            while (i < country.size()) {
                System.out.println(country.get(i).toString());
                i++;
            }

            ArrayList city = getObject(TipoIdentidade.CIDADE);
            while (i < city.size()) {
                System.out.println(city.get(i).toString());
                i++;
            }
            */
            ArrayList inavlideType = getObjects(TipoIdentidade.TIPO_INVALIDO);
            while (i < inavlideType.size()) {
                System.out.println(inavlideType.get(i).toString());
                i++;
            }

        }
    }
}
