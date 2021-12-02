
import controllers.DOMController;
import controllers.SAXController;
import controllers.STAXController;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.XMLCreator;
import util.XMLValidator;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DemoTest {
    private static final Logger log = Logger.getLogger(DemoTest.class.getName());

    @Test
    public void testDemo(){
        XMLCreator xmlCreator = new XMLCreator();
        DOMController domController = new DOMController(xmlCreator);
        SAXController saxDrugController = new SAXController(xmlCreator);
        STAXController staxController = new STAXController(xmlCreator);

        String XML = "src/main/resources/input.xml";
        String XSD = "src/main/resources/input.xsd";
        if(XMLValidator.validateXML(XML, XSD)){
            log.info("XML is valid");
        }
        else log.info("XML is not valid");
        saxDrugController.parse(XML);
        saxDrugController.createXML();
        staxController.parse(XML);
        staxController.createXML();
        domController.parse(XML);
        domController.createXML();
        try {
            Assertions.assertTrue(FileUtils.contentEquals(new File("src/main/resources/dom.xml"),
                    new File("src/main/resources/sax.xml")));
            Assertions.assertTrue(FileUtils.contentEquals(new File("src/main/resources/sax.xml"),
                    new File("src/main/resources/stax.xml")));
        }catch (IOException e){
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }
}
