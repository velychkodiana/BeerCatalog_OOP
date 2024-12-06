package org.example.parsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class BeerSAXParser {

    // Метод для парсингу XML файлу за допомогою SAX парсера
    public void parseXML(String filePath) {
        try {
            // Створюємо фабрику SAXParser
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // Створюємо SAXParser для розбору XML
            SAXParser saxParser = factory.newSAXParser();

            // Створюємо обробник для обробки елементів XML
            BeerHandler handler = new BeerHandler();
            // Парсимо файл за допомогою SAX парсера та нашого обробника
            saxParser.parse(new File(filePath), handler);

        } catch (Exception e) {
            // якщо є помилки - виводимо їх
            e.printStackTrace();
        }
    }

    // Внутрішній клас, який обробляє елементи XML за допомогою SAX
    private static class BeerHandler extends DefaultHandler {

        // Логічні змінні для відстеження стану елементів XML
        private boolean isName = false;
        private boolean isType = false;
        private boolean isAlcohol = false;
        private boolean isManufacturer = false;
        private boolean isIngredient = false;
        private boolean isChar = false;

        // Метод, який викликається на кожен стартовий тег елемента
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            // Перевіряємо, який елемент почався, і ставимо відповідну змінну в true
            switch (qName) {
                case "Name" -> isName = true;
                case "Type" -> isType = true;
                case "Alcohol" -> isAlcohol = true;
                case "Manufacturer" -> isManufacturer = true;
                case "Ingredient" -> isIngredient = true;
                case "Char" -> isChar = true;
            }
        }

        // Метод, який викликається на кожен кінцевий тег елемента
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            // Якщо зустріли тег "Beer", виводимо повідомлення про завершення обробки пива
            if ("Beer".equals(qName)) {
                System.out.println("End of Beer\n");
            }
        }

        // Метод, який викликається, коли зустрічаються текстові дані між тегами
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            // Отримуємо текстовий вміст між тегами
            String content = new String(ch, start, length).trim();

            // В залежності від того, який елемент зараз обробляється, виводимо його значення
            if (isName) {
                System.out.println("Name: " + content);
                isName = false;  // Скидаємо прапор після обробки
            } else if (isType) {
                System.out.println("Type: " + content);
                isType = false;
            } else if (isAlcohol) {
                System.out.println("Alcohol: " + content);
                isAlcohol = false;
            } else if (isManufacturer) {
                System.out.println("Manufacturer: " + content);
                isManufacturer = false;
            } else if (isIngredient) {
                System.out.println("Ingredient: " + content);
                isIngredient = false;
            } else if (isChar) {
                System.out.println("Char: " + content);
                isChar = false;
            }
        }
    }

    // Метод main для запуску парсингу XML файлу
    public static void main(String[] args) {
        // Створюємо об'єкт BeerSAXParser
        BeerSAXParser parser = new BeerSAXParser();
        // Вказуємо шлях до XML файлу та викликаємо метод парсингу
        parser.parseXML("path/to/your/beer_catalog.xml");
    }
}