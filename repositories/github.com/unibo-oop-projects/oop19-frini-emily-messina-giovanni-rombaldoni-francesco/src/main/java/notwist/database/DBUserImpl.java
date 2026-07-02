package notwist.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import javax.swing.JOptionPane;

import notwist.base.User;

/**
 * Class for manage interaction with database from Users
 */
public class DBUserImpl extends DBManagerImpl implements DBUser {

	private ResultSet rs = null;
	private String query;

	// cambiare ed ottimizzare query con Relax
	public boolean existUser(final String email, final String username) {

		try {
			String query = "select * from UTENTE where email='" + this.Crypt(email) + "' and nome='" + username + "'";
			rs = open().executeQuery(query);
			if (rs.next() && rs.getString("email").contentEquals(email)
					|| rs.getString("nome").contentEquals(username)) {
				System.out.println("User exist! (" + email + ")");
				return true;
			} else
				System.out.println("email is not registered yet!");
		} catch (Exception e) {
			System.out.println("User doesn't exist!" + e);
		} finally {
			close();
		}

		return false;
	}

	public boolean register(final String user, final String password, final String email, final boolean isModerator) {
		if (existUser(email, user)) {
			JOptionPane.showMessageDialog(null, "Is still registered with this email \nUser: " + user);
			return false;
		}

		try {
			open();
			PreparedStatement prepared = super.getConn()
					.prepareStatement("insert into UTENTE (nome, password, email,isModeratore) values (?,?,?,?)");
			prepared.setString(1, user);
			prepared.setString(2, this.Crypt(password));
			prepared.setString(3, this.Crypt(email));
			prepared.setBoolean(4, isModerator);

			prepared.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("\nError while adding new user " + e);
			return false;
		} finally {
			close();
		}
	}

	// cambiare ed ottimizzare query con Relax
	public Optional<User> login(final String email, final String password) {

		User user = null;
		try {
			query = "select * from UTENTE where email= '" + this.Crypt(email) + "'";
			rs = open().executeQuery(query);
			if (rs.next() && rs.getString("email").contentEquals(this.Crypt(email))
					&& rs.getString("password").contentEquals(this.Crypt(password))) {
				user = new User(rs.getInt("idUser"), rs.getString("nome"), this.Decrypt(rs.getString("password")),
						this.Decrypt(rs.getString("email")), rs.getBoolean("isModeratore"));
				System.out.println("Welcome " + user.getUsername() + "  :)");

			}
		} catch (Exception e) {
			System.out.println("User doesn't exist!\n" + e);

		} finally {
			close();
		}
		if (user != null)
			return Optional.of(user);
		else
			return Optional.empty();

	}

	@Override
	public Optional<User> getUserFromId(Integer id) {
		User user = null;

		try {
			query = "SELECT * FROM UTENTE WHERE idUser=" + id;
			rs = open().executeQuery(query);
			if (rs.next())
				user = new User(rs.getInt("idUser"), rs.getString("nome"), this.Decrypt(rs.getString("password")),
						this.Decrypt(rs.getString("email")), rs.getBoolean("isModeratore"));

		} catch (Exception e) {
			System.out.println("Error while get User from Id\n" + e);
		} finally {
			close();
		}
		if (user != null)
			return Optional.of(user);
		else
			return Optional.empty();
	}

}
