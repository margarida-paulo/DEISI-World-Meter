package pt.ulusofona.aed.deisiworldmeter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static pt.ulusofona.aed.deisiworldmeter.TipoEntidade.CIDADE;
import static pt.ulusofona.aed.deisiworldmeter.TipoEntidade.PAIS;

public class TestMain {
    @Test
    public void parseFilesCidadesTest(){
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals(5, Main.getObjects(CIDADE).size());
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals(5, Main.getObjects(CIDADE).size());
    }

    @Test
    public void parseFilesPaisesRepetidos(){
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals(6, Main.getObjects(PAIS).size());
        Main.parseFiles(new File("test-files"));
        Assertions.assertEquals(6, Main.getObjects(PAIS).size());
    }
}
