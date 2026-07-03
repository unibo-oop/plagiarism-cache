package hotelmaster.db.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Manages the interaction with the database when working with data.
 */
public class QueryManagerImpl implements QueryManager {

    private Statement stmt;
    private PreparedStatement pstmt;
    private final Connection conn = DatabaseConnectionImpl.getSingleton().getConnection();

    @Override
    public QueryManager integer(final int index, final int value) {
        try {
            this.pstmt.setInt(index, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public QueryManager price(final int index, final double value) {
        try {
            this.pstmt.setDouble(index, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public QueryManager string(final int index, final String value) {
        try {
            this.pstmt.setString(index, value);
        } catch (SQLException e) {
            e.getMessage();
        }
        return this;
    }

    @Override
    public QueryManager date(final int index, final LocalDate date) {
        final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.pstmt.setString(index, format.format(date));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public void update() throws SQLException {
        this.pstmt.executeUpdate();
        this.pstmt.close();
    }

    @Override
    public ResultSet selectPrepared() {
        ResultSet rs = null;
        try {
            rs = this.pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public void close() {
        try {
            if (this.stmt != null && !this.stmt.isClosed()) {
                try {
                    this.stmt.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        try {
            if (this.pstmt != null && !this.pstmt.isClosed()) {
                try {
                    this.pstmt.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        } catch (SQLException e) {
            e.getMessage();
        }

    }

    @Override
    public ResultSet selectNotPrepared(final String query) {
        ResultSet rs = null;
        try {
            rs = this.stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public QueryManager prepareQuery(final String query) throws SQLException {
        try {
            this.pstmt = this.conn.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public QueryManager createQuery() {
        try {
            this.stmt = this.conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public void delete() throws SQLException {
        this.pstmt.execute();
        this.pstmt.close();

    }

    @Override
    public void createTable(final String query) {
        try {
            this.stmt = this.conn.createStatement();
            this.stmt.execute(query);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
