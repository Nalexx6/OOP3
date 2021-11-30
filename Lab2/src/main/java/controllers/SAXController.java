package controllers;


import org.xml.sax.SAXException;
import util.XMLCreator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SAXController extends ParserXML {
    private static final Logger log = Logger.getLogger(SAXParser.class.getName());

    public SAXController(XMLCreator xmlCreator) {
        this.xmlCreator = xmlCreator;
    }

    @Override
    public void parse(String xmlPath){
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(xmlPath, beerHandler);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }

    @Override
    public void createXML() {
        xmlCreator.buildXML(beerHandler.getBeerShop().getBeer(),"src/main/resources/output_sax.xml");
    }
}
