package model.container;

import model.AbstractValue;

/**
 * 
 * class item of shop and inventory.
 *
 */
public class Item extends AbstractValue {

    /**
     * 
     */
    private static final long serialVersionUID = 1686230290479622002L;
    private int price;
    private String url;

    /**
     * 
     * @param newName
     *            constructor for name
     * @param newValue
     *            constructor for value
     * @param newPrice
     *            constructor for price
     * @param newUrl
     *            is the new url.
     */
    public Item(final String newName, final int newValue, final int newPrice, final String newUrl) {
        super(newName, newValue);
        this.price = newPrice;
        this.url = newUrl;
    }

    /**
     * 
     * @return price
     */
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return super.toString() + "price = " + price + "url= " + url;
    }

    /**
     * 
     * @return url
     */
    public String getUrl() {
        return this.url;
    }
}
