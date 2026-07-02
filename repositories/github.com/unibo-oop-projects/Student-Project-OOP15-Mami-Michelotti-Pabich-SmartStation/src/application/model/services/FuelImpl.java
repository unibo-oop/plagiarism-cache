package application.model.services;

import javafx.scene.paint.Color;

/**
 * Implements the Fuel interface.
 * @author Alessandro Mami
 * 
 */
public class FuelImpl implements Fuel {
    
    /** 
     * Pump's attributes declaration.
     */
    private String name;
    private int price;
    private int wholesalePrice;
    private Color color;
    
    /**
     * Constructor for the FuelImpl that creates every fuel.
     * @param nm fuel's name.
     * @param prc fuel's price.
     * @param wPrice fuel's wholesale price.
     * @param clr color of the fuel.
     */
    public FuelImpl(final String nm, final int prc, final int wPrice, final Color clr) {
        this.name = nm;
        this.price = prc;
        this.wholesalePrice = wPrice;
        this.color = clr;
    }
    
    //GETTERS AND SETTERS OF MAIN ATTRIBUTES
    @Override
    public String getName() {
    	return this.name;
    }
    
    @Override
    public void setName(final String nm) {
    	this.name = nm;	
    }
    
    @Override
    public int getPrice() {
    	return this.price;
    }
    
    @Override
    public void setPrice(final int prc) {
    	this.price = prc;
    }
    
    @Override
    public int getWholeSalePrice() {
    	return this.wholesalePrice;
    }
    
    @Override
    public void setWholeSalePrice(final int wholeSalePrice) {
    	this.wholesalePrice = wholeSalePrice;	
    }
    
    @Override
    public Color getColor() {
    	return this.color;
    }
    
    @Override
    public void setColor(final Color clr) {
    	this.color = clr;		
    }
}
