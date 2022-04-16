package de.zmanuu.dev.utils.hooks;

import org.bukkit.entity.Player;

import java.sql.*;

public class MySQLAPI {

    private Connection connection;

    public MySQLAPI() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rpg",
                    "Manuel" , "FantasyPixel.mysql.Manuel");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            System.out.println("\n \n");
            e.printStackTrace();
        }
        System.out.println("Connection zur Datenbank wurde erstellt!");
        printStar();
    }
    public Connection getConnection() {
        return connection;
    }

    public String get(String table, Player player, int row) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM `"+table+"` WHERE `uuid` = \"" +
                    player.getUniqueId().toString()+"\"");
            if(result.next())
                return result.getString(row);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean set(String table, Player player, String row, String value) {
        try {
            Statement statement = connection.createStatement();
            return statement.execute("UPDATE `"+table+"` SET `"+row+"` = \""+value+"\" WHERE `uuid` = \""+
                    player.getUniqueId().toString()+"\"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void printStar() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM leben");
            while(resultSet.next()) {
                System.out.println("[ "+resultSet.getString(1)+" | "+resultSet.getString(2)+" | "+
                        resultSet.getString(3)+" ]\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
