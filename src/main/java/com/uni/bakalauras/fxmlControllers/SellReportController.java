package com.uni.bakalauras.fxmlControllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.uni.bakalauras.hibernateOperations.OrdersOperations;
import com.uni.bakalauras.model.Have;
import com.uni.bakalauras.model.Orders;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;

public class SellReportController implements Initializable {

    public TextField fldStatus;
    public DatePicker fldStartDate;
    public DatePicker fldEndDate;

    public Boolean witchOption;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fldStartDate.setConverter(
                new StringConverter<>() {
                    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    @Override
                    public String toString(LocalDate date) {
                        return (date != null) ? dateFormatter.format(date) : "";
                    }

                    @Override
                    public LocalDate fromString(String string) {
                        return (string != null && !string.isEmpty())
                                ? LocalDate.parse(string, dateFormatter)
                                : null;
                    }
                });

        fldEndDate.setConverter(
                new StringConverter<>() {
                    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    @Override
                    public String toString(LocalDate date) {
                        return (date != null) ? dateFormatter.format(date) : "";
                    }

                    @Override
                    public LocalDate fromString(String string) {
                        return (string != null && !string.isEmpty())
                                ? LocalDate.parse(string, dateFormatter)
                                : null;
                    }
                });

    }

    public void setFromData(Boolean witchOption) {
        this.witchOption =witchOption;
    }


    public void createReport(ActionEvent actionEvent) throws FileNotFoundException, DocumentException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH;mm;ss");
        LocalDateTime now = LocalDateTime.now();

        List<Orders> orderList;

        String dateInterval = "Data";

        String fileName = "C:\\Ataskaitos\\pardavimuAtaskaita\\" + "PardavimuAtaskaita_" + now.format(formatter) + ".pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        String startDateString = "";
        String endDateString = "";

        if (!Objects.equals(fldStartDate.getValue(), null)) {
            dateInterval = dateInterval + " nuo: " + fldStartDate.getValue();
            startDateString = String.valueOf(fldStartDate.getValue());
        }

        if (!Objects.equals(fldEndDate.getValue(), null)) {
            dateInterval = dateInterval + " iki: " + fldEndDate.getValue();
            endDateString = String.valueOf(fldEndDate.getValue());
        }


        Paragraph pDateInterval = new Paragraph(dateInterval);
        Paragraph pName;

        if (witchOption) {
            pName = new Paragraph("pardavimu ataskaita su prekėmis");
        }
        else {
            pName = new Paragraph("pardavimu ataskaita");
        }


        document.add(pName);
        document.add(pDateInterval);
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(7);

        PdfPCell c2 = new PdfPCell(new Phrase("Data"));
        PdfPCell c3 = new PdfPCell(new Phrase("Klientas"));
        PdfPCell c4 = new PdfPCell(new Phrase("Adresas"));
        PdfPCell c5 = new PdfPCell(new Phrase("Būsena"));
        PdfPCell c6= new PdfPCell(new Phrase("Pristatymo būsena"));
        PdfPCell c7 = new PdfPCell(new Phrase("Suma"));
        PdfPCell c8 = new PdfPCell(new Phrase(""));
        PdfPCell c9 = new PdfPCell(new Phrase("Rušis"));
        PdfPCell c10 = new PdfPCell(new Phrase("Pavadinimas"));
        PdfPCell c11 = new PdfPCell(new Phrase("Spalva"));
        PdfPCell c12 = new PdfPCell(new Phrase("Išmatavimas"));
        PdfPCell c13 = new PdfPCell(new Phrase("Kaina"));
        PdfPCell c14 = new PdfPCell(new Phrase("Kiekis"));


        table.addCell(c2);
        table.addCell(c3);
        table.addCell(c4);
        table.addCell(c5);
        table.addCell(c6);
        table.addCell(c7);
        table.addCell(c8);

        List<String> filters = new ArrayList<>();

        filters.add("");
        filters.add(startDateString);
        filters.add(endDateString);
        filters.add("");
        filters.add("");
        filters.add("");
        filters.add("");
        filters.add(fldStatus.getText());
        filters.add("");

        orderList = OrdersOperations.findOrderByFilters(filters);

        orderList.sort(Comparator.comparing(Orders::getCreated));

        for(int i = 0; i<orderList.size(); i++) {
            List<Have> haveList = OrdersOperations.findOrdersProduct(orderList.get(i).getId());
            table.addCell(String.valueOf(orderList.get(i).getCreated()));
            table.addCell(orderList.get(i).getClientName());
            table.addCell(orderList.get(i).getOrderAddress());
            table.addCell(orderList.get(i).getStatus());
            table.addCell(orderList.get(i).getDeliveryType());
            table.addCell(orderList.get(i).getSumToString());
            table.addCell(c8);
            if (witchOption && 0<haveList.size()) {
                table.addCell(c8);
                table.addCell(c9);
                table.addCell(c10);
                table.addCell(c11);
                table.addCell(c12);
                table.addCell(c13);
                table.addCell(c14);

                for(int j = 0; j<haveList.size(); j++) {
                    table.addCell(c8);
                    table.addCell(haveList.get(j).getProduct().getGroupName());
                    table.addCell(haveList.get(j).getProduct().getName());
                    table.addCell(haveList.get(j).getProduct().getColor());
                    table.addCell(haveList.get(j).getProduct().getMeasurement());
                    table.addCell(haveList.get(j).getProduct().getSellCostToString());
                    table.addCell(String.valueOf(haveList.get(j).getQuantity()));
                }
            }

        }
        document.add(table);
        document.close();
    }
}
