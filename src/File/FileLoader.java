package File;

import Delivery.Tastamat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader {
    public static ArrayList<Tastamat> loadTastamatsFromFile(String filename) {
        ArrayList<Tastamat> tastamats = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Парсинг строки для создания объекта Tastamat
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int index = Integer.parseInt(parts[0].trim());
                    String city = parts[1].trim();
                    String location = parts[2].trim();
                    tastamats.add(new Tastamat(index, city, location));
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return tastamats;
    }
}

