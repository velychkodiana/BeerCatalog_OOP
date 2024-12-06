package org.example;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class BeerXMLTransformer {
    public void transformXML(File xmlFile, File xslFile, File outputHtml) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            StreamSource xslStream = new StreamSource(xslFile);
            Transformer transformer = transformerFactory.newTransformer(xslStream);

            StreamSource xmlStream = new StreamSource(xmlFile);
            StreamResult result = new StreamResult(outputHtml);
            transformer.transform(xmlStream, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
