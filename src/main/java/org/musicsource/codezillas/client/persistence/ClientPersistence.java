package org.musicsource.codezillas.client.persistence;

import java.sql.*;

public class ClientPersistence {



    public ClientPersistence() {

    }

    public static Connection initConenction() {
        Connection connection = null;

        try {

            String urlConnection = "jdbc:sqlite:/Users/codecadet/Desktop/client.db";
            connection = DriverManager.getConnection(urlConnection);

                //System.out.println("ggwp!!!!");

            return connection;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static void createTable() {

        try {

            Connection connection = initConenction();

            String sql = "CREATE TABLE IF NOT EXISTS tracks (\n"
                    + "	id integer PRIMARY KEY,\n"
                    + "	name varchar(255) NOT NULL UNIQUE\n"
                    + ");";


            Statement stmt = null;

            if (connection != null) {

                stmt = connection.createStatement();
                stmt.execute(sql);
            }

        }catch (SQLException ex){

            System.out.println(ex.getMessage());
        }
    }

    public static void insertData(String data){

        try {

            Connection connection = initConenction();

            String sql = "INSERT INTO tracks(name) VALUES(?)";

            if (connection != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, data);
                preparedStatement.execute();
            }


        }catch (SQLException ex){
            //System.out.println(ex.getMessage());
        }
    }

    public static void getData(){

        try {

            Connection connection = initConenction();

            String sql = "SELECT * FROM tracks";

            if (connection != null) {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()){
                    System.out.println(rs.getString("name"));
                }
            }


        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

}
