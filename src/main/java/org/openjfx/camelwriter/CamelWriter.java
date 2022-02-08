/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.camelwriter;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erso
 */
public class CamelWriter {

    private File inFile;
    String result;


    public CamelWriter(String fName) {
        this.inFile = new File(fName);
    }

    public void readLines() throws IOException {
        // Benyt en Scanner til at læse inFile én linje ad gangen
        Scanner scanner = scanFile(inFile);
        // Kald convert2camel med hver linje.
        StringBuilder result = new StringBuilder();
        while(scanner.hasNextLine()){
            result.append(convert2camel(scanner.nextLine()));
        }
        FileWriter writer = new FileWriter(new File("DryLipsCamel.txt"));
        writer.write(result.toString());
        writer.close();
    }

    private Scanner scanFile(File file){
        try {
            return new Scanner(inFile);
        } catch (FileNotFoundException ex){
            System.out.println(ex);
        }
        return null;
    }

    private String convert2camel(String line) {
        // Split line til et String[] af de enkelte ord i linjen
        if(line.isEmpty()) return "\n";

        String[] words = line.split(" ");
        // Konverter 1. ord til små og resten til stort begyndelsesbogstav
        StringBuilder resultString = new StringBuilder(words[0].substring(0,1).toLowerCase()).append(words[0].substring(1,words[0].length()));
        for (int i = 1; i < words.length; i++) {
            resultString.append(words[i].substring(0, 1).toUpperCase()).append(words[i].substring(1, words[i].length()).toLowerCase());
        }
        // Sammensæt ordene til ét langt ord og udskriv.
        resultString.append("\n");
        return resultString.toString();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        CamelWriter cw = new CamelWriter("DryLips.txt");
        cw.readLines();
    }

}
