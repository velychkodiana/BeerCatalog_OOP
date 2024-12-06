package org.example.parsers;

import org.example.generated.Beer;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeerParserStAX {

    public List<Beer> parse(File xmlFile) {
        List<Beer> beers = new ArrayList<>();
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(xmlFile));

            String beerName = null, type = null, manufacturer = null;
            boolean alcohol = false;

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();

                    switch (startElement.getName().getLocalPart()) {
                        case "BeerName":
                            beerName = reader.getElementText();
                            break;
                        case "Type":
                            type = reader.getElementText();
                            break;
                        case "Alcohol":
                            alcohol = Boolean.parseBoolean(reader.getElementText());
                            break;
                        case "Manufacturer":
                            manufacturer = reader.getElementText();
                            break;
                    }
                } else if (event.isEndElement() && event.asEndElement().getName().getLocalPart().equals("Beer")) {
                    beers.add(new Beer(beerName, type, alcohol, manufacturer));
                }
            }

        } catch (XMLStreamException e) {
            System.err.println("Помилка парсингу XML: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Помилка читання файлу: " + e.getMessage());
        }

        return beers;
    }
}

