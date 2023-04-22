package com.uni.bakalauras.fxmlControllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.uni.bakalauras.hibernateOperations.GroupsOperations;
import com.uni.bakalauras.hibernateOperations.ProductsOperations;
import com.uni.bakalauras.model.Groups;
import com.uni.bakalauras.model.Products;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProductInStockController implements Initializable {

    public ComboBox<String> cmTypes;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Groups> groupsList = GroupsOperations.findAllGroups();

        groupsList.forEach(group -> cmTypes.getItems().add(group.getName()));
        cmTypes.getItems().add("Visi");
        cmTypes.getSelectionModel().select("Visi");
    }

    public void createReport(ActionEvent actionEvent) throws FileNotFoundException, DocumentException {
        List<Products> productsList;

        if (Objects.equals(cmTypes.getValue(), "Visi")) {
            productsList = ProductsOperations.findProductsInStock(null);
        }
        else {
            productsList = ProductsOperations.findProductsInStock(GroupsOperations.findByNamePart(cmTypes.getValue()).stream().findFirst().get().getId());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH;mm;ss");
        LocalDateTime now = LocalDateTime.now();

        String fileName = "C:\\Ataskaitos\\pardavimuAtaskaita\\" + "VizituAtaskaita_" + now.format(formatter) + ".pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        PdfPTable table = new PdfPTable(6);

        PdfPCell c9 = new PdfPCell(new Phrase("Rušis"));
        PdfPCell c10 = new PdfPCell(new Phrase("Pavadinimas"));
        PdfPCell c11 = new PdfPCell(new Phrase("Spalva"));
        PdfPCell c12 = new PdfPCell(new Phrase("Išmatavimas"));
        PdfPCell c13 = new PdfPCell(new Phrase("Kaina"));
        PdfPCell c14 = new PdfPCell(new Phrase("Kiekis"));

        table.addCell(c9);
        table.addCell(c10);
        table.addCell(c11);
        table.addCell(c12);
        table.addCell(c13);
        table.addCell(c14);

        for(int i = 0; i<productsList.size(); i++) {
            table.addCell(productsList.get(i).getGroupName());
            table.addCell(productsList.get(i).getName());
            table.addCell(productsList.get(i).getColor());
            table.addCell(productsList.get(i).getMeasurement());
            table.addCell(String.valueOf(productsList.get(i).getSellCost()));
            table.addCell(String.valueOf(productsList.get(i).getInStock()));
        }
        document.add(table);
        document.close();
    }
}
