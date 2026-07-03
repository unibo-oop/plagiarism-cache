package it.unibo.coinquify.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.unibo.coinquify.shoppinglist.model.Item;
import it.unibo.coinquify.shoppinglist.model.ItemImpl;

/**
 * Read and write Items.
 */
public final class ItemFile implements Serializable {

    private static final long serialVersionUID = 608479737266128134L;

    private ItemFile() {
    }

    /**
     * @return List<Item>
     * @throws IOException 
     */
    public static List<Item> read() throws IOException {
        String i = " ", q = "0";
        final List<Item> res = new ArrayList<>();

        try (BufferedReader out = new BufferedReader(new FileReader(FilePathsConstants.SHOPPING_LIST))) {

            for (i = out.readLine(); i != null; i = out.readLine()) {
                q = out.readLine();
                res.add(new ItemImpl(i, q));
            }

        } catch (FileNotFoundException e) {
            return res;
        }
        return res;
    }

    /**
     * @param listItem 
     */
    public static void write(final List<Item> listItem) {
        try (BufferedWriter in = new BufferedWriter(new FileWriter(FilePathsConstants.SHOPPING_LIST))) {

            for (final Item i : listItem) {
                in.write(i.toString());
                in.newLine();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
