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
        Assertions.assertEquals(4, Main.getObjects(CIDADE).size());
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals(4, Main.getObjects(CIDADE).size());
    }

    @Test
    public void parseFilesPaisesRepetidos(){
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals(4, Main.getObjects(PAIS).size());
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals(4, Main.getObjects(PAIS).size());
    }

    @Test
    public void paisesIncorretos(){
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("paises.csv | 4 | 2 | 6", Main.getObjects(INPUT_INVALIDO).get(0));
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("paises.csv | 4 | 2 | 6", Main.getObjects(INPUT_INVALIDO).get(0));
    }

    @Test
    public void cidadesIncorretas(){
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("cidades.csv | 4 | 1 | 5", Main.getObjects(INPUT_INVALIDO).get(1));
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals("cidades.csv | 4 | 1 | 5", Main.getObjects(INPUT_INVALIDO).get(1));
    }
}
