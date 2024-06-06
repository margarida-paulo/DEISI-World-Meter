package pt.ulusofona.aed.deisiworldmeter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import static pt.ulusofona.aed.deisiworldmeter.TipoEntidade.CIDADE;
import static pt.ulusofona.aed.deisiworldmeter.TipoEntidade.PAIS;
import static pt.ulusofona.aed.deisiworldmeter.TipoEntidade.INPUT_INVALIDO;


public class TestMain {
/*    @Test
    public void parseFilesCidadesTest() {
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals(8, Main.getObjects(CIDADE).size());
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals(8, Main.getObjects(CIDADE).size());
    }

    @Test
    @Disabled("nao funciona, por agora")
    public void parseFilesPaisesRepetidos() {
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals(6, Main.getObjects(PAIS).size());
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals(6, Main.getObjects(PAIS).size());
    }

    @Test
    @Disabled("nao funciona, por agora")
    public void paisesIdMaior700() {
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("Suécia | 752 | SE | SWE | 151", Main.getObjects(PAIS).get(3));
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("Suécia | 752 | SE | SWE | 151", Main.getObjects(PAIS).get(3));
    }

    @Test
    public void paisesIdMenor700() {
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("Afeganistão | 4 | AF | AFG", Main.getObjects(PAIS).get(0));
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("Afeganistão | 4 | AF | AFG", Main.getObjects(PAIS).get(0));
    }

    @Test
    public void cidadesParaString() {
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("andorra la vella | DE | 07 | 20430 | (42.5,1.5166667)", Main.getObjects(CIDADE).get(0));
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("andorra la vella | DE | 07 | 20430 | (42.5,1.5166667)", Main.getObjects(CIDADE).get(0));
    }

    @Test
    public void paisesECidades() {
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("Afeganistão | 4 | AF | AFG", Main.getObjects(PAIS).get(0));
        Assertions.assertEquals("África do Sul | 710 | ZA | ZAF | 0", Main.getObjects(PAIS).get(1));
        Assertions.assertEquals("anar darreh | AF | 06 | 10023 | (32.758697999999995,61.653969)", Main.getObjects(CIDADE).get(4));
        Assertions.assertEquals("acin | AF | 18 | 15098 | (34.082481,70.668152)", Main.getObjects(CIDADE).get(5));
        Assertions.assertEquals("caledon | ZA | 11 | 4687 | (-34.229974,19.426502)", Main.getObjects(CIDADE).get(6));
        Assertions.assertEquals("butterworth | ZA | 05 | 34353 | (-32.330833,28.149808)", Main.getObjects(CIDADE).get(7));
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("Afeganistão | 4 | AF | AFG", Main.getObjects(PAIS).get(0));
        Assertions.assertEquals("África do Sul | 710 | ZA | ZAF | 0", Main.getObjects(PAIS).get(1));
        Assertions.assertEquals("anar darreh | AF | 06 | 10023 | (32.758697999999995,61.653969)", Main.getObjects(CIDADE).get(4));
        Assertions.assertEquals("acin | AF | 18 | 15098 | (34.082481,70.668152)", Main.getObjects(CIDADE).get(5));
        Assertions.assertEquals("caledon | ZA | 11 | 4687 | (-34.229974,19.426502)", Main.getObjects(CIDADE).get(6));
        Assertions.assertEquals("butterworth | ZA | 05 | 34353 | (-32.330833,28.149808)", Main.getObjects(CIDADE).get(7));
    }

    @Test
    public void linhasInvalidas() {
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("paises.csv | 6 | 5 | 6", Main.getObjects(INPUT_INVALIDO).get(0));
        Assertions.assertEquals("cidades.csv | 8 | 1 | 5", Main.getObjects(INPUT_INVALIDO).get(1));
        Assertions.assertEquals("populacao.csv | 151 | 5 | 2", Main.getObjects(INPUT_INVALIDO).get(2));
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("paises.csv | 6 | 5 | 6", Main.getObjects(INPUT_INVALIDO).get(0));
        Assertions.assertEquals("cidades.csv | 8 | 1 | 5", Main.getObjects(INPUT_INVALIDO).get(1));
        Assertions.assertEquals("populacao.csv | 151 | 5 | 2", Main.getObjects(INPUT_INVALIDO).get(2));

    }

    @Test
    public void linha() {
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("paises.csv | 6 | 5 | 6", Main.getObjects(INPUT_INVALIDO).get(0));
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("paises.csv | 6 | 5 | 6", Main.getObjects(INPUT_INVALIDO).get(0));
    }

    *//*
    @Test
    public void bigFiles(){
        Main.parseFiles(new File("Data"));
        ArrayList<String> paises = Main.getObjects(PAIS);
        for (int i = 0; i < paises.size(); i++){
            System.out.println((i + 2) + " " + Main.getObjects(PAIS).get(i));
        }
        System.out.println("\n\nSECOND TIME:\n\n");
        for (int i = 0; i < paises.size(); i++){
            System.out.println((i + 2) + " " + Main.getObjects(PAIS).get(i));
        }
        Assertions.assertEquals("Suécia | 752 | SE | SWE | 151", Main.getObjects(PAIS).get(170));
        Main.parseFiles(new File("Data"));
        Assertions.assertEquals("Suécia | 752 | SE | SWE | 151", Main.getObjects(PAIS).get(170));
        Main.parseFiles(new File("Data"));
        Assertions.assertEquals("Suécia | 752 | SE | SWE | 151", Main.getObjects(PAIS).get(170));
    }

     *//*


    @Test
    public void linhasIncorretas(){
        Main.parseFiles(new File("Data"));
        System.out.println(Main.getObjects(INPUT_INVALIDO));

        Main.parseFiles(new File("Data"));
        System.out.println(Main.getObjects(INPUT_INVALIDO));
        Main.parseFiles(new File("Data"));
        ArrayList<String> cidades = Main.getObjects(CIDADE);
        System.out.println(Main.getObjects(INPUT_INVALIDO));
        System.out.println(Main.getObjects(INPUT_INVALIDO));
  //      for (int i = 0; i < cidades.size(); i++){
  //          System.out.println((i + 2) + " " + Main.getObjects(CIDADE).get(i));
   //     }
    }*/

    /*
de,andorra la vella,07,20430.0,42.5,1.5166667
de,canillo,02,3292.0,42.5666667,1.6
af,encamp,03,11224.0,42.533333299999995,1.5833333
al,almada,04,2291.0,60.63333299999999,8.566667
af,anar darreh,06,10023.0,32.758697999999995,61.653969
af,acin,18,15098.0,34.082481,70.668152
za,caledon,11,4687.0,-34.229974,19.426502
za,butterworth,05,34353.0,-32.330833,28.149808
     */

    @Test
    public void testeGeralParseFiles(){
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("Afeganistão | 4 | AF | AFG", Main.getObjects(PAIS).get(0));
        Assertions.assertEquals("África do Sul | 710 | ZA | ZAF | 7", Main.getObjects(PAIS).get(1));
        Assertions.assertEquals("Albânia | 8 | AL | ALB", Main.getObjects(PAIS).get(2));
        Assertions.assertEquals("Alemanha | 276 | DE | DEU", Main.getObjects(PAIS).get(3));

        Assertions.assertEquals("andorra la vella | DE | 07 | 20430 | (42.5,1.5166667)", Main.getObjects(CIDADE).get(0));
        Assertions.assertEquals("canillo | DE | 02 | 3292 | (42.5666667,1.6)", Main.getObjects(CIDADE).get(1));
        Assertions.assertEquals("encamp | AF | 03 | 11224 | (42.533333299999995,1.5833333)", Main.getObjects(CIDADE).get(2));
        Assertions.assertEquals("almada | AL | 04 | 2291 | (60.63333299999999,8.566667)", Main.getObjects(CIDADE).get(3));
        Assertions.assertEquals("anar darreh | AF | 06 | 10023 | (32.758697999999995,61.653969)", Main.getObjects(CIDADE).get(4));
        Assertions.assertEquals("acin | AF | 18 | 15098 | (34.082481,70.668152)", Main.getObjects(CIDADE).get(5));
        Assertions.assertEquals("caledon | ZA | 11 | 4687 | (-34.229974,19.426502)", Main.getObjects(CIDADE).get(6));
        Assertions.assertEquals("butterworth | ZA | 05 | 34353 | (-32.330833,28.149808)", Main.getObjects(CIDADE).get(7));


        // LinhasOk, LinhasNaoOk, PrimeiraLinhaNaoOk
        Assertions.assertEquals("paises.csv | 4 | 7 | 4", Main.getObjects(INPUT_INVALIDO).get(0));
        Assertions.assertEquals("cidades.csv | 8 | 1 | 5", Main.getObjects(INPUT_INVALIDO).get(1));

        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("Afeganistão | 4 | AF | AFG", Main.getObjects(PAIS).get(0));
        Assertions.assertEquals("África do Sul | 710 | ZA | ZAF | 7", Main.getObjects(PAIS).get(1));
        Assertions.assertEquals("Albânia | 8 | AL | ALB", Main.getObjects(PAIS).get(2));
        Assertions.assertEquals("Alemanha | 276 | DE | DEU", Main.getObjects(PAIS).get(3));

        Assertions.assertEquals("andorra la vella | DE | 07 | 20430 | (42.5,1.5166667)", Main.getObjects(CIDADE).get(0));
        Assertions.assertEquals("canillo | DE | 02 | 3292 | (42.5666667,1.6)", Main.getObjects(CIDADE).get(1));
        Assertions.assertEquals("encamp | AF | 03 | 11224 | (42.533333299999995,1.5833333)", Main.getObjects(CIDADE).get(2));
        Assertions.assertEquals("almada | AL | 04 | 2291 | (60.63333299999999,8.566667)", Main.getObjects(CIDADE).get(3));
        Assertions.assertEquals("anar darreh | AF | 06 | 10023 | (32.758697999999995,61.653969)", Main.getObjects(CIDADE).get(4));
        Assertions.assertEquals("acin | AF | 18 | 15098 | (34.082481,70.668152)", Main.getObjects(CIDADE).get(5));
        Assertions.assertEquals("caledon | ZA | 11 | 4687 | (-34.229974,19.426502)", Main.getObjects(CIDADE).get(6));
        Assertions.assertEquals("butterworth | ZA | 05 | 34353 | (-32.330833,28.149808)", Main.getObjects(CIDADE).get(7));

    }

    @Test
    public void testeCountCities(){
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("8", ExecutionFunctions.countCities(new String[]{"COUNT_CITIES", "0"}));
        Assertions.assertEquals("8", ExecutionFunctions.countCities(new String[]{"COUNT_CITIES", "2291"}));
        Assertions.assertEquals("7", ExecutionFunctions.countCities(new String[]{"COUNT_CITIES", "2292"}));
    }

    @Test
    public void testeTopCitiesByCountry(){
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("abrantes:13K\n" +
                "weida:10K\n" +
                "abraveses:9K\n" +
                "adaufe:3K\n" +
                "adjusta:1K\n", ExecutionFunctions.getTopCitiesByCountry(new String[]{"GET_TOP_CITIES_BY_COUNTRY", "-1", "Portugal"}));

        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("abrantes:13K\n" +
                "weida:10K\n" +
                "abraveses:9K\n" +
                "adaufe:3K\n" +
                "adjusta:1K\n", ExecutionFunctions.getTopCitiesByCountry(new String[]{"GET_TOP_CITIES_BY_COUNTRY", "-1", "Portugal"}));

        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("abrantes:13K\n" +
                "weida:10K\n" +
                "abraveses:9K\n" +
                "adaufe:3K\n" +
                "adjusta:1K\n", ExecutionFunctions.getTopCitiesByCountry(new String[]{"GET_TOP_CITIES_BY_COUNTRY", "-1", "Portugal"}));


    }



}