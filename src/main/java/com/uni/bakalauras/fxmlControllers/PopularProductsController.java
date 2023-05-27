package com.uni.bakalauras.fxmlControllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.uni.bakalauras.hibernateOperations.GroupsOperations;
import com.uni.bakalauras.hibernateOperations.OrdersOperations;
import com.uni.bakalauras.model.Groups;
import com.uni.bakalauras.model.Have;
import com.uni.bakalauras.model.Orders;
import com.uni.bakalauras.util.PopupOperations;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class PopularProductsController implements Initializable {
    public DatePicker fldStartDate;
    public DatePicker fldEndDate;
    public ComboBox<String> cmGroups;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        List<Groups> groupsList = GroupsOperations.findAllGroups();

        groupsList.forEach(group -> cmGroups.getItems().add(group.getName()));
        cmGroups.getItems().add("Visi");
        cmGroups.getSelectionModel().select("Visi");
    }

    public void createReport(ActionEvent actionEvent) throws DocumentException, IOException {
        String startDateString = "";
        String endDateString = "";
        DecimalFormat df = new DecimalFormat("#.00");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH;mm;ss");
        LocalDateTime now = LocalDateTime.now();

        String dateInterval = "Data";

        if (!Objects.equals(fldStartDate.getValue(), null)) {
            dateInterval = dateInterval + " nuo: " + fldStartDate.getValue();
            startDateString = String.valueOf(fldStartDate.getValue());
        }

        if (!Objects.equals(fldEndDate.getValue(), null)) {
            dateInterval = dateInterval + " iki: " + fldEndDate.getValue();
            endDateString = String.valueOf(fldEndDate.getValue());
        }

        List<Have> productsList = getSoldProducts(startDateString, endDateString);

        String fileName = "C:\\Ataskaitos\\pardavimuAtaskaita\\" + "PopuliariausiosPrekės_" + now.format(formatter) + ".pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        BaseFont bfComic = BaseFont.createFont("C:\\Users\\Mintautas\\IdeaProjects\\Bakalauras\\src\\main\\resources\\com\\uni\\bakalauras\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Paragraph pDateInterval = new Paragraph(dateInterval);
        Paragraph pName = new Paragraph("Populiariausios prekės", new Font(bfComic, 12));

        document.add(pName);
        document.add(pDateInterval);
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);

        PdfPCell c9 = new PdfPCell(new Phrase("Rūšis"));
        PdfPCell c10 = new PdfPCell(new Phrase("Pavadinimas"));
        PdfPCell c11 = new PdfPCell(new Phrase("Spalva"));
        PdfPCell c12 = new PdfPCell(new Phrase("Matmenys"));
        PdfPCell c13 = new PdfPCell(new Phrase("Kaina"));
        PdfPCell c14 = new PdfPCell(new Phrase("Parduota"));
        PdfPCell c15 = new PdfPCell(new Phrase("Suma"));

        table.addCell(c9);
        table.addCell(c10);
        table.addCell(c11);
        table.addCell(c12);
        table.addCell(c13);
        table.addCell(c14);
        table.addCell(c15);

        for(int i = 0; i<productsList.size(); i++) {
            c9 = new PdfPCell(new Phrase(productsList.get(i).getProduct().getGroupName(), new Font(bfComic, 12)));
            c10 = new PdfPCell(new Phrase(productsList.get(i).getProduct().getName(), new Font(bfComic, 12)));
            c11 = new PdfPCell(new Phrase(productsList.get(i).getProduct().getColor(), new Font(bfComic, 12)));
            c12 = new PdfPCell(new Phrase(productsList.get(i).getProduct().getMeasurement()));
            c13 = new PdfPCell(new Phrase(String.valueOf(productsList.get(i).getProduct().getSellCostToString())));
            c14 = new PdfPCell(new Phrase(String.valueOf(productsList.get(i).getQuantity())));
            c15 = new PdfPCell(new Phrase(df.format(productsList.get(i).getProduct().getSellCost() * productsList.get(i).getQuantity()) + "€"));

            table.addCell(c9);
            table.addCell(c10);
            table.addCell(c11);
            table.addCell(c12);
            table.addCell(c13);
            table.addCell(c14);
            table.addCell(c15);
        }
        document.add(table);
        document.close();
        PopupOperations.alertMessage("Ataskaita sukurta");
    }

    public List<Have> getSoldProducts (String startDate, String endDate) {
        List<String> filters = new ArrayList<>();
        List<Orders> orderList;
        List<Have> haveList = new ArrayList<>();
        List<Have> haveListJoin = new ArrayList<>();

        filters.add("");
        filters.add(startDate);
        filters.add(endDate);
        filters.add("");
        filters.add("");
        filters.add("");
        filters.add("");
        filters.add("");
        filters.add("");

        orderList = OrdersOperations.findOrderByFilters(filters);

        orderList.forEach(order -> haveList.addAll(order.getHave()));

        for (Have have : haveList) {

            if (haveListJoin.stream().anyMatch(hl -> hl.getProduct() == have.getProduct())) {

            }
            else {

                Long productId = have.getProduct().getId();
                List<Have> tempList = haveList.stream().filter(h -> Objects.equals(h.getProduct().getId(), productId)).toList();

                Have tempHave = new Have(tempList.get(0).getProduct(), tempList.get(0).getOrder(), 0);
                for (Have have1 : tempList) {
                    tempHave.setQuantity(have1.getQuantity() + tempHave.getQuantity());
                }
                haveListJoin.add(tempHave);
            }
        }

        if (Objects.equals(cmGroups.getValue(), "Visi")) {
            return haveListJoin;
        }
        else {
            List<Have> filteredByGroup = new ArrayList<>();
            filteredByGroup = haveListJoin.stream().filter(have -> Objects.equals(have.getProduct().getGroupName(), cmGroups.getValue())).collect(Collectors.toList());
            return filteredByGroup;

        }
    }
}
