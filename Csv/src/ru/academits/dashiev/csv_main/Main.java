package ru.academits.dashiev.csv_main;

import ru.academits.dashiev.csv.ConverterFromCsvToHtml;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try{
            if (args.length != 2){
                throw new IllegalArgumentException("Argument should be 2, first argument is existing source file name, second argument is producing output Html file name.");
            }

            String  sourcePath = args[0];
            String  outputPath = args[1];

            ConverterFromCsvToHtml.convert(sourcePath, outputPath);
        } catch (IllegalArgumentException e){
            System.out.println("Argument should be 2, first argument is existing source file name, second argument is producing output Html file name.");
        } catch (IOException e) {
            System.out.println("File not found!");
        }
    }
}