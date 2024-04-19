package File;

import Delivery.Tastamat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileSaver {
    public static void saveTastamatsToFile(ArrayList<Tastamat> tastamats, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Tastamat tastamat : tastamats) {
                // Форматирование строки для записи в файл
                String line = tastamat.getIndex() + ", " + tastamat.getCity() + ", " + tastamat.getLocation();
                writer.write(line);
                writer.newLine(); // Переход на новую строку
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи файла: " + e.getMessage());
        }
    }
}

