package ru.academits.dashiev.csv;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Csv {
    public static void main(String[] args) throws IOException {
        // Буфферизованные потоки
        int readLine;

        String sourcePath = "csv.txt";
        String resultPath = "csv2.html";
        String tagTableStart = "<table>";
        String tagTableEnd = "</table>";
        String tagTrStart = "<tr>"; // tableRow
        String tagTrEnd = "</tr>";
        String tagTdStart = "<td>"; // tableDetail
        String tagTdEnd = "</td>";
        String tagBr = "<br>"; // перевод сроки
        String symbolLess = "&lt"; // symbol <
        String symbolBigger = "&gt"; // symbol >
        String symbolAmp = "&amp"; // symbol &

        char[] buffer = new char[100];

        try (BufferedReader reader = //
                     new BufferedReader( //
                             new FileReader(sourcePath, StandardCharsets.UTF_8));
             BufferedWriter writer = //
                     new BufferedWriter( //
                             new FileWriter(resultPath, StandardCharsets.UTF_8))) { //

            while ((readLine = reader.read()) != -1) {
                System.out.print((char) readLine);
                writer.write((char) readLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
