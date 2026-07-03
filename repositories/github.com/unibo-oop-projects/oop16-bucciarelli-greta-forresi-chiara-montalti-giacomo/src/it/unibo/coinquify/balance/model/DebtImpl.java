package it.unibo.coinquify.balance.model;

import java.io.Serializable;



/**
 * new debt.
 *
 */
public class DebtImpl implements Debt, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final String description;
    private final Double quantity;
    private final Object debitor;


    /**
     * @param descriptor of the debt
     * @param debitor of the debt
     * @param quantity of the debt
     */
    public DebtImpl(final String descriptor, final Object debitor, final Double quantity) {
        this.description = descriptor;
        this.debitor = debitor;
        this.quantity = quantity;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the quantity
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * @return the debitor
     */
    public Object getDebitor() {
        return debitor;
    }


}
