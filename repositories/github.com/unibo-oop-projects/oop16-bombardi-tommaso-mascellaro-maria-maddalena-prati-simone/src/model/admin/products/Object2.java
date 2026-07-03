package model.admin.products;

/**
 * Enumeration with all products available for rent or storage operations.
 */
public enum Object2 {

    /**
     * Skis.
     */
    SKIS,
    /**
     * Ski boots.
     */
    SKI_BOOTS,
    /**
     * Ski poles.
     */
    SKI_POLES,
    /**
     * Snowboard.
     */
    SNOWBOARD,
    /**
     * Snowboard boots.
     */
    SNOWBOARD_BOOTS,
    /**
     * Helmet.
     */
    HELMET,
    /**
     * Snow mask.
     */
    SNOW_MASK;

    private static final double SKIS_R_PRICE = 25.00;
    private static final double SKI_BOOTS_R_PRICE = 10.00;
    private static final double SKI_POLES_R_PRICE = 3.00;
    private static final double SNOWBOARD_R_PRICE = 20.00;
    private static final double SNOWBOARD_BOOTS_R_PRICE = 8.00;
    private static final double HELMET_R_PRICE = 5.00;
    private static final double SNOW_MASK_R_PRICE = 3.00;

    private static final double SKIS_S_PRICE = 5.00;
    private static final double SKI_BOOTS_S_PRICE = 2.50;
    private static final double SKI_POLES_S_PRICE = 1.50;
    private static final double SNOWBOARD_S_PRICE = 5.00;
    private static final double SNOWBOARD_BOOTS_S_PRICE = 2.50;
    private static final double HELMET_S_PRICE = 1.00;
    private static final double SNOW_MASK_S_PRICE = 0.50;

    /**
     * Get description.
     * 
     * @return a string which represents the description of the product
     */
    public String getDescription() {
        switch (this) {
            case SKIS:              return Object1.SKIS.getDescription();
            case SKI_BOOTS:         return Object1.SKI_BOOTS.getDescription();
            case SKI_POLES:         return Object1.SKI_POLES.getDescription();
            case SNOWBOARD:         return Object1.SNOWBOARD.getDescription();
            case SNOWBOARD_BOOTS:   return Object1.SNOWBOARD_BOOTS.getDescription();
            case HELMET:            return Object1.HELMET.getDescription();
            default:                return Object1.SNOW_MASK.getDescription();
        }
    }

    /**
     * Get image.
     * 
     * @return a string which represents the path of product's image
     */
    public String getImage() {
        switch (this) {
            case SKIS:              return Object1.SKIS.getImage();
            case SKI_BOOTS:         return Object1.SKI_BOOTS.getImage();
            case SKI_POLES:         return Object1.SKI_POLES.getImage();
            case SNOWBOARD:         return Object1.SNOWBOARD.getImage();
            case SNOWBOARD_BOOTS:   return Object1.SNOWBOARD_BOOTS.getImage();
            case HELMET:            return Object1.HELMET.getImage();
            default:                return Object1.SNOW_MASK.getImage();
        }
    }

    /**
     * Get rent price.
     * 
     * @return a double which represents the rent price of a single object, for one day in mid season
     */
    public double getRentPrice() {
        switch (this) {
            case SKIS:              return SKIS_R_PRICE;
            case SKI_BOOTS:         return SKI_BOOTS_R_PRICE;
            case SKI_POLES:         return SKI_POLES_R_PRICE;
            case SNOWBOARD:         return SNOWBOARD_R_PRICE;
            case SNOWBOARD_BOOTS:   return SNOWBOARD_BOOTS_R_PRICE;
            case HELMET:            return HELMET_R_PRICE;
            default:                return SNOW_MASK_R_PRICE;
        }
    }

    /**
     * Get storage price.
     * 
     * @return a double which represents the storage price of a single object, for one day
     */
    public double getStoragePrice() {
        switch (this) {
            case SKIS:              return SKIS_S_PRICE;
            case SKI_BOOTS:         return SKI_BOOTS_S_PRICE;
            case SKI_POLES:         return SKI_POLES_S_PRICE;
            case SNOWBOARD:         return SNOWBOARD_S_PRICE;
            case SNOWBOARD_BOOTS:   return SNOWBOARD_BOOTS_S_PRICE;
            case HELMET:            return HELMET_S_PRICE;
            default:                return SNOW_MASK_S_PRICE;
        }
    }

}
