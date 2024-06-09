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
    public void testGetCitiesByRegion() {
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