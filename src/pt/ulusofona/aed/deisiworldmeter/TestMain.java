package pt.ulusofona.aed.deisiworldmeter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static pt.ulusofona.aed.deisiworldmeter.TipoEntidade.CIDADE;
import static pt.ulusofona.aed.deisiworldmeter.TipoEntidade.PAIS;
import static pt.ulusofona.aed.deisiworldmeter.TipoEntidade.INPUT_INVALIDO;


public class TestMain {
    @Test
    public void parseFilesCidadesTest(){
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals(8, Main.getObjects(CIDADE).size());
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals(8, Main.getObjects(CIDADE).size());
    }

    @Test
    public void parseFilesPaisesRepetidos(){
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals(6, Main.getObjects(PAIS).size());
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals(6, Main.getObjects(PAIS).size());
    }

    @Test
    public void paisesIdMaior700(){
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("Suécia | 752 | SE | SWE | 151", Main.getObjects(PAIS).get(5));
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("Suécia | 752 | SE | SWE | 151", Main.getObjects(PAIS).get(5));
    }

    @Test
    public void paisesIdMenor700(){
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("Afeganistão | 4 | AF | AFG", Main.getObjects(PAIS).get(0));
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("Afeganistão | 4 | AF | AFG", Main.getObjects(PAIS).get(0));
    }

    @Test
    public void cidadesParaString(){
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("andorra la vella | DE | 07 | 20430 | (42.5,1.5166667)", Main.getObjects(CIDADE).get(0));
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("andorra la vella | DE | 07 | 20430 | (42.5,1.5166667)", Main.getObjects(CIDADE).get(0));
    }

    @Test
    public void paisesECidades(){
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
    public void linhasInvalidzs(){
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("paises.csv | 6 | 2 | 6", Main.getObjects(INPUT_INVALIDO).get(0));
        Assertions.assertEquals("cidades.csv | 8 | 1 | 5", Main.getObjects(INPUT_INVALIDO).get(1));
        Assertions.assertEquals("populacao.csv | 151 | 5 | 2", Main.getObjects(INPUT_INVALIDO).get(2));
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("paises.csv | 6 | 2 | 6", Main.getObjects(INPUT_INVALIDO).get(0));
        Assertions.assertEquals("cidades.csv | 8 | 1 | 5", Main.getObjects(INPUT_INVALIDO).get(1));
        Assertions.assertEquals("populacao.csv | 151 | 5 | 2", Main.getObjects(INPUT_INVALIDO).get(2));

    }
}