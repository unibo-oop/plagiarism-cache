package hotelmaster.database.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.exceptions.AccountException;

/**
 * Manages the accounts of administrator and secretaries.
 */
public class AccountManagerImpl implements AccountManager {

    private final QueryManager manager;

    /**
     * 
     */
    public AccountManagerImpl() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    public void addUserAccount(final String username, final String password) throws AccountException {
        final String query = "INSERT INTO Account (tipo, username, password) VALUES (?,?,?)";
        try {
            manager.prepareQuery(query).integer(1, 1)
                                       .string(2, username)
                                       .string(3, password)
                                       .update();
        } catch (SQLException e) {
            throw new AccountException("The username already exists");
        }
    }

    @Override
    public void removeUserAccount(final String username)  {
        final String query = "DELETE FROM Account WHERE username = ? AND tipo = 1";
        try {
            manager.prepareQuery(query).string(1, username).delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<String> getAccounts() {
        final String query = "SELECT username FROM Account WHERE tipo = 1";
        ResultSet rs;
        Set<String> users = new HashSet<>();
        try {
            rs = manager.createQuery().selectNotPrepared(query);
            while (rs.next()) {
                users.add(rs.getString(1));
            }
            rs.close();
            manager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (users.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(users));
    }

}
