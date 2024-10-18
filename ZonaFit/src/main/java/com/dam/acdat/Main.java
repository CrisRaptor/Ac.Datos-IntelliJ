package com.dam.acdat;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        //Registro
        try {
            //Registrar el driver
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver cargado");
        }  catch (ClassNotFoundException ex) {
            System.out.println("Error: No se puede cargar el controlador");
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        //Conexion
        try {
            //Cadena de conexion a la base de datos
            String url = "jdbc:postgresql://localhost:5432/";
            String user = "postgres";
            String password = "iesbelen";
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion establecida con la base de datos");

            //Crear el statement
            Statement statement = con.createStatement();

            //Cerrar los metodos
            //rs.close();
            con.close();
        }  catch (SQLException e) {
            System.out.println("Error: No se puede conectar con la base de datos");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}