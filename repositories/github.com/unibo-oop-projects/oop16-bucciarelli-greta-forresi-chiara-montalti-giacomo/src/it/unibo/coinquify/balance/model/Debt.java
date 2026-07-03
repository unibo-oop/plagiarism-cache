package it.unibo.coinquify.balance.model;

/**
 *
 *
 */
public interface Debt {

    /**
     * @return the debitor
     */
    Object getDebitor();
    /**
     * @return the quantity
     */
    Double getQuantity();
    /**
     * @return the description
     */
    String getDescription();
}
