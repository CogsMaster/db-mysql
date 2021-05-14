package com.company;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DB db = new DB();

        try {
            db.getArticles("articles");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
