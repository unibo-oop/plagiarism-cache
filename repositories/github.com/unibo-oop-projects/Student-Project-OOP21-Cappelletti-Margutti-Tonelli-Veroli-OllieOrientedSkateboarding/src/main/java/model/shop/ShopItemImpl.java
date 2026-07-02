package model.shop;

public class ShopItemImpl implements ShopItem {

    private final String itemName; 
    private final int price; 
    private boolean purchased; 

    public ShopItemImpl(final String itemName, final int price) {
        this.itemName = itemName; 
        this.price = price; 
        this.purchased = false; 
    }

    public final String getName() {
        return this.itemName; 
    }

    public final int getPrice() {
        return this.price; 
    }

    public final void purchase() {
        this.purchased = true; 
    }

    public final boolean isPurchased() {
        return this.purchased;
    }

}
