package model.gui.activity;

import java.awt.Color;
import java.awt.Toolkit;

import view.controller.ViewControllerImpl;

import java.awt.Dimension;

public class DesignActivity {

    private static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private static int WIDTH = (int) SCREEN.getWidth();
    private static int HEIGHT = (int) SCREEN.getHeight();

    /**
     * 
     * @param wh width
     * @param ht height
     * @param x coordinate x
     * @param y coordinate y
     * @param name activity name
     * @param distance activity distance
     * @return object shop 
     */
    public static Square createShop(final int wh, final int ht, final int x, final int y, final String name, final int distance) {
        final Square shop = new Square();
        shop.setWidth(WIDTH / wh);
        shop.setHeight(HEIGHT / ht);
        shop.setX(1);
        shop.setY(WIDTH  / y + 1 + distance);
        shop.setColor(new Color(255, 160, 122));
        shop.setName(name);
        return shop;
    }

    /**
     * 
     * @param wh width
     * @param ht height
     * @param x coordinate x
     * @param y coordinate y 
     * @param name activity name
     * @param distance activity distance
     * @return object restaurant 
     */
    public static Square createRestaurant(final int wh, final int ht, final int x, final int y, final String name, final int distance) {
        final Square restaurant = new Square();
        restaurant.setWidth(HEIGHT / ht);
        restaurant.setHeight(WIDTH  / wh);
        restaurant.setX(WIDTH / x + distance);
        restaurant.setY(HEIGHT - (WIDTH / wh));
        restaurant.setColor(new Color(239, 127, 127));
        restaurant.setName(name);
        return restaurant;
    }

    /**
     * 
     * @param wh width
     * @param ht height
     * @param x coordinate x
     * @param y coordinate y
     * @param name activity name
     * @param distance activity distance
     * @return object babyFair 
     */
    public static Square createBabyFair(final int wh, final int ht, final int x, final int y, final String name, final int distance) {
        final Square babyFair = new Square();
        babyFair.setWidth(HEIGHT / ht);
        babyFair.setHeight(WIDTH / wh);
        babyFair.setX(WIDTH / x + distance);
        babyFair.setY(1);
        babyFair.setColor(new Color(192, 0, 250));
        babyFair.setName(name);
        return babyFair;
    }

    /**
     * 
     * @param wh width 
     * @param ht height
     * @param x coordinate x
     * @param y coordinate y
     * @param name activity name
     * @param distance activity distance
     * @return object adultFair
     */
    public static Square createAdultFair(final int wh, final int ht, final int x, final int y, final String name, final int distance) {
        final Square adultFair = new Square();
        adultFair.setWidth(HEIGHT / ht);
        adultFair.setHeight(WIDTH / wh);
        adultFair.setX(WIDTH / x + distance);
        adultFair.setY(1);
        adultFair.setColor(new Color(15, 226, 0));
        adultFair.setName(name);
        return adultFair;
    }

}
