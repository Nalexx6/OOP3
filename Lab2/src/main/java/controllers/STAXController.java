package controllers;

import util.XMLCreator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class STAXController extends ParserXML {
    private static final Logger log = Logger.getLogger(STAXController.class.getName());

    public STAXController(XMLCreator xmlCreator) {
        this.xmlCreator = xmlCreator;
    }

    @Override
    public void parse(String XMLFile) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader;
        try {
            reader = xmlInputFactory.createXMLEventReader(new FileInputStream(XMLFile));
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    String attributeString = null;
                    nextEvent = reader.nextEvent();
                    String string = startElement.getName().getLocalPart();
                    if (nextEvent.isCharacters()) {
                        beerHandler.setElementValue(nextEvent.asCharacters().getData());
                        beerHandler.setField(string, attributeString);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }

    @Override
    public void createXML() {
        xmlCreator.buildXML(beerHandler.getBeerShop().getBeer(), "src/main/resources/output_stax.xml");
    }
}
