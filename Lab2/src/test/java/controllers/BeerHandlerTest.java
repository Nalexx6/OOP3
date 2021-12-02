package controllers;

import models.Beer;
import models.Chars;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BeerHandlerTest {
    private final List<Beer> beers = new ArrayList<>();

    @Before
    public void init(){

    }

    @Test

    public void testHandlerWithDOM(){
        Beer beer1 = new Beer();
        beer1.setChars(new Chars());

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

        beers.add(beer1);

        DOMController controller = new DOMController();
        controller.parse("src/test/resources/handlerTest.xml");
        Assertions.assertEquals(controller.beerHandler.getBeerShop().getBeer().get(0).getName(), beers.get(0).getName());
        Assertions.assertEquals(controller.beerHandler.getBeerShop().getBeer().get(0).getType(), beers.get(0).getType());
        Assertions.assertEquals(controller.beerHandler.getBeerShop().getBeer().get(0).getId(), beers.get(0).getId());
        Assertions.assertEquals(controller.beerHandler.getBeerShop().getBeer().get(0).getManufacturer(), beers.get(0).getManufacturer());
        Assertions.assertEquals(controller.beerHandler.getBeerShop().getBeer().get(0).getIngredient(), beers.get(0).getIngredient());
        Assertions.assertEquals(controller.beerHandler.getBeerShop().getBeer().get(0).getChars(), beers.get(0).getChars());
    }
}
