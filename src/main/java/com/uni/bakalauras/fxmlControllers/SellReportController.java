package com.uni.bakalauras.fxmlControllers;

import com.itextpdf.text.DocumentException;
import com.uni.bakalauras.hibernateOperations.OrdersOperations;
import com.uni.bakalauras.model.Orders;
import com.uni.bakalauras.util.MakeObservable;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.util.StringConverter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class SellReportController implements Initializable {

    public TextField fldStatus;
    public DatePicker fldStartDate;
    public DatePicker fldEndDate;

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


    public void createReport(ActionEvent actionEvent) throws FileNotFoundException, DocumentException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH;mm;ss");
        LocalDateTime now = LocalDateTime.now();

        List<Orders> orderList = new ArrayList<>();

        String dateInterval = "Data";

        String fileName = "C:\\Ataskaitos\\pardavimuAtaskaita\\" + "VizituAtaskaita_" + now.format(formatter) + ".pdf";
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

        document.add(pDateInterval);
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(6);

        PdfPCell c2 = new PdfPCell(new Phrase("Data"));
        PdfPCell c3 = new PdfPCell(new Phrase("Klientas"));
        PdfPCell c4 = new PdfPCell(new Phrase("Adresas"));
        PdfPCell c5 = new PdfPCell(new Phrase("Būsena"));
        PdfPCell c6= new PdfPCell(new Phrase("Pristatymo būsena"));
        PdfPCell c7 = new PdfPCell(new Phrase("Suma"));

        table.addCell(c2);
        table.addCell(c3);
        table.addCell(c4);
        table.addCell(c5);
        table.addCell(c6);
        table.addCell(c7);

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

        for(int i = 0; i<orderList.size(); i++){
            table.addCell(String.valueOf(orderList.get(i).getCreated()));
            table.addCell(orderList.get(i).getClientName());
            table.addCell(orderList.get(i).getOrderAddress());
            table.addCell(orderList.get(i).getStatus());
            table.addCell(orderList.get(i).getDeliveryType());
            table.addCell(String.valueOf(orderList.get(i).getSum()));
        }
        document.add(table);
        document.close();

    }
}
