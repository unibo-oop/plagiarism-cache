package frogger.model.implementations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import frogger.controller.ShopController;
import frogger.model.interfaces.PurchasableObject;
import frogger.model.interfaces.PurchasableObjectFactory;
import frogger.model.interfaces.Shop;

/**
 * Implementation of the {@link Shop} interface.
 * Handles the logic for managing purchasable objects and shop persistence.
 */
public class ShopImpl implements Shop {

    /** Logger for this class. */
    private static final Logger LOGGER = Logger.getLogger(ShopController.class.getName());
    /** Name of the default shop resource file (read-only). */
    private static final String FILE_NAME = "/shop.txt";
    /** List of purchasable objects available in the shop. */
    private final List<PurchasableObject> purchasableObjects;
    /** Factory for creating purchasable objects. */
    private final PurchasableObjectFactory factory;

    /**
     * Constructs a new ShopImpl, initializing the purchasable objects list and factory.
     */
    public ShopImpl() {
        this.purchasableObjects = new LinkedList<>();
        this.factory = new PurchasableObjectFactoryImpl();
    }

    /**
     * {@inheritDoc}
     * Returns an unmodifiable copy of the list of purchasable objects.
     */
    @Override
    public List<PurchasableObject> getPurchasableObjects() {
        return List.copyOf(this.purchasableObjects);
    }

    /**
     * {@inheritDoc}
     * Initializes the shop by loading purchasable objects from the save file or the default resource.
     */
    @Override
    public void init() {
        final InputStream is =  ShopController.class.getResourceAsStream(FILE_NAME);
        try (BufferedReader r = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line = r.readLine();
            while (line != null) {
                final String[] values = line.split(" ");
                if ("Skin".equals(values[0])) {
                    this.purchasableObjects.add(factory.createSkin(
                        Integer.parseInt(values[1]), 
                        values[2],
                        Boolean.parseBoolean(values[3])
                    ));
                }
                line = r.readLine();
            }
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading the shop file", e);
        }
    }
}
