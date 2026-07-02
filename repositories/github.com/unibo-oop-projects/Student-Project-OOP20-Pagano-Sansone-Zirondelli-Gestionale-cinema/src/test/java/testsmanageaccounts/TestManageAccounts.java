package testsmanageaccounts;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import model.manageaccounts.AccountModel;
import modelimpl.manageaccounts.AccountModelImpl;
import utilities.Account;
import utilities.TypeAccount;
import utilitiesimpl.AccountImpl;


public class TestManageAccounts {

    @Test
    void testDeleteAccounts() {
        final Set<Account> setAccount = new HashSet<>();
        final Account a1 = new AccountImpl("Mario", "Rossi", "Ross", "qwerty", TypeAccount.OPERATOR);
        final Account a2 = new AccountImpl("Giacomo", "Pippo", "Giac", "qwerty1", TypeAccount.ADMINISTRATOR);
        final Account a3 = new AccountImpl("Piero", "Suli", "Pier", "qwerty123", TypeAccount.OPERATOR);
        setAccount.add(a1);
        setAccount.add(a2);
        setAccount.add(a3);

        final AccountModel model = new AccountModelImpl(setAccount);
        model.removeAccount(a3);
        assertTrue(model.getAccounts().containsAll(Set.of(a1, a2)));
    }

    @Test
    public void testAddAccounts() {
        final Set<Account> setAccount = new HashSet<>();
        final Account a1 = new AccountImpl("Mario", "Rossi", "Ross", "qwerty", TypeAccount.OPERATOR);
        final Account a2 = new AccountImpl("Giacomo", "Pippo", "Giac", "qwerty1", TypeAccount.ADMINISTRATOR);
        final Account a3 = new AccountImpl("Piero", "Suli", "Pier", "qwerty123", TypeAccount.OPERATOR);
        setAccount.add(a1);
        setAccount.add(a2);
        setAccount.add(a3);

        final AccountModel model = new AccountModelImpl(setAccount);
        final Account a4 = new AccountImpl("Luigi", "Costa", "Lu", "luna21", TypeAccount.ADMINISTRATOR);
        model.addAccount(a4);
        assertTrue(model.getAccounts().containsAll(Set.of(a1, a2, a3, a4)));
    }

    @Test
    public void testDeleteLastAdminAccount() {
        final Set<Account> setAccount = new HashSet<>();
        final Account a1 = new AccountImpl("Mario", "Rossi", "Ross", "qwerty", TypeAccount.ADMINISTRATOR);
        final Account a2 = new AccountImpl("Giacomo", "Pippo", "Giac", "qwerty1", TypeAccount.OPERATOR);
        setAccount.add(a1);
        setAccount.add(a2);

        final AccountModel model = new AccountModelImpl(setAccount);
        model.removeAccount(a1);
        assertFalse(model.getAccounts().containsAll(Set.of(a1, a2)));
    }
}
