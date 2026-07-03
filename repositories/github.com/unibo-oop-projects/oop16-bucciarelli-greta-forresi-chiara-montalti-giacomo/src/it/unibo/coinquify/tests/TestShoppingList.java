package it.unibo.coinquify.tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import it.unibo.coinquify.shoppinglist.controller.ShoppingListController;
import it.unibo.coinquify.shoppinglist.controller.ShoppingListControllerImpl;
import it.unibo.coinquify.shoppinglist.model.Item;
import it.unibo.coinquify.shoppinglist.model.ItemImpl;
import it.unibo.coinquify.shoppinglist.model.ShoppingList;
import it.unibo.coinquify.shoppinglist.model.ShoppingListImpl;

/**
 * Test the Shopping List model and controller.
 *
 */
public class TestShoppingList {

    private static final String NO_EXCEPTION = "Shouldn't have thrown an exception";
    private static final String ITEM = "Pane";
    private static final String ITEM1 = "Biscotti cacao";
    private static final String QUANT = "6";
    private static final String QUANT1 = "3";
    private static final String QUANT_MINORE = "-6";

    /**
     * 
     */
    @Test
    public void testSingleItem() {
        try {

            final Item itemPane = new ItemImpl(ITEM, QUANT);
            final Item itemBiscotti = new ItemImpl(ITEM1, QUANT1);

            assertTrue(itemPane.getItem().equals(ITEM));
            assertFalse(itemPane.getQuantity().equals(QUANT_MINORE));
            assertTrue(itemBiscotti.getItem().equals(ITEM1));

            assertEquals(itemPane.getItem(), "Pane");
            assertNotEquals(itemBiscotti.getItem(), itemPane.getItem());
            assertNotEquals(itemPane.getQuantity(), "-6");

        } catch (Exception e) {
            Assert.fail(NO_EXCEPTION);
        }

    }

    /**
     * @throws IOException 
     * 
     */
    @Test
    public void testShoppingList() throws IOException {

        try {
            final List<Item> listaProva = new ArrayList<>();
            final ShoppingList shoppingList = new ShoppingListImpl();
            final ShoppingList shoppingListPopulate = new ShoppingListImpl(listaProva);
            final Item itemBiscotti = new ItemImpl(ITEM1, QUANT1);
            final ShoppingListController controller = new ShoppingListControllerImpl();

            assertTrue(shoppingList.getItems().isEmpty());
            assertTrue(shoppingListPopulate.getItems().isEmpty());

            shoppingList.addItem(new ItemImpl("Mele Verdi", "9"));
            assertFalse(shoppingList.getItems().isEmpty());

            shoppingListPopulate.addItem(new ItemImpl("Mele Verdi", "9"));
            assertFalse(shoppingListPopulate.getItems().isEmpty());

            controller.addItem(itemBiscotti, shoppingList);
            assertFalse(shoppingList.getItems().isEmpty());

            controller.removeItem(itemBiscotti, shoppingList.getItems());
            assertTrue(shoppingList.getItems().isEmpty());


        } catch (Exception e) {
            Assert.fail(NO_EXCEPTION);
        }
    }

}
