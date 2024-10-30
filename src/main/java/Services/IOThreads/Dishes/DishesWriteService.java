package Services.IOThreads.Dishes;

import Models.Client;
import Models.Dish;
import Services.IRead;
import Services.IWrite;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import Models.Dish;
import Services.IWrite;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DishesWriteService implements IWrite<Dish> {
    @Override
    public void WriteListToFile(List<Dish> list, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Dish dish : list) {
                // Записуємо кожний Dish у форматі CSV: id, name, cost, weightInGrams
                writer.printf("%d, %s, %.2f, %d%n",
                        dish.getId(),
                        dish.getName(),
                        dish.getCost(),
                        dish.getWeightInGrams());
            }
            System.out.println("Список страв успішно записано у файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Помилка при записі у файл: " + e.getMessage());
        }
    }
}
