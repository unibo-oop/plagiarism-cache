package com.biaren.sportclubmanager.corebundle.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;
import com.biaren.utility.BiarenPathHandler;
import com.biaren.utility.BiarenUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.Alert.AlertType;

/**
 * Use com.itexpdf to creates pdf.
 * @author nbrunetti
 *
 */
public class PdfCreator {
    
    /**
     * Creates a pdf with list of players for the match
     * @param fileName of file
     * @param starters list of players
     * @param reserves list of players
     */
    public static void createMatchPlayersListPdf(final String fileName, final Map<String, String> starters, 
            final Map<String, String> reserves) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(
                    BiarenPathHandler.getPDFResourcesPathString() + "\\matchplayerslist\\" + fileName + ".pdf"));
            document.open();
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 20, BaseColor.BLACK);
            Paragraph title = new Paragraph(new Phrase("Distinta gara", headerFont));
            Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.BLACK);
            final float[] columnWidths = {3, 40};
            PdfPTable tableStarters = new PdfPTable(columnWidths);
            Phrase startersHeader = new Phrase("Titolari: ", headerFont);
            PdfPTable tableReserves = new PdfPTable(columnWidths);
            Phrase reservesHeader = new Phrase("A Disposizione: ", headerFont);
            starters.entrySet().forEach(e -> {
                PdfPCell c1 = new PdfPCell(new Phrase(e.getKey(), cellFont));
                c1.setFixedHeight(20);
                c1.setVerticalAlignment(Element.ALIGN_LEFT);
                PdfPCell c2 = new PdfPCell(new Phrase(e.getValue(), cellFont));
                c2.setFixedHeight(20);
                c2.setVerticalAlignment(Element.ALIGN_LEFT);
                tableStarters.addCell(c1);
                tableStarters.addCell(c2);
            });
            
            reserves.entrySet().forEach(e -> {
                PdfPCell c1 = new PdfPCell(new Phrase(e.getKey(), cellFont));
                c1.setFixedHeight(20);
                c1.setVerticalAlignment(Element.ALIGN_LEFT);
                PdfPCell c2 = new PdfPCell(new Phrase(e.getValue(), cellFont));
                c2.setFixedHeight(20);
                c2.setVerticalAlignment(Element.ALIGN_LEFT);
                tableReserves.addCell(c1);
                tableReserves.addCell(c2);
            });
            document.add(title);
            document.add(startersHeader);
            document.add(tableStarters);
            document.add(reservesHeader);
            document.add(tableReserves);
            document.close();
            BiarenUtil.showBasicAlert(AlertType.INFORMATION, "PDF creato con successo", "", "");
        } catch (FileNotFoundException | DocumentException e) {
            BiarenUtil.showBasicAlert(AlertType.ERROR, "PDF non creato", "", "");
        }
    }
}
