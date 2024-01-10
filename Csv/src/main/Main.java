package main;

import ru.academits.dashiev.csv.Csv;

public class Main {
    public static void main(String[] args) {
        String sourcePath = "table.csv";
        String resultPath = "csv2.html";

        Csv.readCSV(sourcePath, resultPath);
    }
}
