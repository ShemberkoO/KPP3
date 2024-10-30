package Services.IOThreads.Dishes;

import Models.Dish;
import Services.IRead;


import java.util.List;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DishesReadService implements IRead<Dish> {
    @Override
    public List<Dish> ReadListFromFile(String filename) {
        List<Dish> dishes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(", ");
                if (parts.length == 4) { // Перевіряємо, чи є всі потрібні поля
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double cost = Double.parseDouble(parts[2].trim().replace(',', '.'));
                    int weightInGrams = Integer.parseInt(parts[3].trim());

                    // Створюємо об'єкт Dish і додаємо його до списку
                    Dish dish = new Dish(id, name, cost, weightInGrams);
                    dishes.add(dish);
                }
            }
            System.out.println("Список страв успішно прочитано з файлу: " + filename);
        } catch (IOException e) {
            System.err.println("Помилка при читанні з файлу: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Помилка формату чисел у файлі: " + e.getMessage());
        }

        return dishes;
    }
}
