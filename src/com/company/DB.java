package com.company;

import java.sql.*;


public class DB {

    private final String HOST = "localhost";
    private final String PORT = "3306";
    private final String DB_NAME = "java_db";
    private final String LOGIN = "root";
    private final String PASS = "root";

    private Connection dbConn;

    private Connection getDbConn() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return dbConn;
    }

    public void isConnected() throws SQLException, ClassNotFoundException {
        dbConn = getDbConn();
        System.out.println(dbConn.isValid(1000));
    }
    public void createTable(String tableName) throws SQLException, ClassNotFoundException {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName
                + " (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), "
                + "password VARCHAR(100))" + " ENGINE=MYISAM;";

        Statement statement = getDbConn().createStatement();
        statement.executeUpdate(sql);
    }

    public void insertArticle(String title, String text, String date, String author) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO articles (title, text, date, author) VALUES (?,?,?,?)";

        PreparedStatement preparedStatement = getDbConn().prepareStatement(sql);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, text);
        preparedStatement.setString(3, date);
        preparedStatement.setString(4, author);

        preparedStatement.executeUpdate();
    }

    public void getArticles(String table) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM " + table;

        Statement statement = getDbConn().createStatement();
        ResultSet res = statement.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString("title"));
            System.out.println(res.getString("text"));
        }
    }

}
