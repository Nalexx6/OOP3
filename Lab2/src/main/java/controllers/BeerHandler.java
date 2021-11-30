package controllers;

import models.Beer;
import models.BeerShop;
import models.Chars;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

public class BeerHandler extends DefaultHandler {
    public static final String BEER_SHOP = "BeerShop";
    public static final String BEER = "Beer";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String ALCOHOL = "alcohol";
    public static final String MANUFACTURER = "manufacturer";
    public static final String CHARS = "Chars";
    public static final String BRILLIANCE = "brilliance";
    public static final String REVOLUTIONS = "revolutions";
    public static final String FILTERED = "filtered";
    public static final String NUTRITION_VALUE = "nutritionValue";
    public static final String CONTAINER_VOLUME = "containerVolume";
    public static final String CONTAINER_MATERIAL = "containerMaterial";
    public static final String INGREDIENT = "Ingredient";
    public BeerShop beerShop = new BeerShop();

    private String elementValue;

    private final String nodeName = "Beer";

    public void setElementValue(String elementValue) {
        this.elementValue = elementValue;
    }

    public String getName() {
        return nodeName;
    }

    @Override
    public void characters(char[] ch, int start, int length){
        elementValue = new String(ch, start, length);
    }

    @Override
    public void startDocument() {
        beerShop = new BeerShop();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr){
        switch (qName) {
            case BEER:
                Beer beer = new Beer();
                beerShop.getBeer().add(beer);
                break;
            case CHARS:
                latestBeer().setChars(new Chars());
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        setField(qName);
    }

    public Beer latestBeer() {
        List<Beer> beerList = beerShop.getBeer();
        return beerList.get(beerList.size() - 1);
    }

    public BeerShop getBeerShop() {
        return beerShop;
    }

    public void setField(String qName) {
        switch (qName) {
            case ID:
                latestBeer().setId(elementValue);
                break;
            case NAME:
                latestBeer().setName(elementValue);
                break;
            case TYPE:
                latestBeer().setType(elementValue);
                break;
            case ALCOHOL:
                latestBeer().setAlcohol(Boolean.parseBoolean((elementValue)));
                break;
            case MANUFACTURER:
                latestBeer().setManufacturer(elementValue);
                break;
            case BRILLIANCE:
                latestBeer().getChars().setBrilliance(Double.parseDouble(elementValue));
                break;
            case REVOLUTIONS:
                latestBeer().getChars().setRevolutions(Integer.parseInt(elementValue));
                break;
            case FILTERED:
                latestBeer().getChars().setFiltered(Boolean.parseBoolean(elementValue));
                break;
            case NUTRITION_VALUE:
                latestBeer().getChars().setNutritionValue(Integer.parseInt(elementValue));
                break;
            case CONTAINER_VOLUME:
                latestBeer().getChars().setContainerVolume(Integer.parseInt(elementValue));
                break;
            case CONTAINER_MATERIAL:
                latestBeer().getChars().setContainerMaterial(elementValue);
                break;
            case INGREDIENT:
                latestBeer().getIngredient().add(elementValue);
            default:
                break;
        }
    }

    public void setField(String qName, String attribute){
        switch (qName) {
            case BEER:
                Beer beer = new Beer();
                beerShop.getBeer().add(beer);
                break;
            case CHARS:
                latestBeer().setChars(new Chars());
                break;
            default:
                setField(qName);
                break;
        }
    }

}
