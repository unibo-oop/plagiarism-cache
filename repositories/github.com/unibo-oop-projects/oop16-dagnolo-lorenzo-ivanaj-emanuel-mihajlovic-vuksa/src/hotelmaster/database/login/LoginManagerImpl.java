package hotelmaster.database.login;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.common.base.Optional;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.login.AccountLevel;

/**
 * Manages the login within the database.
 */
public class LoginManagerImpl implements LoginManager {

    private final QueryManager manager;

    /**
     * 
     */
    public LoginManagerImpl() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    public Optional<AccountLevel> logIn(final String username, final String password) {
        final String query = "SELECT tipo FROM Account WHERE username = ? AND password = ?";
        AccountLevel accountLevel = null;
        ResultSet rs = null;
        try {
            rs = manager.prepareQuery(query).string(1, username)
                                            .string(2, password)
                                            .selectPrepared();
            if (rs.next()) {
                accountLevel = AccountLevel.of(rs.getInt(1));
            }
            rs.close();
            manager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (accountLevel != null ? Optional.of(accountLevel) : Optional.absent());
    }

    @Override
    public Optional<AccountLevel> changePassword(final String username, final String password, final String newPassword) {
        Optional<AccountLevel> login = this.logIn(username, password);
        if (login.isPresent()) {
            final String query = "UPDATE Account SET password = ? WHERE username = ?";
            try {
                manager.prepareQuery(query).string(1, newPassword)
                                           .string(2, username)
                                           .update();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return login;
        } else {
            return Optional.absent();
        }
    }

}
