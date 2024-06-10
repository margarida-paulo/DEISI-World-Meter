package pt.ulusofona.aed.deisiworldmeter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static pt.ulusofona.aed.deisiworldmeter.TipoEntidade.CIDADE;
import static pt.ulusofona.aed.deisiworldmeter.TipoEntidade.PAIS;
import static pt.ulusofona.aed.deisiworldmeter.TipoEntidade.INPUT_INVALIDO;


public class TestMain {

    @Test
    public void testCountCities() {
        assertTrue(Main.parseFiles(new File("test-files/countCities")));
        Result result = Main.execute("COUNT_CITIES 100");
        assertNotNull(result);
        assertTrue(result.success);
        Assertions.assertEquals("12", result.result);

        result = Main.execute("COUNT_CITIES 50000");
        assertNotNull(result);
        assertTrue(result.success);
        Assertions.assertEquals("0", result.result);

        result = Main.execute("COUNT_CITIES 15000");
        assertNotNull(result);
        assertTrue(result.success);
        Assertions.assertEquals("2", result.result);

        result = Main.execute("COUNT_CITIES 10000");
        assertNotNull(result);
        assertTrue(result.success);
        Assertions.assertEquals("8", result.result);
    }

    @Test
    public void testGetCitiesByCountry() {
        assertTrue(Main.parseFiles(new File("test-files/general")));
        Result result = Main.execute("GET_CITIES_BY_COUNTRY 1 Portugal");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "porto"
        }, resultParts);


        assertTrue(Main.parseFiles(new File("test-files/general")));
        result = Main.execute("GET_CITIES_BY_COUNTRY 10 Espanha");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "madrid"
        }, resultParts);


        assertTrue(Main.parseFiles(new File("test-files/general")));
        result = Main.execute("GET_CITIES_BY_COUNTRY 5 Alemanha");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "berlim",
                "frankfurt"
        }, resultParts);


        assertTrue(Main.parseFiles(new File("test-files/general")));
        result = Main.execute("GET_CITIES_BY_COUNTRY 1 Alemanha");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "berlim"
        }, resultParts);
    }


    @Test
    public void testSumPopulations() {
        assertTrue(Main.parseFiles(new File("test-files/general")));
        Result result = Main.execute("SUM_POPULATIONS Portugal,Espanha");
        assertNotNull(result);
        assertTrue(result.success);
        Assertions.assertEquals("2000000", result.result);

        result = Main.execute("SUM_POPULATIONS Portugal,Alemanha");
        assertNotNull(result);
        assertTrue(result.success);
        Assertions.assertEquals("1000000", result.result);

        result = Main.execute("SUM_POPULATIONS Portugal,Espanha,Alemanha");
        assertNotNull(result);
        assertTrue(result.success);
        Assertions.assertEquals("2000000", result.result);

        result = Main.execute("SUM_POPULATIONS Portugal,Bla,Alemanha");
        assertNotNull(result);
        assertTrue(result.success);
        Assertions.assertEquals("Pais invalido: Bla", result.result);
    }

    @Test
    public void testGetHistory() {
        assertTrue(Main.parseFiles(new File("test-files/general")));
        Result result = Main.execute("GET_HISTORY 2023 2024 Alemanha");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "2023:200k:800k"
        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/general")));
        result = Main.execute("GET_HISTORY 2023 2025 Alemanha");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "2023:200k:800k"
        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/general")));
        result = Main.execute("GET_HISTORY 2024 2024 Portugal");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "2024:500k:500k"
        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/general")));
        result = Main.execute("GET_HISTORY 2020 2030 Portugal");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "2023:50k:100k",
                "2024:500k:500k"
        }, resultParts);

    }


    @Test
    public void testGetMissingHistory() {
        assertTrue(Main.parseFiles(new File("test-files/getMissingHistory")));
        Result result = Main.execute("GET_MISSING_HISTORY 2020 2030");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "pt:Portugal",
                "es:Espanha",
                "de:Alemanha"
        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/getMissingHistory")));
        result = Main.execute("GET_MISSING_HISTORY 2024 2030");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "pt:Portugal",
                "de:Alemanha"        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/getMissingHistory")));
        result = Main.execute("GET_MISSING_HISTORY 1900 2000");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "pt:Portugal",
                "es:Espanha",
                "de:Alemanha"
        }, resultParts);

    }


    @Test
    public void testGetMostPopulous(){
        assertTrue(Main.parseFiles(new File("test-files/getMostPopulous")));
        Result result = Main.execute("GET_MOST_POPULOUS 5");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "China:jilin:1881977",
                "Zimbábue:zvishavane:79876",
                "Portugal:santarem:29385",
                "Estados Unidos:manchester:19235",
                "Andorra:les escaldes:15854"
        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/getMostPopulous")));
        result = Main.execute("GET_MOST_POPULOUS 4");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "China:jilin:1881977",
                "Zimbábue:zvishavane:79876",
                "Portugal:santarem:29385",
                "Estados Unidos:manchester:19235"
        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/getMostPopulous")));
        result = Main.execute("GET_MOST_POPULOUS 2");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "China:jilin:1881977",
                "Zimbábue:zvishavane:79876"
        }, resultParts);

    }


    @Test
    public void testGetTopCitiesByCountry(){
        assertTrue(Main.parseFiles(new File("test-files/getTopCitiesByCountry")));
        Result result = Main.execute("GET_TOP_CITIES_BY_COUNTRY 5 Portugal");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "lisbon:517K",
                "porto:249K",
                "amadora:178K",
                "braga:121K",
                "setubal:117K"
        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/getTopCitiesByCountry")));
        result = Main.execute("GET_TOP_CITIES_BY_COUNTRY 5 Brasil");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "sao paulo:10021K",
                "rio de janeiro:6023K",
                "salvador:2711K",
                "fortaleza:2416K",
                "belo horizonte:2373K"
        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/getTopCitiesByCountry")));
        result = Main.execute("GET_TOP_CITIES_BY_COUNTRY 5 China");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "shanghai:14608K",
                "peking:7480K",
                "wuhan:4184K",
                "chongqing:3967K",
                "xian:3953K"
        }, resultParts);
    }


    @Test
    public void testGetDuplicateCities(){
        assertTrue(Main.parseFiles(new File("test-files/getDuplicateCities")));
        Result result = Main.execute("GET_DUPLICATE_CITIES 10000");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "binzhou (China,16)"
        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/getDuplicateCities")));
        result = Main.execute("GET_DUPLICATE_CITIES 100000");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "Sem resultados"
        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/getDuplicateCities")));
        result = Main.execute("GET_DUPLICATE_CITIES 20000");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "Sem resultados"
        }, resultParts);
    }


    @Test
    public void testGetCountriesGenderGap(){
        assertTrue(Main.parseFiles(new File("test-files/getCountriesGenderGap")));
        Result result = Main.execute("GET_COUNTRIES_GENDER_GAP 0");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "Portugal:5.57",
                "África do Sul:5.57",
                "Alemanha:1.26"
        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/getCountriesGenderGap")));
        result = Main.execute("GET_COUNTRIES_GENDER_GAP 6");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "Sem resultados"
        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/getCountriesGenderGap")));
        result = Main.execute("GET_COUNTRIES_GENDER_GAP 5");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "Portugal:5.57",
                "África do Sul:5.57"
        }, resultParts);
    }


    @Test
    public void testGetTopPopulationIncrease(){
        assertTrue(Main.parseFiles(new File("test-files/getTopPopulationIncrease")));
        Result result = Main.execute("GET_TOP_POPULATION_INCREASE 2020 2030");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "Portugal:2020-2022:25.45%",
                "África do Sul:2020-2024:22.69%",
                "África do Sul:2023-2024:22.69%",
                "Portugal:2020-2021:16.36%",
                "África do Sul:2021-2024:15.12%"
        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/getTopPopulationIncrease")));
        result = Main.execute("GET_TOP_POPULATION_INCREASE 2020 2000");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "Sem resultados"
        }, resultParts);
    }


    @Test
    public void testInsertCity(){
        assertTrue(Main.parseFiles(new File("test-files/insertCity")));
        Result initialNumberOfCities = Main.execute("COUNT_CITIES 0");
        Result result = Main.execute("INSERT_CITY pt lisboa 14 10000");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        Assertions.assertEquals(Integer.parseInt(initialNumberOfCities.result) + 1, Integer.parseInt(Main.execute("COUNT_CITIES 0").result));
        assertArrayEquals(new String[] {
                "Inserido com sucesso"
        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/insertCity")));
        initialNumberOfCities = Main.execute("COUNT_CITIES 0");
        result = Main.execute("INSERT_CITY bla cidadeImaginaria 0 12");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        Assertions.assertEquals(Integer.parseInt(initialNumberOfCities.result), Integer.parseInt(Main.execute("COUNT_CITIES 0").result));
        assertArrayEquals(new String[] {
                "Pais invalido"
        }, resultParts);
    }


    @Test
    public void testRemoveCountry(){
        assertTrue(Main.parseFiles(new File("test-files/removeCountry")));
        Result result = Main.execute("REMOVE_COUNTRY Alemanha");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "Removido com sucesso"
        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/removeCountry")));
        result = Main.execute("REMOVE_COUNTRY PaisImaginario");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "Pais invalido"
        }, resultParts);
    }

    @Test
    public void testGetDuplicatesDifferentCountries(){
        assertTrue(Main.parseFiles(new File("test-files/getDuplicatesDifferentCountries")));
        Result result = Main.execute("GET_DUPLICATE_CITIES_DIFFERENT_COUNTRIES 0");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "viseu: Brasil,Portugal",
                "valencia: Colômbia,Espanha"
        }, resultParts);

        result = Main.execute("GET_DUPLICATE_CITIES_DIFFERENT_COUNTRIES 15000");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "viseu: Brasil,Portugal"
        }, resultParts);
    }


    @Test
    public void testGetCitiesAtDistance(){
        assertTrue(Main.parseFiles(new File("test-files/getCitiesAtDistance")));
        Result result = Main.execute("GET_CITIES_AT_DISTANCE 100 Portugal");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "viana do castelo->vila real"
        }, resultParts);

        assertTrue(Main.parseFiles(new File("test-files/getCitiesAtDistance")));
        result = Main.execute("GET_CITIES_AT_DISTANCE 10 China");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "anbu->chenghai"
        }, resultParts);
    }


    @Test
    public void testGetCitiesAtDistance2(){
        assertTrue(Main.parseFiles(new File("test-files/getCitiesAtDistance2")));
        Result result = Main.execute("GET_CITIES_AT_DISTANCE2 50 Portugal");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "cangas->vila praia de ancora",
                "galegos->tomino",
                "meadela->nigran",
                "mozelos->redondela",
                "nigran->viana do castelo"
        }, resultParts);

        result = Main.execute("GET_CITIES_AT_DISTANCE2 60 Espanha");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "bueu->mozelos",
                "cartaya->moncarapacho",
                "macedo de cavaleiros->verin",
                "nigran->sande",
                "povoa de lanhoso->tomino",
                "punta umbria->tavira",
                "viana do castelo->vigo"
        }, resultParts);
    }


    @Test
    public void testCreativeCommand() {
        assertTrue(Main.parseFiles(new File("test-files/getCitiesByRegion")));
        Result result = Main.execute("GET_CITIES_BY_REGION 10 BLA");
        assertNotNull(result);
        assertTrue(result.success);
        String[] resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "Pais invalido"
        }, resultParts);

        result = Main.execute("GET_CITIES_BY_REGION 10 Portugal");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "Portugal -> porto",
        }, resultParts);

        result = Main.execute("GET_CITIES_BY_REGION 10 Alemanha");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "Alemanha -> colonia",
                "Alemanha -> dusseldorf",
                "Alemanha -> frankfurt"
        }, resultParts);

        result = Main.execute("GET_CITIES_BY_REGION 50 Portugal");
        assertNotNull(result);
        assertTrue(result.success);
        resultParts = result.result.split("\n");
        assertArrayEquals(new String[] {
                "Sem resultados",
        }, resultParts);

    }


}