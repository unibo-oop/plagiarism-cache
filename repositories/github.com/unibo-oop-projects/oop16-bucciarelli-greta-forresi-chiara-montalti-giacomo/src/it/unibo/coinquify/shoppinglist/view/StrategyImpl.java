package it.unibo.coinquify.shoppinglist.view;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JCheckBox;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import it.unibo.coinquify.file.FilePathsConstants;
import it.unibo.coinquify.shoppinglist.controller.ShoppingListController;
import it.unibo.coinquify.shoppinglist.controller.ShoppingListControllerImpl;
import it.unibo.coinquify.shoppinglist.model.Item;
import it.unibo.coinquify.utils.Messages;

/**
 *
 *
 */
public class StrategyImpl implements Strategy {
    private final ShoppingListController controller;
    private static final int FONT_1 = 11;
    private static final int FONT_2 = 14;

    /**
     * 
     */
    public StrategyImpl() {
        this.controller = new ShoppingListControllerImpl();
    }


    /* (non-Javadoc)
     * @see it.unibo.coinquify.shoppinglist.view.Strategy#refresh(java.util.Map, java.util.List)
     */
    @Override
    public void refresh(final Map<JCheckBox, Integer> lCheck, final List<Item> lItem) {

       final Set<JCheckBox> res = new HashSet<>(lCheck.keySet());
       final  List<Item> tmp = new ArrayList<>(lItem);
        for (final JCheckBox c : res) {

            if (c.isSelected()) {

                lCheck.remove(c);

                for (final Item i : tmp) {
                    if (i.getItem().equals(c.getText())) {
                        lItem.remove(i);
                        this.controller.removeItem(i, lItem);

                    }
                }

            }

            try {

                final FileWriter fw = new FileWriter(FilePathsConstants.SHOPPING_LIST);

                fw.close();
            } catch (IOException e) {

                e.printStackTrace();
            }

        }

    }

    /* (non-Javadoc)
     * @see it.unibo.coinquify.shoppinglist.view.Strategy#createPDF()
     *
     */
    @Override
    public void createPDF() throws DocumentException, IOException {
        String l = " ";
        final Document documentPDF = new Document(PageSize.A4);
        PdfWriter.getInstance(documentPDF, new FileOutputStream(
        System.getProperty("user.home") + System.getProperty("file.separator") + "shoppinglist.pdf"));

        final BufferedReader out = new BufferedReader(new FileReader(FilePathsConstants.SHOPPING_LIST));
        documentPDF.open();

        final Font font1 = new Font();
        font1.setStyle(Font.NORMAL);
        font1.setSize(FONT_1);

        final Font font2 = new Font();
        font2.setStyle(Font.BOLD);
        font2.setSize(FONT_2);

        documentPDF.add(new Paragraph("\n"));
        final Paragraph title = new Paragraph(Messages.getMessages().getString("SHOPPING_LIST_NAME"), font2);
        title.setAlignment(Element.ALIGN_CENTER);
        documentPDF.add(title);
        documentPDF.add(new Paragraph("\n"));

        for (l = out.readLine(); l != null; l = out.readLine()) {
           final Paragraph para = new Paragraph(l + " " + out.readLine() + "\n", font1);
            para.setAlignment(Element.ALIGN_JUSTIFIED);
            documentPDF.add(para);

        }

        final Paragraph author = new Paragraph(Messages.getMessages().getString("CREATE_COINQUIFY"), font2);
        font2.setSize(10);
        author.setAlignment(Element.ALIGN_CENTER);
        documentPDF.add(new Paragraph("\n"));
        documentPDF.add(new Paragraph("\n"));
        documentPDF.add(author);
        documentPDF.close();
        out.close();
    }

}
