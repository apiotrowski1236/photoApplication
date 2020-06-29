package edu.au.cc.gallery.tools.data;

import java.sql.SQLException;
public class Postgres {

    public static UserDAO getUserDAO() throws SQLException {
	return new PostgresDAO();
    }

}
