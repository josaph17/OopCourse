package ru.academits.dashiev.csv;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Csv {
    public static void readCSV(String sourcePath, String resultPath) {
        String readLine;

        // Буферизованные потоки
        try (BufferedReader reader = new BufferedReader(new FileReader(sourcePath, StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter( new FileWriter(resultPath, StandardCharsets.UTF_8))) {
            writer.write("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n\t<meta charset=\"UTF-8\">\n\t<title>Document</title>\n</head>\n");
            writer.write("<body>\n");

            boolean isNextTableRow = true;
            boolean isCharIncludeInCell = false;
            boolean isTwoDotsCallFirst = false;

            writer.write("\t<table border=\"1\">\n");

            while ((readLine = reader.readLine()) != null) {
                if (readLine.isEmpty()) {
                    continue;
                }

                if (!isCharIncludeInCell) {
                    writer.write("\t\t<tr>\n");
                    writer.write("\t\t\t<td>");
                } else {
                    writer.write("<br>");
                }

                for (int i = 0; i < readLine.length(); i++) {
                    char currentChar = readLine.charAt(i);

                    /* Прошу не считать ошибкой вместо этого можно использовать == и !=. Character.compare(currentChar, '"') == 0 */
                    if (currentChar == '"') {
                        if (!isTwoDotsCallFirst) {
                            isTwoDotsCallFirst = true;
                            isCharIncludeInCell = true; // start involve in cell  before "
                        } else if (i < (readLine.length() - 1) && (readLine.charAt(i + 1) == '"')) {
                            i++;

                            writer.write('"');
                        } else { // если след за "char не равен"
                            isNextTableRow = !isNextTableRow;

                            isCharIncludeInCell = false;
                            isTwoDotsCallFirst = false;

                            // Here we "Close content!";
                        }
                    } else if (currentChar == ',') {
                        if (isCharIncludeInCell) {
                            writer.write(currentChar);
                        } else {
                            writer.write("</td>\n");
                            writer.write("\t\t\t<td>");
                        }
                    } else if (currentChar == '<') {
                        writer.write("&lt;");
                    } else if (currentChar == '>') {
                        writer.write("&gt;");
                    } else if (currentChar == '&') {
                        writer.write("&amp;");
                    } else {
                        writer.write(currentChar);
                    }
                }

                if (!isCharIncludeInCell) {
                    writer.write("</td>");
                    writer.write("\n\t\t</tr>\n");
                }
                // к этому моменту заполнили строчку
            }

            writer.write("\t</table>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}