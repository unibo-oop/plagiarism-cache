package model.ticket;

public enum Ticket {

    /**
     * Ticket for people with age between 12 and 65 years old. 
     */
    ADULT(15),
    /**
     * Ticket for people younger then 12 years old and older than 65 year ols.
     */
    REDUCED(10),
    /**
     * Season_pass.
     */
    SEASON_PASS(30);

    private final Integer price;

/*Constructor of the class ticket*/
    Ticket(final Integer price) {
        this.price = price;
    }

/*Method that returns the price of the ticket*/
    public Integer getPrice() {
        return price;
    }
}
