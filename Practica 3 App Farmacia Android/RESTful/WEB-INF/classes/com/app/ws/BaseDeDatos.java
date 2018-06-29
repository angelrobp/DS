package com.app.ws;
import java.sql.*;
import java.util.*;

public class BaseDeDatos
{
    private String user;
    private String pass;
    private String db_name;
    private static Connection conexion;

    public BaseDeDatos(String user, String pass, String db_name){
        this.user = user;
        this.pass = pass;
        this.db_name = db_name;
        try {
            realizarConexion();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // root "" farmacia
    public void realizarConexion() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name, user, pass);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public ResultSet realizarQuery(String peticion) throws SQLException {
        Statement stmt = conexion.createStatement();
        System.out.println(peticion);
        ResultSet rs = stmt.executeQuery(peticion);
        return rs;
    }

    public void realizarUpdate(String peticion) throws SQLException{
        Statement stmt = conexion.createStatement();
        System.out.println(peticion);
        stmt.executeUpdate(peticion);
    }
}