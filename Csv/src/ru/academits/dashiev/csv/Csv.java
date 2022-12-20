package ru.academits.dashiev.csv;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Csv {
    public static void main(String[] args) throws IOException {
        // Буфферизованные потоки
        String readLine = null;

        String tableStart = "<table>";
        String tableEnd = "<table>";

        try (BufferedReader reader = new BufferedReader(
                // не получается поставить StandardCharsets.UTF_8
                new FileReader("csv.txt", StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(
                new FileWriter("csv2.txt", StandardCharsets.UTF_8))) {
            while ((readLine = reader.readLine()) != null) {
                writer.write(tableStart);

                System.out.print(readLine);
                writer.write(readLine);

                writer.write(tableEnd);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
