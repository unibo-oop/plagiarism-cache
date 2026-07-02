package modelimpl.manageaccounts;

import java.util.Set;

import model.manageaccounts.AccountModel;
import utilities.Account;

/**
 * Implements Account model.
 */
public class AccountModelImpl implements AccountModel {
    private final Set<Account> collect;
    private Account accountLogged;

    /**
     * Constructor for the Account Model.
     * @param setAccount that is set of account
     */
    public AccountModelImpl(final Set<Account> setAccount) {
        this.collect = setAccount; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAccount(final Account newAccount) {
        this.collect.add(newAccount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAccount(final Account oldAccount) {
        this.collect.remove(oldAccount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Account> getAccounts() {
        return this.collect;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Account " + collect;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account getAccountLogged() {
        return this.accountLogged;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAccountLogged(final Account accountLogged) {
        this.accountLogged = accountLogged;
    }

}
