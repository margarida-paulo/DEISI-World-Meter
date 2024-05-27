package pt.ulusofona.aed.deisiworldmeter;

import static pt.ulusofona.aed.deisiworldmeter.Main.citiesSortedByPopulation;

public class ExecutionFunctions {

    public static String helpCommand(){
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

    public static String countCities(String[] comando){
        int min_populacao = Integer.parseInt(comando[1]);
        int qtyCities = 0;
        for (int i = 0; i < citiesSortedByPopulation.size() && citiesSortedByPopulation.get(i).populacao >= min_populacao ; i++){
            qtyCities++;
        }
        return Integer.toString(qtyCities);
    }

    public static String getCitiesByCountry(String[] comando){
        return null;
    }

}
