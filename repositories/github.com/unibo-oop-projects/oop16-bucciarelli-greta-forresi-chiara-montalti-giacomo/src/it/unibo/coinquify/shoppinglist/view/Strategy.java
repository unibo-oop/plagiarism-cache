package it.unibo.coinquify.shoppinglist.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.JCheckBox;
import com.itextpdf.text.DocumentException;

import it.unibo.coinquify.shoppinglist.model.Item;

/**
 *
 *
 */
public interface Strategy {

    /**
     * @param lCheck 
     * @param lItem 
     */
    void refresh(Map<JCheckBox, Integer> lCheck, List<Item> lItem);

    /**
     * @throws FileNotFoundException 
     * @throws DocumentException 
     * @throws IOException 
     */
    void createPDF() throws FileNotFoundException, DocumentException, IOException;
}
