package model.manageaccounts;

import java.util.Set;

import utilities.Account;

public interface AccountModel {

    /**
     * Add new Account.
     * @param newAccount to add
     */
    void addAccount(Account newAccount);

    /**
     * Delete specific Account. 
     * @param oldAccount to remove
     */
    void removeAccount(Account oldAccount);

    /**
     * Recover all account from account's set.
     * @return set of Accounts
     */
    Set<Account> getAccounts();

    /**
     * Get Account logged in that time.
     * @return account
     */
    Account getAccountLogged();

    /**
     * Set Account logged in that time.
     * @param accountLogged in that time
     */
    void setAccountLogged(Account accountLogged);
}
