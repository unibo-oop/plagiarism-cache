package it.unibo.coinquify.balance.model;

import java.io.Serializable;


/**
 * new Lending.
 *
 */
public class LendingImpl implements Lending, Serializable {

    private static final long serialVersionUID = 1L;
    private final String description;
    private final Object debitor;

    /**
     * New Lending.
     * 
     * @param debitor 
     * @param description 
     */
    public LendingImpl(final Object debitor, final String description) {
        this.debitor = debitor;
        this.description = description;
    }

    @Override
    public Object getDebitor() {

        return this.debitor;
    }

    @Override
    public String getDescription() {

        return this.description;
    }

}
