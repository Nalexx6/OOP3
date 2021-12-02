package controllers;

import org.apache.commons.io.FileUtils;
import models.Beer;
import models.Chars;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.XMLCreator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class XMLCreatorTest {
    private static final Logger log = Logger.getLogger(XMLCreatorTest.class.getName());

    @Test
    public void createXML(){
        XMLCreator xmlCreator = new XMLCreator();
        List<Beer> beers = new ArrayList<>();
        Beer beer1 = new Beer();
        Beer beer2 = new Beer();
        beer1.setChars(new Chars());
        beer2.setChars(new Chars());

        beer1.setName("Corona");
        beer1.setId("1");
        beer1.setType("lager");
        beer1.setAlcohol(true);
        beer1.setManufacturer("Grupo Modelo");
        beer1.getIngredient().add("water");
        beer1.getIngredient().add("barley molt");
        beer1.getIngredient().add("corn");
        beer1.getIngredient().add("hops");
        beer1.getIngredient().add("yeast");
        beer1.getChars().setRevolutions(100);
        beer1.getChars().setFiltered(true);
        beer1.getChars().setNutritionValue(100);
        beer1.getChars().setBrilliance(4.5);
        beer1.getChars().setContainerVolume(100);
        beer1.getChars().setContainerMaterial("steel");

        beer2.setName("Heineken");
        beer2.setId("2");
        beer2.setType("light");
        beer2.setAlcohol(true);
        beer2.setManufacturer("Heineken");
        beer2.getIngredient().add("water");
        beer2.getIngredient().add("barley molt");
        beer2.getIngredient().add("corn");
        beer2.getIngredient().add("hops");
        beer2.getIngredient().add("yeast");
        beer2.getChars().setRevolutions(200);
        beer2.getChars().setFiltered(true);
        beer2.getChars().setNutritionValue(200);
        beer2.getChars().setBrilliance(5.0);
        beer2.getChars().setContainerVolume(200);
        beer2.getChars().setContainerMaterial("steel");


        beers.add(beer1);
        beers.add(beer2);
        xmlCreator.buildXML(beers,"src/test/resources/XMLCreatorTest.xml");
        try {
            Assertions.assertTrue(FileUtils.contentEquals(new File("src/test/resources/XMLCreatorTest.xml"),
                    new File("src/test/resources/creatorExpected.xml")));
        }catch (IOException e){
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }

}
