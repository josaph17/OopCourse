package ru.academits.dashiev.csv;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Csv {
    public static void main(String[] args) throws IOException {
        // Буфферизованные потоки
        String readLine;

        String sourcePath = "table.csv";
        String resultPath = "csv2.html";
        boolean isNextTableRow = true;
        boolean isCharIncludeInCell = false;
        boolean isTwoDotsCallFirst = false;

        try (BufferedReader reader = //
                     new BufferedReader( //
                                         new FileReader(sourcePath, StandardCharsets.UTF_8)); //
             BufferedWriter writer = //
                     new BufferedWriter( //
                                         new FileWriter(resultPath, StandardCharsets.UTF_8))) { //
            writer.write("<table border=\"1\">");

            while ((readLine = reader.readLine()) != null) {
                StringBuilder sb = new StringBuilder();
                if (isCharIncludeInCell == false) {
                    sb.append("<tr>");
                    sb.append("<td>");
                } else {
                    sb.append("<br>");
                }

                for (int i = 0; i < readLine.length(); i++) {
                    char currentChar = readLine.charAt(i);

                    System.out.println("currentChar[" + i + "] = " + currentChar);

                    if (i == 10) {
                        System.out.println("hello");
                    }

                    if ((Character.compare(currentChar, '"') == 0)) { // todo символ "
                        if (isTwoDotsCallFirst == false) {
                            isTwoDotsCallFirst = true;
                            isCharIncludeInCell = true; // start involve in cell  before "
                        } else if (i < (readLine.length() - 1) && (Character.compare(
                                readLine.charAt(i + 1), '"') == 0)) {
                            i = i + 1;

                            sb.append('"');
                        } else { // если след за " char не равен "
                            isNextTableRow = !isNextTableRow;

                            isCharIncludeInCell = false;
                            isTwoDotsCallFirst = false;

                            System.out.println("--Close content!--");
                        }
                    } else if (Character.compare(currentChar, ',') == 0) { // todo символ ,
                        // символ ,
                        if (isCharIncludeInCell == true) {
                            sb.append(currentChar);
                        } else {
                            sb.append("</td>");
                            sb.append("<td>");
                        }
                    } else if (Character.compare(currentChar, '<') == 0){
                        sb.append("&lt");
                    } else if (Character.compare(currentChar, '>') == 0){
                        sb.append("&gt");
                    }else if (Character.compare(currentChar, '&') == 0){
                        sb.append("&amp");
                    }
                    else {
                        sb.append(currentChar);
                    }

                    System.out.println(sb);
                }

                if (isCharIncludeInCell == false) {
                    sb.append("</td>");
                    sb.append("</tr>");
                }

                String resultLine = sb.toString();

                System.out.println(sb);

                writer.write(resultLine); // запоняем каждую строчку
            }

            writer.write("</table>");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.print("");
        }
    }
}