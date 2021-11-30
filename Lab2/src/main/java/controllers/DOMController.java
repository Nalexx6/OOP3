package controllers;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import models.Beer;
import models.Chars;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import util.XMLCreator;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DOMController extends ParserXML {
    private static final Logger log = Logger.getLogger(DOMController.class.getName());

    public DOMController() {}

    public DOMController(XMLCreator xmlCreator) {
        this.xmlCreator = xmlCreator;
    }

    @Override
    public void parse(String xmlPath) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(xmlPath);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }

        Element root = doc.getDocumentElement();
        NodeList beerNodes = root.getElementsByTagName(beerHandler.getName());
        for (int i = 0; i < beerNodes.getLength(); i++) {
            Element beerElem = (Element) beerNodes.item(i);
            beerHandler.getBeerShop().getBeer().add(new Beer());
            beerHandler.latestBeer().setChars(new Chars());
            NodeList childNodes = beerElem.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    Element child = (org.w3c.dom.Element) childNodes.item(j);
                    beerHandler.setElementValue(getChildValue(beerElem, child.getNodeName()));
                    beerHandler.setField(child.getNodeName());
                    NodeList childChildNodes = child.getChildNodes();
                    for (int k = 0; k < childChildNodes.getLength(); k++) {
                        if (childChildNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                            Element childChild = (org.w3c.dom.Element) childChildNodes.item(k);
                            beerHandler.setElementValue(getChildValue(child, childChild.getNodeName()));
                            beerHandler.setField(childChild.getNodeName());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void createXML() {
        xmlCreator.buildXML(beerHandler.getBeerShop().getBeer(),"src/main/resources/output_dom.xml");
    }

    protected static String getChildValue(Element element, String name) {
        Element child = (Element) element.getElementsByTagName(name).item(0);
        if (child == null) {
            return "";
        }
        Node node = child.getFirstChild();
        return node.getNodeValue();
    }
}
