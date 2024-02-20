package ru.academits.dashiev.csv_main;

import ru.academits.dashiev.csv.CsvToHtmlConverter;

import java.io.IOException;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                System.out.println("Argument should be 2 arguments, first argument is existing source file name, second argument is producing output Html file name.");

                return;
            }

            String inputPath = args[0];
            String outputPath = args[1];

            CsvToHtmlConverter.convert(inputPath, outputPath);
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found!");
        } catch (IOException e) {
            System.out.println("An IOException occurred while working with the input file.");
        }
    }
}