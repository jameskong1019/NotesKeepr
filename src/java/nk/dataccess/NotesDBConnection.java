package nk.dataccess;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class NotesDBConnection {

    private static NotesDBConnection pool = null;
    private static DataSource dataSource = null;

    private NotesDBConnection() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("jdbc/NotesDB");
        } catch (NamingException e) {
            System.out.println(e);
        }
    }

    public static synchronized NotesDBConnection getInstance() {
        if (pool == null) {
            pool = new NotesDBConnection();
        }
        return pool;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
