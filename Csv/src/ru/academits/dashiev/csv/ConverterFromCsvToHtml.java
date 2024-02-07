package ru.academits.dashiev.csv;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ConverterFromCsvToHtml {
    public static void convert(String sourcePath, String resultPath) throws IOException {
        // Буферизованные потоки
        try (BufferedReader reader = new BufferedReader(new FileReader(sourcePath, StandardCharsets.UTF_8));
             PrintWriter writer = new PrintWriter(new FileWriter(resultPath, StandardCharsets.UTF_8))) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html lang=\"en\">");
            writer.println("<head>");
            writer.println("\t<meta charset=\"UTF-8\">");
            writer.println("\t<title>Document</title>");
            writer.println("</head>");
            writer.println("<body>");

            // является ячейка в кавычках
            boolean isCellInQuotes = false;
            boolean isQuotesFirstTime = false;

            writer.println("\t<table border=\"1\">");

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) { // Обрабатываем пустую строку
                    writer.print("");
                } else {
                    if (!isCellInQuotes) {
                        writer.println("\t\t<tr>");
                        writer.print("\t\t\t<td>");
                    } else {
                        writer.print("<br>");
                    }

                    for (int i = 0; i < line.length(); i++) {
                        char currentChar = line.charAt(i);

                        if (currentChar == '"') {
                            if (!isQuotesFirstTime) {
                                isQuotesFirstTime = true;
                                isCellInQuotes = true; // Начинаем включать в кавычки "
                            } else if (i < (line.length() - 1) && (line.charAt(i + 1) == '"')) {
                                i++;

                                writer.print('"');
                            } else { // Если след за "char не равен"
                                isCellInQuotes = false;
                                isQuotesFirstTime = false;
                                // Здесь мы закрываем контент
                            }
                        } else if (currentChar == ',') {
                            if (isCellInQuotes) {
                                writer.print(currentChar);
                            } else {
                                writer.println("</td>");
                                writer.print("\t\t\t<td>");
                            }
                        } else if (currentChar == '<') {
                            writer.print("&lt;");
                        } else if (currentChar == '>') {
                            writer.print("&gt;");
                        } else if (currentChar == '&') {
                            writer.print("&amp;");
                        } else {
                            writer.print(currentChar);
                        }
                    }

                    if (!isCellInQuotes) {
                        writer.println("</td>");
                        writer.println("\t\t</tr>");
                    }
                    // К этому моменту заполнили строчку
                }
            }

            writer.println("\t</table>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}