package org.example.parsers;

import org.example.generated*;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BeerParserDOM {

    // Метод для парсингу XML файлу за допомогою DOM парсера
    public List<Beer> parse(String FilePath) throws Exception {
        // Список для збереження об'єктів пива
        List<Beer> beers = new ArrayList<>();

        try {
            // Створюємо фабрику для створення DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Парсимо XML файл в об'єкт Document
            Document document = builder.parse(new File(xmlFilePath));

            // Отримуємо всі елементи <beer> з XML
            NodeList beerNodes = document.getElementsByTagName("beer");

            // Проходимо всі елементи <beer>
            for (int i = 0; i < beerNodes.getLength(); i++) {
                Node beerNode = beerNodes.item(i);

                // Перевіряємо, чи <beer> - елемент , а не текст чи коментар
                if (beerNode.getNodeType() == Node.ELEMENT_NODE) {
                    // Перетворюємо Node на елемент
                    Element beerElement = (Element) beerNode;

                    // Зчитуємо атрибути елемента <beer>
                    String name = beerElement.getAttribute("name");
                    String type = beerElement.getAttribute("type");
                    boolean isAlcoholic = Boolean.parseBoolean(beerElement.getAttribute("alcoholic"));
                    String manufacturer = beerElement.getAttribute("manufacturer");

                    // Створюємо список для інгредієнтів
                    List<String> ingredients = new ArrayList<>();
                    // Отримуємо всі елементи <ingredient> для поточного пива
                    NodeList ingredientNodes = beerElement.getElementsByTagName("ingredient");
                    for (int j = 0; j < ingredientNodes.getLength(); j++) {
                        // Додаємо текст в список інгредієнтів
                        ingredients.add(ingredientNodes.item(j).getTextContent());
                    }

                    // Створюємо список для характеристик
                    List<String> characteristics = new ArrayList<>();
                    // Отримуємо всі елементи <characteristic> для поточного пива
                    NodeList characteristicNodes = beerElement.getElementsByTagName("characteristic");
                    for (int j = 0; j < characteristicNodes.getLength(); j++) {
                        // Додаємо текст в список характеристик
                        characteristics.add(characteristicNodes.item(j).getTextContent());
                    }

                    // Створюємо об'єкт Beer і додаємо його в список
                    beers.add(new Beer(name, type, isAlcoholic, manufacturer, ingredients, characteristics));
                }
            }
        } catch (Exception e) {
            // Виводимо помилки, якщо вони виникли
            e.printStackTrace();
        }

        // Повертаємо список пив
        return beers;
    }
}