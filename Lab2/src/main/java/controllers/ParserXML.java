package controllers;

import util.XMLCreator;

public abstract class ParserXML {
    protected XMLCreator xmlCreator;

    BeerHandler beerHandler = new BeerHandler();
    public abstract void parse(String XMLFile);
    public abstract void createXML();
}
