package pt.aed.ulusofona.deisiworldmeter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Pais> dataPaises = new ArrayList<>();
    public static ArrayList<Cidade> dataCidades = new ArrayList<>();
    public static ArrayList<Populacao> dataPopulacao = new ArrayList<>();

    /**
     *
     * @param ficheiro Ficheiro .csv a ser lido
     * @param tipoData 0 -> Ficheiro de Países ; 1 -> Ficheiro de cidades ; 2 -> Ficheiro de População
     * @return False se a leitura do ficheiro falhar ou este estiver desformatado, true se estiver tudo correto.
     */
    static Boolean parseEachFile(String ficheiro, int tipoData){
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
                String linha = scanner.nextLine();
                String[] linhaDividida = linha.split(",");
                switch (tipoData){
                    case 0 -> {
                        if (linhaDividida.length != 4){
                            return false;
                        }
                        try {
                            Integer.parseInt(linhaDividida[0]);
                        } catch (NumberFormatException e) {
                            linhaDividida[0] = "-1";
                        }
                        dataPaises.add(new Pais(Integer.parseInt(linhaDividida[0]), linhaDividida[1], linhaDividida[2], linhaDividida[3], linhaCount));
                    }
                    case 1 -> {
                        if (linhaDividida.length != 6){
                            return false;
                        }

                        try {
                            Integer.parseInt(linhaDividida[2]);
                        } catch (NumberFormatException e) {
                            linhaDividida[2] = "-1";
                        }

                        try {
                            Float.parseFloat(linhaDividida[3]);
                        } catch (NumberFormatException f) {
                            linhaDividida[3] = "-1.0";
                        }

                        try {
                            Float.parseFloat(linhaDividida[4]);
                        } catch (NumberFormatException g) {
                            linhaDividida[4] = "-1.0";
                        }

                        try {
                            Float.parseFloat(linhaDividida[5]);
                        } catch (NumberFormatException h) {
                            linhaDividida[5] = "-1.0";
                        }

                        dataCidades.add(new Cidade(linhaDividida[0],linhaDividida[1], Integer.parseInt(linhaDividida[2]), Float.parseFloat(linhaDividida[3]), Float.parseFloat(linhaDividida[4]), Float.parseFloat(linhaDividida[5]), linhaCount));
                    }
                    case 2 -> {
                        if (linhaDividida.length != 5){
                            return false;
                        }

                        try {
                            Integer.parseInt(linhaDividida[0]);
                        } catch (NumberFormatException e) {
                            linhaDividida[0] = "-1";
                        }

                        try {
                            Integer.parseInt(linhaDividida[1]);
                        } catch (NumberFormatException f) {
                            linhaDividida[1] = "-1";
                        }

                        try {
                            Integer.parseInt(linhaDividida[2]);
                        } catch (NumberFormatException g) {
                            linhaDividida[2] = "-1";
                        }

                        try {
                            Integer.parseInt(linhaDividida[3]);
                        } catch (NumberFormatException h) {
                            linhaDividida[3] = "-1";
                        }

                        try {
                            Float.parseFloat(linhaDividida[4]);
                        } catch (NumberFormatException h) {
                            linhaDividida[4] = "-1.0";
                        }
                        dataPopulacao.add(new Populacao(Integer.parseInt(linhaDividida[0]), Integer.parseInt(linhaDividida[1]), Integer.parseInt(linhaDividida[2]), Integer.parseInt(linhaDividida[3]), Float.parseFloat(linhaDividida[4]), linhaCount));
                    }
                }

            }
            linhaCount++;
        }
        return true;
    }

    /**
     *
     * @param pasta Pasta que contém os ficheiros .csv
     * @return True, caso tenha conseguido ler todos os ficheiros corretamente, ou false, caso não tenha.
     */
    static Boolean parseFiles(File pasta) {
        if (!parseEachFile(pasta + "/paises.csv", 0)) {
            return false;
        }
        if (!parseEachFile(pasta + "/cidades.csv", 1)){
            return false;
        }
        if (!parseEachFile(pasta + "/populacao.csv", 2)){
           return false;
       }
        return true;
    }

    public static void main(String[] args) {
        if (parseFiles(new File("Data"))){
            int i = 0;
            while (i < dataPopulacao.size()){
                System.out.println(dataPopulacao.get(i).toString());
                i++;
            }
        }
    }
}
