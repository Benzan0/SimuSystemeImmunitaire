package fr.um3.ProjetInfo.src.PackageConstructionSimu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilesReader {
    public static List<Double> readFiles(String filePath) throws IOException {
        List<Double> numbers = new ArrayList<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("[,\\s]+"); // Séparation des nombres sur les virgules et les espaces
                for (String token : tokens) {
                    try {
                        double number = Double.parseDouble(token); // Conversion de la chaîne de caractères en double
                        numbers.add(number);
                    } catch (NumberFormatException e) {
                        System.out.println("Valeur doit-être un double"+ Arrays.toString(e.getStackTrace()));
                    }
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return numbers;
    }
}
