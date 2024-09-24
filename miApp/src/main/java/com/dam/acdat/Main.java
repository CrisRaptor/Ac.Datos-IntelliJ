package com.dam.acdat;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            //Registrar el driver
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver cargado");
            //Cadena de conexion a BD
            String url = "jdbc:postgresql://localhost:5432/institutoFP";
            String user = "postgres";
            String password = "iesbelen";
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion establecida");

            //Crear una vista mediante el RS
            Statement statement = con.createStatement();
            String SQLsentence = "select * from Asignaturas order by Asignaturas.codigo";

            ResultSet rs = statement.executeQuery(SQLsentence);
            System.out.println("Codigo \tNombre");
            while (rs.next()) {
                System.out.println(rs.getInt("codigo") + "\t\t" + rs.getString("nombre"));
            }

            //Cerrar los metodos
            rs.close();
            con.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("Error: No se puede cargar el controlador");
            System.out.println(ex.getMessage());
            System.exit(1);
        } catch (SQLException e) {
            System.out.println("Error: No se puede conectar con la base de datos");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}