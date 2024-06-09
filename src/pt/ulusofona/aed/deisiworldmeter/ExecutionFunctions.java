package pt.ulusofona.aed.deisiworldmeter;

import java.util.*;

import static pt.ulusofona.aed.deisiworldmeter.Main.*;

public class ExecutionFunctions {
    public static String helpCommand(){
        String help = "\n-------------------------\n";
        help += "Commands available:\n";
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
        help += "-------------------------\n";
        return help;
    }

    public static String countCities(String[] comando) { ///Mudei para um for loop -> sem erros
        if (comando.length != 2) {
            return "Número errado de argumentos!\n";
        }
        int min_population = Integer.parseInt(comando[1]);
        int qtyCities = 0;

        for (Cidade citiesPopulation : dataCidades) {
            if (citiesPopulation.populacao >= min_population) {
                qtyCities++;
            }
        }
        return Integer.toString(qtyCities);
    }

    public static String getCitiesByCountry(String[] comando){ /// comando -> sem erros

        int numResults = Integer.parseInt(comando[1]);
        StringBuilder countryName = new StringBuilder();
        for (int i = 2; i < comando.length; i++){
            countryName.append(comando[i]);
            if (i != comando.length - 1){
                countryName.append(" ");
            }
        }


        int citiesFoundedByCountry = 0;
        String alfa2Country = null;
        StringBuilder citiesList = new StringBuilder();

        for (Pais dataPaise : dataPaises) {
            if (countryName.toString().equals(dataPaise.nome)) {
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

    public static String sumPopulations(String[] comando) { ///comando -> sem erros

        int i = 1;
        StringBuilder argumentos = new StringBuilder();

        while (i < comando.length) {
            argumentos.append(comando[i]);
            if (i != comando.length - 1){
                argumentos.append(" ");
            }
            i++;
        }


        String[] multipleCountries = argumentos.toString().split(",");
        HashMap<String, Integer> countryMap = new HashMap<>(); /// criei um mapa que nos ajuda com os IDs através dos nomes dos paises
        int soma = 0;

        for (Pais dataPais : dataPaises) {
            countryMap.put(dataPais.nome, dataPais.id);
        }

        for (String countryName : multipleCountries) {
            Integer idCountry = countryMap.get(countryName);

            if (idCountry == null) {
                return "Pais invalido: " + countryName;
            }

            for (Populacao populacao : dataPopulacao) {
                if (idCountry == populacao.id && populacao.ano == 2024) {
                    soma += populacao.populacaoMasculina + populacao.populacaoFeminina;
                    break;
                }
            }
        }
        return String.valueOf(soma);
    }

    public static String getHistory(String[] comando) {

        int startYear = Integer.parseInt(comando[1]);
        int endYear = Integer.parseInt(comando[2]);
        StringBuilder countryName = new StringBuilder();

        for (int i = 3; i < comando.length; i++){
            countryName.append(comando[i]);
            if (i != comando.length - 1){
                countryName.append(" ");
            }
        }

        StringBuilder informationList = new StringBuilder();
        Pais pais = countriesByName.get(countryName.toString());
        if (pais == null) {
            return "Pais invalido: " + countryName;
        }

        while (startYear <= endYear) {
            Populacao populacaoStartYear = pais.dadosPopulacao.get(startYear);
            if (populacaoStartYear != null) {
                informationList.append(startYear).append(":").append(populacaoStartYear.populacaoMasculina / 1000).append("k").append(":").append(populacaoStartYear.populacaoFeminina / 1000).append("k");
                informationList.append("\n");
            }
            startYear++;
        }
        if (informationList.isEmpty()){
            return "Sem resultados";
        }
        return informationList.toString();
    }

    public static String getMissingHistory(String[] comando){
        if (comando.length != 3) {
            return "Número errado de argumentos!\n";
        }
        StringBuilder missingHistory = new StringBuilder();
        int startYear = Integer.parseInt(comando[1]);
        int endYear = Integer.parseInt(comando[2]);

        for (Pais pais : countriesById.values()) {
            for (int i = startYear; i <= endYear; i++) {
                if (pais.dadosPopulacao.get(i) == null) {
                    missingHistory.append(pais.alfa2).append(":").append(pais.nome).append("\n");
                    break;
                }
            }
        }
        if (missingHistory.isEmpty()){
            return "Sem resultados";
        }
        return missingHistory.toString();
    }

    public static String getMostPopulous(String[] comando){
        if (comando.length != 2) {
            return "Número errado de argumentos!\n";
        }
        int numResults = Integer.parseInt(comando[1]);
        HashMap<String, String> paisesJaUsados = new HashMap<>();
        StringBuilder mostPopulous = new StringBuilder();
        Iterator<Cidade> iterator = citiesSortedByPopulation.iterator();
        int i = 0;

        while (iterator.hasNext() && i < numResults){
            Cidade city = iterator.next();
            String nomePais = countriesByAlfa2.get(city.alfa2).nome;
            if (paisesJaUsados.get(nomePais) == null){
                paisesJaUsados.put(nomePais, nomePais);
                mostPopulous.append(nomePais).append(":").append(city.cidade).append(":").append((int) city.populacao).append("\n");
                i++;
            }
        }
        if (mostPopulous.isEmpty()){
            return "Sem resultados";
        }
        return mostPopulous.toString();
    }



    public static String getTopCitiesByCountry(String[] comando){
        StringBuilder topCities = new StringBuilder();
        StringBuilder nomePais = new StringBuilder();

        for (int i = 2; i < comando.length; i++){
            nomePais.append(comando[i]);
            if (i != comando.length - 1){
                nomePais.append(" ");
            }
        }
        int numResults = Integer.parseInt(comando[1]);
        Pais pais = countriesByName.get(nomePais.toString());

        if (pais == null) {
            return "Pais invalido";
        }
        ArrayList<Cidade> cidades = new ArrayList<>(countriesByAlfa2.get(pais.alfa2).cidades);
        cidades.sort(Comparator.comparingInt((Cidade c) -> c.populacaoMilhares).reversed().thenComparing((Cidade c) -> c.cidade));
        if (numResults == -1){
            numResults = cidades.size();
        }
        for (int i = 0; i < numResults && i < cidades.size() && cidades.get(i).populacaoMilhares >= 10; i++){
            topCities.append(cidades.get(i).cidade).append(":").append(cidades.get(i).populacaoMilhares).append("K\n");
        }
        if (topCities.isEmpty()){
            return "Sem resultados";
        }
        return topCities.toString();
    }

    public static String getDuplicates(String[] comando){
        if (comando.length!= 2) {
            return "Número errado de argumentos!\n";
        }
        int min_populacao = Integer.parseInt(comando[1]);
        StringBuilder duplicados = new StringBuilder();
        HashMap<String, Cidade> cidadesRepetidas = new HashMap<>();

        for(Cidade cidade : dataCidades){
            if (cidade.populacao < min_populacao){
                continue;
            }
            if (cidadesRepetidas.get(cidade.cidade) == null) {
                cidadesRepetidas.put(cidade.cidade, cidade);
            } else {
                duplicados.append(cidade.cidade).append(" (").append(countriesByAlfa2.get(cidade.alfa2).nome).append(",").append(cidade.regiao).append(")\n");
            }
        }
        if (duplicados.isEmpty()) {
            return "Sem resultados";
        } else {
            return duplicados.toString();
        }
    }

    public static String getCountriesGenderGap(String[] comando) { ///comando -> sem erros
        if (comando.length != 2) {
            return "Número errado de argumentos!\n";
        }
        StringBuilder paises = new StringBuilder();
        int min_gender_gap = Integer.parseInt(comando[1]);
        for (Populacao populacao : dataPopulacao) {
            if (populacao.ano == 2024) {
                float gender_gap_2024 = ((Math.abs(populacao.populacaoMasculina - populacao.populacaoFeminina) * (float) 100)) / (populacao.populacaoMasculina + populacao.populacaoFeminina);
                if (gender_gap_2024 >= min_gender_gap) {
                    paises.append(countriesById.get(populacao.id).nome).append(":").append(String.format("%.2f", gender_gap_2024)).append("\n");
                }
            }
        }
        if (paises.isEmpty()) {
            return "Sem resultados";
        } else {
            return paises.toString();
        }
    }

    public static String getTopPopulationIncrease(String[] comando) {
        StringBuilder finalString = new StringBuilder();
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
            finalString.append(difPop.get(i).nome).append(":").append(difPop.get(i).anoInicial).append("-").append(difPop.get(i).anoFinal).append(":").append(String.format("%.2f", difPop.get(i).diferencaPopulacao)).append("%\n");
        }
        if (finalString.isEmpty()) {
            return "Sem resultados";
        } else {
            return finalString.toString();
        }
    }

    public static String insertCity(String[] comando){
//        Main.prepDistances();
        if (comando.length != 5) {
            return "Número errado de argumentos!\n";
        }
        String alfa2 = comando[1];
        String nome = comando[2];
        String regiao = comando[3];
        try {
            Integer.parseInt(comando[4]);
        } catch (NumberFormatException e) {
            return "População inválida!\n";
        }

        if (countriesByAlfa2.get(alfa2) == null)
        {
            return "Pais invalido";
        }

        dataCidades.add(new Cidade(alfa2, nome, regiao, Float.parseFloat(comando[4]), "0.0", "0.0", false));
        citiesSortedByPopulation.add(new Cidade(alfa2, nome, regiao, Float.parseFloat(comando[4]), "0.0", "0.0", false));
        countriesByAlfa2.get(alfa2).cidades.add(new Cidade(alfa2, nome, regiao, Float.parseFloat(comando[4]), "0.0", "0.0", false));

        return "Inserido com sucesso";
    }

    public static String removeCountry(String[] comando){
        if (comando.length != 2) {
            return "Número errado de argumentos!\n";
        }
//        Main.prepDistances();
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
        citiesByLatitudeLongitude.values().removeIf(dataCidade -> dataCidade.alfa2.equals(alfa2));
        return "Removido com sucesso";
    }

    public static String getDuplicatesDifferentCountries(String[] comando){
        if (comando.length != 2) {
            return "Número errado de argumentos!\n";
        }
        int min_populacao = Integer.parseInt(comando[1]);
        StringBuilder duplicados = new StringBuilder();
        HashMap<String, ArrayList<String>> cidadesRepetidas = new HashMap<>();
        for (Cidade city: dataCidades){
            if(city.populacao < min_populacao){
                continue;
            }
            String nomePais = countriesByAlfa2.get(city.alfa2).nome;
            if (cidadesRepetidas.get(city.cidade) == null){
                cidadesRepetidas.put(city.cidade, new ArrayList<>());
                cidadesRepetidas.get(city.cidade).add(nomePais);
            } else {
                if (!(cidadesRepetidas.get(city.cidade).contains(nomePais))){
                    cidadesRepetidas.get(city.cidade).add(nomePais);
                }
            }
        }

        for (Map.Entry<String, ArrayList<String>> entry : cidadesRepetidas.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();

            if(value.size() > 1){
                value.sort(null);
                duplicados.append(key).append(": ");
                for (int i = 0; i < value.size(); i++){
                    duplicados.append(value.get(i));
                    if (i != value.size() - 1){
                        duplicados.append(",");
                    }
                    else {
                        duplicados.append("\n");
                    }
                }
            }
        }

        if (duplicados.isEmpty()) {
            return "Sem resultados";
        } else {
            return duplicados.toString();
        }
    }

    public static double haversineFormula(double startLat, double startLong, double endLat, double endLong) {

        double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return 6371 * c; // <-- distance in kilometers
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

    public static String getCitiesAtDistance(String[] comando){
        int distancia = Integer.parseInt(comando[1]);

        StringBuilder nomePais = new StringBuilder();
        int i = 2;
        while (i < comando.length) {
            nomePais.append(comando[i]);
            if (i != comando.length - 1){
                nomePais.append(" ");
            }
            i++;
        }
        ArrayList<String> cidadesDistancia = new ArrayList<>();
        Pais pais = countriesByName.get(nomePais.toString());
        if (pais == null){
            return ("Pais invalido");
        }
        StringBuilder finalString = new StringBuilder();
        for (i = 0; i < pais.cidades.size() - 1; i++){
            for (int a = i + 1; a < pais.cidades.size(); a++){
                Cidade cidade1 = pais.cidades.get(i);
                Cidade cidade2 = pais.cidades.get(a);
                double haversineDistance = haversineFormula(Double.parseDouble(cidade1.latitude), Double.parseDouble(cidade1.longitude), Double.parseDouble(cidade2.latitude), Double.parseDouble(cidade2.longitude));
                if (haversineDistance > distancia - 1 && haversineDistance < distancia + 1){
                    if(cidade1.cidade.compareTo(cidade2.cidade) < 0){
                        cidadesDistancia.add(cidade1.cidade + "->" + cidade2.cidade);
                    } else {
                        cidadesDistancia.add(cidade2.cidade + "->" + cidade1.cidade);
                    }
                }
            }
        }
        if (cidadesDistancia.isEmpty()){
            return "Sem resultados";
        }
        cidadesDistancia.sort(null);
        for (String cidadesQueCabem : cidadesDistancia){
            finalString.append(cidadesQueCabem).append("\n");
        }
        return finalString.toString();
    }


    // Returns an float[] with min latitude, max latitude, min longitude, max longitude
    public static float[] calculateLatLongRanges(float latitude, float longitude, int distance){
        float[] ranges = new float[4];
        int i;
        for(i = 0;  haversineFormula(latitude, longitude, latitude + i, longitude) < distance *20; i++){
        }
        ranges[0] = latitude - i;
        ranges[1] = latitude + i;
        for(i = 0;  haversineFormula(latitude, longitude, latitude, longitude + i) < distance *20; i++) {
        }
        ranges[2] = longitude - i;
        ranges[3] = longitude + i;
        return ranges;

    }

    public static String getCitiesAtDistance2(String[] comando){
        int distancia = Integer.parseInt(comando[1]);
        StringBuilder nomePaisOrigem = new StringBuilder();
        int i = 2;
        while (i < comando.length) {
            nomePaisOrigem.append(comando[i]);
            if (i != comando.length - 1){
                nomePaisOrigem.append(" ");
            }
            i++;
        }

        if (distancesCache.get(nomePaisOrigem.toString() + distancia) != null){
            return distancesCache.get(nomePaisOrigem.toString() + distancia);
        }

        ArrayList<String> cidadesDistancia = new ArrayList<>();
        Pais paisOrigem = countriesByName.get(nomePaisOrigem.toString());
        if (paisOrigem == null){
            return ("Pais invalido");
        }
        StringBuilder finalString = new StringBuilder();
        for (Cidade cidadeOrigem : paisOrigem.cidades) {
            float lat = Float.parseFloat(cidadeOrigem.latitude);
            float longi = Float.parseFloat(cidadeOrigem.longitude);
            float[] ranges = calculateLatLongRanges(lat, longi, distancia);
            float minLatitude = ranges[0];
            float maxLatitude = ranges[1];
            float minLongitude = ranges[2];
            float maxLongitude = ranges[3];
            SortedSet<Float> latitudesRange = latitudesOrdered.subSet(minLatitude, maxLatitude);
            for (float latitude : latitudesRange){
                SortedSet<Float> longitudesRange = latitudesLongitudes.get(latitude).subSet(minLongitude, maxLongitude);
                for (float longitude : longitudesRange){
                    Cidade cidadeDestino = citiesByLatitudeLongitude.get(latitude + "," + longitude);
                    if (cidadeDestino != null && !cidadeDestino.alfa2.equals(cidadeOrigem.alfa2)){
                        double haversineDistance = haversineFormula(lat, longi, Float.parseFloat(cidadeDestino.latitude),Float.parseFloat(cidadeDestino.longitude));
                        if (haversineDistance > distancia - 1 && haversineDistance < distancia + 1){
                            if (cidadeOrigem.cidade.compareTo(cidadeDestino.cidade) < 0){
                                cidadesDistancia.add(cidadeOrigem.cidade + "->" + cidadeDestino.cidade);
                            } else {
                                cidadesDistancia.add(cidadeDestino.cidade + "->" + cidadeOrigem.cidade);
                            }
                        }
                    }
                }
            }
        }
        if (cidadesDistancia.isEmpty()){
            return "Sem resultados";
        }
        cidadesDistancia.sort(null);
        for (String cidadesQueCabem : cidadesDistancia){
            finalString.append(cidadesQueCabem).append("\n");
        }
        distancesCache.put(nomePaisOrigem.toString() + distancia, finalString.toString());
        return finalString.toString();
    }

    public static String getCitiesByRegion(String[] comando) {

        String region = comando[1];
        StringBuilder countryName = new StringBuilder();

        for (int i = 2; i < comando.length; i++) {
            countryName.append(comando[i]);
            if (i != comando.length - 1) {
                countryName.append(" ");
            }
        }

        StringBuilder informationList = new StringBuilder();
        Pais pais = countriesByName.get(countryName.toString());

        if (pais == null) {
            return "Pais invalido";
        }

        ArrayList<Cidade> cidades = new ArrayList<>(countriesByAlfa2.get(pais.alfa2).cidades);
        cidades.sort(Comparator.comparing((Cidade c) -> c.cidade));

        for (Cidade regionInfo : cidades) {
            if (region.equals(regionInfo.regiao)) {
                informationList.append(countryName).append(" -> ").append(regionInfo.cidade).append("\n");
            }
        }
        if (informationList.isEmpty()){
            return "Sem resultados";
        }
        return informationList.toString();
    }
}
