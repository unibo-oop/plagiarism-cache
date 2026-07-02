package model.gui.activity;

import java.util.LinkedList;
import java.util.List;

import controller.EnvironmentControllerImpl;
import view.controller.ViewControllerImpl;
import view.model.activity.ActivityType;
import view.model.activity.ViewActivityImpl;

public class ActivityInsertion {

    private final List<Square> shop;
    private final List<Square> restaurant;
    private final List<Square> adultFair;
    private final List<Square> babyFair;
    private final EnvironmentControllerImpl controller;

    /**
     * 
     * @param controller constructor
     */
    public ActivityInsertion(final EnvironmentControllerImpl controller) {

        this.shop = new LinkedList<>();
        this.restaurant = new LinkedList<>();
        this.adultFair = new LinkedList<>();
        this.babyFair = new LinkedList<>();
        this.controller = controller;

    }

    /**
     * Add objects in their list.
     */
    public final void listActivity() {
        int distanceAdultFair = 0;
        int distanceBabyFair = 0;
        int distanceShop = 0;
        int distanceRestaurant = 0;

        for (final ViewActivityImpl a : this.controller.getActivityList()) {
            final ActivityType type = a.getActivityType();
            switch (type) {
            case BABYFAIR: 
                babyFair.add(DesignActivity.createBabyFair(20, 9, 10, 10, a.getName(), distanceBabyFair));
                distanceBabyFair += 200;
                break;
            case FAIR: 
                adultFair.add(DesignActivity.createAdultFair(20, 9, 2, 9, a.getName(), distanceAdultFair));
                distanceAdultFair += 200;
                break;
            case SHOP: 
                shop.add(DesignActivity.createShop(20, 9, 0, 10, a.getName(), distanceShop));
                distanceShop += 200;
                break;
            case REST: 
                restaurant.add(DesignActivity.createRestaurant(20, 9, 10, 10, a.getName(), distanceRestaurant));
                distanceRestaurant += 200;
                break;
            default:
                break;

            }

        }

    }

    /**
     * 
     * @return list of shops
     */
    public final List<Square> getShop() {
        return shop;
    }

    /**
     * 
     * @return list of restaurants
     */
    public final List<Square> getRestaurant() {
        return restaurant;
    }

    /**
     * 
     * @return list of adult fairs
     */
    public final List<Square> getAdultFair() {
        return adultFair;
    }

    /**
     * 
     * @return list of baby fairs
     */
    public final List<Square> getBabyFair() {
        return babyFair;
    }

}




