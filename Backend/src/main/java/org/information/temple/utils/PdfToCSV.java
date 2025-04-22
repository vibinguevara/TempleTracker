package org.information.temple.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class PdfToCSV {

    public static void main(String[] args) {

        String pdfPath = "C:\\Labs\\Project TT\\temple_list_coimbatore.pdf";
        String csvPath = "C:\\Labs\\Project TT\\temple_list_coimbatore.csv";

        try (PDDocument document = PDDocument.load(new File(pdfPath))) {

            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvPath));

            writer.write("Temple Name,City,District,postalcode");
            writer.newLine();

            String[] lines = text.split("\\r?\\n");

            for (String line : lines) {
                System.out.println("line ->>>> "+line);
                    String[] parts = line.split(",");
                    if (parts.length >= 3) {
                        String templeName = parts[0].trim();
                        String city =  parts[1].trim();
                        String district = "";
                            System.out.println("arts[2].length() -->>> " + parts[2].length());
                            if (parts[2].length() != 0) {
                                district = parts[2].trim();
                            } else {
                                district = "";
                            }
                            String postalCode = "";
                            System.out.println("district ->>>> " + district);
                            if (district != "") {
                                if (district.toString().indexOf("-") != -1) {
                                    postalCode = district.split("-")[1];
                                } else {
                                    postalCode = "";
                                }
                            }
                        System.out.println("\"" + templeName + "\",\"" + city + "\",\""+district+"\",\""+postalCode+"\"");
                        writer.write("\"" + templeName + "\",\"" + city + "\",\""+district+"\",\""+postalCode+"\"");
                        writer.newLine();
                    }else{
                        System.out.println("Incorrect order !!");
                    }
            }

            writer.close();

            System.out.println("CSV File generated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
