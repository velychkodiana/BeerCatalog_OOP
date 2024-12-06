package org.example;

import org.example.parsers.BeerParserDOM;
import org.example.parsers.BeerParserStAX;
import org.example.BeerXMLTransformer;
//import org.example.Beer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

public class Main {
    // Логер для логування
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // Встановлюємо шлях до файлів XML
        String filePath = "/Users/macbookpro/Desktop/ооп/BeerCatalog/src/main/resources/beers.xml";
        File xmlFile = new File(filePath);

        // Парсинг файлу через DOM
        BeerParserDOM parserDOM = new BeerParserDOM();
        List<Beer> beersDOM = parserDOM.parse(xmlFile);
        logger.info("Beers parsed using DOM: ");
        beersDOM.forEach(beer -> logger.info(beer));

        // Парсинг  через StAX
        BeerParserStAX parserStAX = new BeerParserStAX();
        List<Beer> beersStAX = parserStAX.parse(xmlFile);
        logger.info("Beers parsed using StAX: ");
        beersStAX.forEach(beer -> logger.info(beer));

        // Перетворення XML у інший формат
        BeerXMLTransformer transformer = new BeerXMLTransformer();
        String transformedFilePath = "/Users/macbookpro/Desktop/ооп/BeerCatalog/src/main/resources/transformed_beers.xml";
        transformer.transform(xmlFile, new File(transformedFilePath));
        logger.info("XML file transformed and saved to: " + transformedFilePath);
    }
}
