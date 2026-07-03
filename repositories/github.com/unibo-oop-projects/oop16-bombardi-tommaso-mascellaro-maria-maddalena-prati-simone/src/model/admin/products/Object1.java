package model.admin.products;

/**
 * Enumeration with all products available for buy operations.
 */
public enum Object1 {

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
    SNOW_MASK,
    /**
     * Gloves.
     */
    GLOVES,
    /**
     * Scarf.
     */
    SCARF,
    /**
     * Snow coat.
     */
    SNOW_COAT,
    /**
     * Snow pants.
     */
    SNOW_PANTS,
    /**
     * Snow boots.
     */
    SNOW_BOOTS;

    private static final double SKIS_B_PRICE = 349.90;
    private static final double SKI_BOOTS_B_PRICE = 149.90;
    private static final double SKI_POLES_B_PRICE = 24.90;
    private static final double SNOWBOARD_B_PRICE = 249.90;
    private static final double SNOWBOARD_BOOTS_B_PRICE = 109.90;
    private static final double HELMET_B_PRICE = 69.90;
    private static final double SNOW_MASK_B_PRICE = 29.90;
    private static final double GLOVES_B_PRICE = 19.90;
    private static final double SCARF_B_PRICE = 14.90;
    private static final double SNOW_COAT_B_PRICE = 99.90;
    private static final double SNOW_PANTS_B_PRICE = 49.90;
    private static final double SNOW_BOOTS_B_PRICE = 84.90;

    /**
     * Get description.
     * 
     * @return a string which represents the description of the product
     */
    public String getDescription() {
        switch (this) {
            case SKIS:              return "Sci";
            case SKI_BOOTS:         return "Scarponi Sci";
            case SKI_POLES:         return "Racchette Sci";
            case SNOWBOARD:         return "Snowboard";
            case SNOWBOARD_BOOTS:   return "Scarponi Snow";
            case HELMET:            return "Casco";
            case SNOW_MASK:         return "Maschera";
            case GLOVES:            return "Guanti";
            case SCARF:             return "Sciarpa";
            case SNOW_COAT:         return "Giacca A Vento";
            case SNOW_PANTS:        return "Pantaloni Neve";
            default:                return "Scarponi Neve";
        }
    }

    /**
     * Get image.
     * 
     * @return a string which represents the path of product's image
     */
    public String getImage() {
        switch (this) {
            case SKIS:              return "/sci.jpg";
            case SKI_BOOTS:         return "/scarponi_sci.jpg";
            case SKI_POLES:         return "/racchette_sci.jpg";
            case SNOWBOARD:         return "/snowboard.jpg";
            case SNOWBOARD_BOOTS:   return "/scarponi_snowboard.jpg";
            case HELMET:            return "/casco.jpg";
            case SNOW_MASK:         return "/maschera.jpg";
            case GLOVES:            return "/guanti.jpg";
            case SCARF:             return "/sciarpa.jpg";
            case SNOW_COAT:         return "/giacca_vento.jpg";
            case SNOW_PANTS:        return "/pantaloni_neve.jpg";
            default:                return "/scarponi_neve.jpg";
        }
    }

    /**
     * Get buy price.
     * 
     * @return a double which represents the buy price of a single object
     */
    public double getBuyPrice() {
        switch (this) {
            case SKIS:              return SKIS_B_PRICE;
            case SKI_BOOTS:         return SKI_BOOTS_B_PRICE;
            case SKI_POLES:         return SKI_POLES_B_PRICE;
            case SNOWBOARD:         return SNOWBOARD_B_PRICE;
            case SNOWBOARD_BOOTS:   return SNOWBOARD_BOOTS_B_PRICE;
            case HELMET:            return HELMET_B_PRICE;
            case SNOW_MASK:         return SNOW_MASK_B_PRICE;
            case GLOVES:            return GLOVES_B_PRICE;
            case SCARF:             return SCARF_B_PRICE;
            case SNOW_COAT:         return SNOW_COAT_B_PRICE;
            case SNOW_PANTS:        return SNOW_PANTS_B_PRICE;
            default:                return SNOW_BOOTS_B_PRICE;
        }
    }

}
