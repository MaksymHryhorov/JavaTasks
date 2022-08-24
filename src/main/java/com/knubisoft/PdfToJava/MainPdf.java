package com.knubisoft.PdfToJava;

import com.knubisoft.ORM.format.CSVOrm;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPdf {

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\Projects\\java-education\\src\\main\\resources\\sample.pdf");
        PDDocument document = PDDocument.load(file);

        PDFTextStripper s = new PDFTextStripper();
        String content = s.getText(document);

        String[] splitter = content.split("Category Budget Actual");
        String[] formatter = splitter[1].split("Total");

        List<String> stringList = new ArrayList<>();
        stringList.add(0, "Category Budget Actual");

        stringList.addAll(List.of(formatter[0].trim().split("\r\n")));

        // TODO: Delete first space between two words in first column
        stringList.set(6, "PersonalItems 300,00 UAH 80,00 UAH");
        List<Model> personList = CSVOrm.transform(stringList, Model.class);

        for (Model model : personList) {
            System.out.println(model);
        }
    }
}
