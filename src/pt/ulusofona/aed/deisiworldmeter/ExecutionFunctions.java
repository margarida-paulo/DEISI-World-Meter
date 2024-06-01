package pt.ulusofona.aed.deisiworldmeter;

import java.util.ArrayList;
import java.util.Iterator;

import static pt.ulusofona.aed.deisiworldmeter.Main.*;

public class ExecutionFunctions {

    public static String helpCommand() {
        String help = "";
        help += "COUNT_CITIES <min_population>\n";
        help += "GET_CITIES_BY_COUNTRY <num-results> <country-name>\n";
        help += "SUM_POPULATIONS <countries-list>\n";
        help += "GET_HISTORY <year-start> <year-end> <country-name>\n";
        help += "GET_MISSING_HISTORY <year-start> <year-end>\n";
        help += "GET_MOST_POPULOUS <num-results>\n";
        help += "GET_TOP_CITIES_BY_COUNTRY <num-results> <country-name>\n";
        help += "GET_DUPLICATE_CITIES <min_population>\n";
        help += "GET_COUNTRIES_GENDER_GAP <min-gender-gap>\n";
        help += "GET_TOP_POPULATION_INCREASE <year-start> <year-end>\n";
        help += "GET_DUPLICATE_CITIES_DIFFERENT_COUNTRIES <min-population>\n";
        help += "GET_CITIES_AT_DISTANCE <distance> <country-name>\n";
        help += "INSERT_CITY <alfa2> <city-name> <region> <population>\n";
        help += "REMOVE_COUNTRY <country-name>\n";
        help += "HELP\n";
        help += "QUIT\n";
        return help;
    }

    public static String countCities(String[] comando) {
        if (comando.length != 2) {
            return "Número errado de argumentos!\n";
        }
        int min_populacao = Integer.parseInt(comando[1]);
        int qtyCities = 0;

        Iterator<Cidade> iterator = citiesSortedByPopulation.iterator();
        while (iterator.hasNext() && iterator.next().populacao >= min_populacao) {
            qtyCities++;
        }

        return Integer.toString(qtyCities);
    }

    public static String getCitiesByCountry(String[] comando){

        int numResults = Integer.parseInt(comando[1]);
        String countryName = comando[2];
        int citiesFoundedByCountry = 0;
        String alfa2Country = null;
        StringBuilder citiesList = new StringBuilder();

        for (Pais dataPaise : dataPaises) {
            if (countryName.equals(dataPaise.nome)) {
                alfa2Country = dataPaise.alfa2;
                break;
            }
        }

        if (alfa2Country == null) {
            return "Sem resultados";
        }

        for (int i = 0; citiesFoundedByCountry < numResults && i < dataCidades.size(); i++) {
            if (alfa2Country.equals(dataCidades.get(i).alfa2)) {
                citiesList.append(dataCidades.get(i).cidade);
                citiesList.append("\n");
                citiesFoundedByCountry++;
            }
        }
        if (citiesList.isEmpty()){
            return "Sem resultados";
        }
        return citiesList.toString();
    }
    public static String getCountriesGenderGap(String[] comando) {
        if (comando.length != 2) {
            return "Número errado de argumentos!\n";
        }
        String paises = "";
        int min_gender_gap = Integer.parseInt(comando[1]);
        for (Populacao populacao : dataPopulacao) {
            if (populacao.ano == 2024) {
                float gender_gap_2024 = ((Math.abs(populacao.populacaoMasculina - populacao.populacaoFeminina) * (float) 100)) / (populacao.populacaoMasculina + populacao.populacaoFeminina);
                if (gender_gap_2024 >= min_gender_gap) {
                    paises += countriesById.get(populacao.id).nome + ":" + String.format("%.2f", gender_gap_2024) + "\n";
                }
            }
        }
        if (paises.isEmpty()) {
            return "Sem resultados";
        } else {
            return paises;
        }
    }

    public static String getTopPopulationIncrease(String[] comando) {
        String finalString = "";
        if (comando.length != 3) {
            return "Número errado de argumentos!\n";
        }

        int year_start = Integer.parseInt(comando[1]);
        int year_end = Integer.parseInt(comando[2]);
        ArrayList<DiferencasPopulacao> difPop = new ArrayList<>();
        countriesById.forEach((id, pais) -> {
            for (int anoInicio = year_start; anoInicio < year_end; anoInicio++) {
                for (int anoFim = anoInicio + 1; anoFim <= year_end; anoFim++) {
                    Populacao populacaoAnoInicial = pais.dadosPopulacao.get(anoInicio);
                    Populacao populacaoAnoFinal = pais.dadosPopulacao.get(anoFim);

                    if (populacaoAnoInicial == null || populacaoAnoFinal == null) {
                        continue;
                    }
                    int popTotalAnoInicial = populacaoAnoInicial.populacaoMasculina + populacaoAnoInicial.populacaoFeminina;
                    int popTotalAnoFinal = populacaoAnoFinal.populacaoMasculina + populacaoAnoFinal.populacaoFeminina;
                    difPop.add(new DiferencasPopulacao(pais.nome, anoInicio, anoFim, (((float) popTotalAnoFinal - (float) popTotalAnoInicial) / (float) popTotalAnoFinal) * (float) 100));
                }
            }
        });
        difPop.sort((d1, d2) -> Float.compare(d2.diferencaPopulacao, d1.diferencaPopulacao));
        for (int i = 0; i < difPop.size() && i < 5; i++) {
            if (difPop.get(i).diferencaPopulacao < 0) {
                break;
            }
            finalString += difPop.get(i).nome + ":" + difPop.get(i).anoInicial + "-" + difPop.get(i).anoFinal + ":" + String.format("%.2f", difPop.get(i).diferencaPopulacao) + "%\n";
        }
        if (finalString.isEmpty()) {
            return "Sem resultados";
        } else {
            return finalString;
        }
    }

    public static String insertCity(String[] comando){
        if (comando.length != 5) {
            return "Número errado de argumentos!\n";
        }
        String alfa2 = comando[1];
        String nome = comando[2];
        String regiao = comando[3];
        try {
            int populacao = Integer.parseInt(comando[4]);
        } catch (NumberFormatException e) {
            return "População inválida!\n";
        }

        if (countriesByAlfa2.get(alfa2) == null)
        {
            return "Pais invalido";
        }

        dataCidades.add(new Cidade(alfa2, nome, regiao, Float.parseFloat(comando[4]), "0.0", "0.0", false));
        citiesSortedByPopulation.add(new Cidade(alfa2, nome, regiao, Float.parseFloat(comando[4]), "0.0", "0.0", false));
        return "Inserido com sucesso";
    }

    public static String removeCountry(String[] comando){
        if (comando.length != 2) {
            return "Número errado de argumentos!\n";
        }
        if (countriesByName.get(comando[1]) == null)
        {
            return "Pais invalido";
        }
        int id = countriesByName.get(comando[1]).id;
        String alfa2 = countriesByName.get(comando[1]).alfa2;
        countriesByName.remove(comando[1]);
        countriesById.remove(id);
        countriesByAlfa2.remove(alfa2);
        Iterator<Pais> iterator = dataPaises.iterator();
        while (iterator.hasNext()) {
            Pais dataPais = iterator.next();
            if (dataPais.id == id) {
                iterator.remove();
                break;
            }
        }
        dataCidades.removeIf(dataCidade -> dataCidade.alfa2.equals(alfa2));
        citiesSortedByPopulation.removeIf(dataCidade -> dataCidade.alfa2.equals(alfa2));
        return "Removido com sucesso";
    }

    public static String getDuplicatesDifferentCountries(String[] comando){
        if (comando.length != 2) {
            return "Número errado de argumentos!\n";
        }
        int min_populacao = Integer.parseInt(comando[1]);
        String duplicados = "";




        if (duplicados.isEmpty()) {
            return "Sem resultados";
        } else {
            return duplicados;
        }
    }

}
