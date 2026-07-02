package notwist.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import notwist.base.Comments;

public class DBCommentsImpl extends DBManagerImpl implements DBComments{

	private ResultSet rs = null;
	private String query;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public Optional<List<Comments>> getAllComments(final Integer idDiscussion) {
		List<Comments> list = new LinkedList<>();

		try {
			query = "select * from COMMENT where idDiscussion= " + idDiscussion;
			rs = open().executeQuery(query);

			while (rs.next()) {
				// list.add(new CommentsImplements())
			}
		} catch (SQLException e) {
			System.out.println("Error while download comments\n" + e);
		} finally {
			close();
		}
		return Optional.of(list);
	}

	public void write(final Integer idDiscussion, final Integer idUser, final String comment) {
		try {

			query = "insert into COMMENT (idUser, idDiscussion, commento, data) values (?,?,?,?)";
			open();
			PreparedStatement prepared = super.getConn().prepareStatement(query);
			prepared.setInt(1, idUser);
			prepared.setInt(2, idDiscussion);
			prepared.setString(3, comment);
			prepared.setDate(4, java.sql.Date.valueOf(sdf.format(new Date())));

			prepared.executeUpdate();
			JOptionPane.showMessageDialog(null, "Comment uploaded successfully\nWell done!");
		} catch (Exception e) {
			System.out.println("\nError while adding new comment in discussion " + e);
		} finally {
			close();
		}
	}
}
