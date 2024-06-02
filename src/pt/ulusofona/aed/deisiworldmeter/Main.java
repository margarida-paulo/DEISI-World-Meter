package pt.ulusofona.aed.deisiworldmeter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

enum TipoEntidade {
    PAIS, CIDADE, INPUT_INVALIDO
}

public class Main {
    public static ArrayList<Pais> dataPaises = new ArrayList<>(); //dataPaises, vai armazenar uma lista de objetos do tipo Pais
    public static ArrayList<Cidade> dataCidades = new ArrayList<>(); //dataCidades, vai armazenar uma lista de objetos do tipo Cidade
    public static ArrayList<Populacao> dataPopulacao = new ArrayList<>(); //dataPopulacao, vai armazenar uma lista de objetos do tipo Populacao
    public static ArrayList<TipoInvalido> dataInvalidos = new ArrayList<>(); //dataInvalidos, vai armazenar uma lista de objetos do tipo TipoInvalido
    public static ArrayList<Cidade> citiesSortedByPopulation = new ArrayList<>();

    // Dentro de cada país do countriesById, há um HashSet que tem os dados populacionais referentes a esse país
    public static HashMap<Integer, Pais> countriesById = new HashMap<>();

    // Dentro de cada país do countriesByAlfa2, há de haver uma estrutura com as cidades desse país
    public static HashMap<String, Pais> countriesByAlfa2 = new HashMap<>();
    public static HashMap<String, Pais> countriesByName = new HashMap<>();

    static boolean alfa2EncontradoEmPaises(String alfa2) { //alfa2EncontradoEmPaises é uma função que verifica se um determinado alfa2 está presente na lista de países (dataPaises)
        for (Pais dataPais : dataPaises) {
            if (Objects.equals(alfa2, dataPais.alfa2)) {
                return true;
            }
        }
        return false;
    }

    static boolean idEncontradoEmPaises(int id) { //idEncontradoEmPaises é uma função que verifica se um determinado id está presente na lista de países (dataPaises)
        for (Pais dataPais : dataPaises) {
            if (id == dataPais.id) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param ficheiro Ficheiro .csv a ser lido
     * @param tipoData 0 -> Ficheiro de Países ; 1 -> Ficheiro de cidades ; 2 -> Ficheiro de População
     * @return False se a leitura do ficheiro falhar ou este estiver desformatado, true se estiver tudo correto.
     */
    static boolean parseEachFile(String ficheiro, int tipoData) { //parseEachFile é uma função que retorna um boolean. Esta indica se o processo de análise do arquivo foi bem-sucedido ou não. Aceita dois parâmetros: ficheiro, que é o caminho para o arquivo a ser analisado, e tipoData, que indica o tipo de dados que está a ser analisado
        File ficheiroLido = new File(ficheiro); //Criamos um novo objeto File com base no caminho do arquivo fornecido.
        int linhaCount = 1;
        Scanner scanner;

        try { //Com o scanner tentamos ler o arquivo que foi fornecido e tentamos apanhar o erro "FileNotFoundException" (se o arquivo não foi encontrado). Em caso de ser apanhado retornamos false e sabemos que a análise do arquivo falhou.
            scanner = new Scanner(ficheiroLido);
        } catch (FileNotFoundException e) {
            return false;
        }

        if (scanner.hasNext()) { //não nos interessa a primeira linha de cada ficheiro
            if (linhaCount == 1) { //verificamos se é a primeira linha
                scanner.nextLine(); //ignoramos a primeira linha
            } else {
                scanner.nextLine();
            }
            linhaCount++;
        }

        while (scanner.hasNext()) { //Entramos num loop enquanto existirem linhas no arquivo

            boolean erro = false;
            String linha = scanner.nextLine(); //lemos a proxima linha do arquivo
            String[] linhaDividida = linha.split(","); //dividimos a linha em partes separadas, com base na virgula e estas ficam armazenadas num array
            switch (tipoData) { //Utilizamos um switch-case para determinarmos que tipo de dados está a ser analisado
                case 0 -> newPais(linhaDividida, erro, linhaCount);
                case 1 -> newCidade(linhaDividida, erro, linhaCount);
                case 2 -> newPopulacao(linhaDividida, erro, linhaCount);
            }
            linhaCount++; //incrementamos as linhas a cada iteração do loop
        }
        return true; //retornamos true caso as análises tenham sido bem sucedidas
    }

    private static void newPopulacao(String[] linhaDividida, boolean erro, int linhaCount) {

        for (int i = 0; i < linhaDividida.length; i++) {
            if (linhaDividida[i].isEmpty() && dataInvalidos.get(2).primeiraLinhaNaoOK == -1) {
                dataInvalidos.get(2).numeroLinhasNaoOk++;
                dataInvalidos.get(2).primeiraLinhaNaoOK = linhaCount;
                return;
            } else if (linhaDividida[i].isEmpty()) {
                dataInvalidos.get(2).numeroLinhasNaoOk++;
                return;
            }
        }

        if (linhaDividida.length != 5) {
            dataInvalidos.get(2).numeroLinhasNaoOk++;
            if (dataInvalidos.get(2).primeiraLinhaNaoOK == -1) {
                dataInvalidos.get(2).primeiraLinhaNaoOK = linhaCount;
            }
            return;
        }

        try {
            Integer.parseInt(linhaDividida[0]);
        } catch (NumberFormatException e) {
            erro = true;
        }

        try {
            Integer.parseInt(linhaDividida[1]);
        } catch (NumberFormatException f) {
            erro = true;
        }

        try {
            Integer.parseInt(linhaDividida[2]);
        } catch (NumberFormatException g) {
            erro = true;
        }

        try {
            Integer.parseInt(linhaDividida[3]);
        } catch (NumberFormatException h) {
            erro = true;
        }

        try {
            Float.parseFloat(linhaDividida[4]);
        } catch (NumberFormatException h) {
            erro = true;
        }
        if (dataInvalidos.get(2).primeiraLinhaNaoOK == -1 && erro) {
            dataInvalidos.get(2).primeiraLinhaNaoOK = linhaCount;
        }
        if (erro || !idEncontradoEmPaises(Integer.parseInt(linhaDividida[0]))) {
            dataInvalidos.get(2).numeroLinhasNaoOk++;
            if (dataInvalidos.get(2).primeiraLinhaNaoOK == -1) {
                dataInvalidos.get(2).primeiraLinhaNaoOK = linhaCount;
            }
            return;
        } else {
            dataInvalidos.get(2).numeroLinhasOk++;
        }
        dataPopulacao.add(new Populacao(Integer.parseInt(linhaDividida[0]), Integer.parseInt(linhaDividida[1]), Integer.parseInt(linhaDividida[2]), Integer.parseInt(linhaDividida[3]), Float.parseFloat(linhaDividida[4]), erro));

        // Dentro do CountriesById existe um HashMap da população de cada país, onde a chave é o ano desses dados.
        countriesById.get(Integer.parseInt(linhaDividida[0])).dadosPopulacao.put(Integer.parseInt(linhaDividida[1]), new Populacao(Integer.parseInt(linhaDividida[0]), Integer.parseInt(linhaDividida[1]), Integer.parseInt(linhaDividida[2]), Integer.parseInt(linhaDividida[3]), Float.parseFloat(linhaDividida[4]), erro));
        return;
    }

    private static void newCidade(String[] linhaDividida, boolean erro, int linhaCount) {

        for (int i = 0; i < linhaDividida.length; i++) {
            if (linhaDividida[i].isEmpty() && dataInvalidos.get(1).primeiraLinhaNaoOK == -1) {
                dataInvalidos.get(1).numeroLinhasNaoOk++;
                dataInvalidos.get(1).primeiraLinhaNaoOK = linhaCount;
                return;
            } else if (linhaDividida[i].isEmpty()) {
                dataInvalidos.get(1).numeroLinhasNaoOk++;
                return;
            }
        }

        if (linhaDividida.length != 6) {
            dataInvalidos.get(1).numeroLinhasNaoOk++;
            if (dataInvalidos.get(1).primeiraLinhaNaoOK == -1) {
                dataInvalidos.get(1).primeiraLinhaNaoOK = linhaCount;
            }
            return;
        }

        try {
            Float.parseFloat(linhaDividida[3]);
        } catch (NumberFormatException f) {
            erro = true;
        }

        try {
            Float.parseFloat(linhaDividida[4]);
        } catch (NumberFormatException g) {
            erro = true;
        }

        try {
            Float.parseFloat(linhaDividida[5]);
        } catch (NumberFormatException h) {
            erro = true;
        }

        if (erro || !alfa2EncontradoEmPaises(linhaDividida[0])) {
            dataInvalidos.get(1).numeroLinhasNaoOk++;
            if (dataInvalidos.get(1).primeiraLinhaNaoOK == -1) {
                dataInvalidos.get(1).primeiraLinhaNaoOK = linhaCount;
            }
            return;
        } else {
            dataInvalidos.get(1).numeroLinhasOk++;
        }
        dataCidades.add(new Cidade(linhaDividida[0], linhaDividida[1], linhaDividida[2], Float.parseFloat(linhaDividida[3]), linhaDividida[4], linhaDividida[5], erro));
    }

    private static void newPais(String[] linhaDividida, boolean erro, int linhaCount) {

        for (int i = 0; i < linhaDividida.length; i++) {
            if (linhaDividida[i].isEmpty() && dataInvalidos.get(0).primeiraLinhaNaoOK == -1) {
                dataInvalidos.get(0).numeroLinhasNaoOk++;
                dataInvalidos.get(0).primeiraLinhaNaoOK = linhaCount;
                return;
            } else if (linhaDividida[i].isEmpty()) {
                dataInvalidos.get(0).numeroLinhasNaoOk++;
                return;
            }
        }

        if (linhaDividida.length != 4) {
            dataInvalidos.get(0).numeroLinhasNaoOk++;
            if (dataInvalidos.get(0).primeiraLinhaNaoOK == -1) {
                dataInvalidos.get(0).primeiraLinhaNaoOK = linhaCount;
            }
            return;
        }
        try {
            Integer.parseInt(linhaDividida[0]);
        } catch (NumberFormatException e) {
            erro = true;
        }
        if (dataInvalidos.get(0).primeiraLinhaNaoOK == -1 && erro) {
            dataInvalidos.get(0).primeiraLinhaNaoOK = linhaCount;
            dataInvalidos.get(0).numeroLinhasNaoOk++;
        }
        if (erro || idEncontradoEmPaises(Integer.parseInt(linhaDividida[0]))) {
            dataInvalidos.get(0).numeroLinhasNaoOk++;
            if (dataInvalidos.get(0).primeiraLinhaNaoOK == -1) {
                dataInvalidos.get(0).primeiraLinhaNaoOK = linhaCount;
            }
            return;
        } else {
            dataInvalidos.get(0).numeroLinhasOk++;
        }
        dataPaises.add(new Pais(Integer.parseInt(linhaDividida[0]), linhaDividida[1], linhaDividida[2], linhaDividida[3], erro, 0, linhaCount));
        return;
    }

    static void resetEverything() {
        dataPaises.clear();
        dataCidades.clear();
        dataPopulacao.clear();
        dataInvalidos.clear();
        citiesSortedByPopulation.clear();
        countriesByAlfa2.clear();
        countriesById.clear();
        countriesByName.clear();

        dataInvalidos.add(new TipoInvalido("paises.csv", 0, 0, -1)); // dataInvalidos.get(0)
        dataInvalidos.add(new TipoInvalido("cidades.csv", 0, 0, -1)); // dataInvalidos.get(1)
        dataInvalidos.add(new TipoInvalido("populacao.csv", 0, 0, -1)); // dataInvalidos.get(2)
    }

    /**
     * @param pasta Pasta que contém os ficheiros .csv
     * @return True, caso tenha conseguido ler todos os ficheiros corretamente, ou false, caso não tenha.
     */
    static boolean parseFiles(File pasta) {
        resetEverything();
        if (!parseEachFile(pasta + "/paises.csv", 0)) {
            return false;
        }
        if (!parseEachFile(pasta + "/cidades.csv", 1)) {
            return false;
        }

        Iterator<Pais> iterator = dataPaises.iterator();
        while (iterator.hasNext()) {
            Pais dataPais = iterator.next();
            boolean foundCity = false;
            for (Cidade dataCidade : dataCidades) {
                if (Objects.equals(dataCidade.alfa2, dataPais.alfa2)) {
                    foundCity = true;
                    break;
                }
            }
            int linha = dataPais.nrLinha;
            if (!foundCity) {
                iterator.remove();

                dataInvalidos.get(0).numeroLinhasNaoOk += 1;
                dataInvalidos.get(0).numeroLinhasOk -= 1;
                if (dataInvalidos.get(0).primeiraLinhaNaoOK == -1 || linha < dataInvalidos.get(0).primeiraLinhaNaoOK) {
                    dataInvalidos.get(0).primeiraLinhaNaoOK = linha;
                }
            } else {
                countriesByAlfa2.put(dataPais.alfa2, dataPais);
                countriesById.put(dataPais.id, dataPais);
                countriesByName.put(dataPais.nome, dataPais);
            }
        }

        for (Cidade dataCidade : dataCidades) {
            citiesSortedByPopulation.add(new Cidade(dataCidade.alfa2, dataCidade.cidade, dataCidade.regiao, dataCidade.populacao, dataCidade.latitude, dataCidade.longitude, false));
            countriesByAlfa2.get(dataCidade.alfa2).cidades.add(dataCidade);
        }

        if (!parseEachFile(pasta + "/populacao.csv", 2)) {
            return false;
        }
        return true;

    }

    static ArrayList<String> getObjects(TipoEntidade tipo) {
        ArrayList<String> novaInformacao = new ArrayList<>();

        if (tipo == TipoEntidade.INPUT_INVALIDO) {
            novaInformacao.add("paises.csv | " + dataInvalidos.get(0).numeroLinhasOk + " | " + dataInvalidos.get(0).numeroLinhasNaoOk + " | " + dataInvalidos.get(0).primeiraLinhaNaoOK);
            novaInformacao.add("cidades.csv | " + dataInvalidos.get(1).numeroLinhasOk + " | " + dataInvalidos.get(1).numeroLinhasNaoOk + " | " + dataInvalidos.get(1).primeiraLinhaNaoOK);
            novaInformacao.add("populacao.csv | " + dataInvalidos.get(2).numeroLinhasOk + " | " + dataInvalidos.get(2).numeroLinhasNaoOk + " | " + dataInvalidos.get(2).primeiraLinhaNaoOK);

        }

        if (tipo == TipoEntidade.PAIS) {
            for (Pais dataPais : dataPaises) {
                dataPais.indicadoresEstatisticos = 0;
                if (dataPais.id > 700) {
                    for (Populacao dataPop : dataPopulacao) {
                        if (dataPop.id == dataPais.id) {
                            dataPais.indicadoresEstatisticos++;
                        }
                    }
                }
                if (!dataPais.linhaInvalida) {
                    novaInformacao.add(dataPais.toString());
                }
            }
        }

        if (tipo == TipoEntidade.CIDADE) {
            for (Cidade dataCidade : dataCidades) {
                //   if (!dataCidade.linhaInvalida) {
                novaInformacao.add(dataCidade.toString());
                //   }
            }
        }
        return novaInformacao;
    }

    public static Result execute(String comando) {

        String[] comandoComArgs = comando.split(" ");

        switch (comandoComArgs[0]) {
            case "HELP" -> {
                return new Result(true, null, ExecutionFunctions.helpCommand());
            }

            case "GET_CITIES_BY_COUNTRY" -> {
                return new Result(true, null, ExecutionFunctions.getCitiesByCountry(comandoComArgs));
            }

            case "SUM_POPULATIONS" -> {
                String resultado = ExecutionFunctions.sumPopulations(comandoComArgs);
                return new Result(true, null, resultado);
            }

            case "COUNT_CITIES" -> {
                return new Result(true, null, ExecutionFunctions.countCities(comandoComArgs));
            }
            case "GET_COUNTRIES_GENDER_GAP" -> {
                String resultado = ExecutionFunctions.getCountriesGenderGap(comandoComArgs);
                return new Result(true, null, resultado);
            }

            case "GET_TOP_POPULATION_INCREASE" -> {
                String resultado = ExecutionFunctions.getTopPopulationIncrease(comandoComArgs);
                return new Result(true, null, resultado);
            }
            case "INSERT_CITY" -> {
                String resultado = ExecutionFunctions.insertCity(comandoComArgs);
                return new Result(true, null, resultado);
            }
            case "REMOVE_COUNTRY" -> {
                String resultado = ExecutionFunctions.removeCountry(comandoComArgs);
                return new Result(true, null, resultado);
            }
            case "GET_DUPLICATE_CITIES_DIFFERENT_COUNTRIES" -> {
                String resultado = ExecutionFunctions.getDuplicatesDifferentCountries(comandoComArgs);
                return new Result(true, null, resultado);
            }
            case "GET_CITIES_AT_DISTANCE" -> {
                String resultado = ExecutionFunctions.getCitiesAtDistance(comandoComArgs);
                return new Result(true, null, resultado);
            }
            default -> {
                return new Result(false, "Comando invalido", null);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to DEISI World Meter");

        long start = System.currentTimeMillis();
        boolean parseOk = parseFiles(new File("Data"));
        if (!parseOk) {
            System.out.println("Error loading files");
            return;
        }
        long end = System.currentTimeMillis();

        System.out.println("Loaded files in " + (end - start) + " ms");

        Result result = execute("HELP");
        System.out.println(result.result);

        Scanner in = new Scanner(System.in);

        String line;
        do {
            System.out.print("> ");
            line = in.nextLine();

            if (line != null && !line.equals("QUIT")) {
                start = System.currentTimeMillis();
                result = execute(line);
                end = System.currentTimeMillis();

                if (!result.success) {
                    System.out.println("Error: " + result.error);
                } else {
                    System.out.println(result.result);
                    System.out.println("(took " + (end - start) + " ms)");
                }
            }
        } while (line != null && !line.equals("QUIT"));
    }
}
