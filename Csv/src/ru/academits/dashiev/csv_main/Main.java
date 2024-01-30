package ru.academits.dashiev.csv_main;

import ru.academits.dashiev.csv.Converter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // table.csv, v2.html
        try{
            String[] paths = new String[args.length];

            paths[0] = args[0];
            paths[1] = args[1];

            // System.arraycopy(args, 0, paths, 0, args.length);

            Converter.convertFromCsvToHtml(paths[0], paths[1]);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}