package controller.manageaccounts;

import java.util.Set;

import controller.CinemaController;
import utilities.Account;

public interface AccountsController {
	
        /**
         * Add new Account.
         * @param newAccount to add
         */
	void addAccount(Account newAccount);
	
	/**
	 * Delete specific Account. 
	 * @param oldAccount to remove
	 */
	void deleteAccount(Account oldAccount);
	
	/**
	 * Recover all account from account's set.
	 * @return set of Accounts
	 */
	Set<Account> getAccounts(); 

	/**
	 * Show menu view.
	 */
	void showMenu();
	
	/**
	 * Show view to add a new account.
	 */
	void showRegistrationAccountView();
	
	/**
	 * Show view with all account.
	 */
        void showManagementAccountView();

        /**
         * Show login view.
         */
        void showLoginAccounView();

        /**
         * Set Cinema Controller.
         * @param cinemaController controller to set
         */
        void setCinemaController(CinemaController cinemaController);

        /**
         * Set Account logged in that time.
         * @param accountLogged in that time
         */
        void setAccountLogged(Account accountLogged);

        /**
         * Get Account logged in that time.
         * @return account logged in that time
         */
        Account getAccountLogged();
}
