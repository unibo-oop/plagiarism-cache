package view.manageaccounts;

import java.util.Set;

import controller.manageaccounts.AccountsController;
import utilities.Account;

public interface LoginAccountView {

    /**
     * Show a login view.
     */
    void show();

    /**
     * Set Account controller observer.
     * @param observer that is Account controller
     */
    void setObserver(AccountsController observer);

    /**
     * Update account.
     * @param setAccount that is a set of accounts
     */
    void updateSetAccount(Set<Account> setAccount);
}
