package com.dam.acdat;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            //Registrar el driver
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver cargado");

            //Cadena de conexion a la base ded datos
            String url = "jdbc:postgresql://localhost:5432/";
            String user = "postgres";
            String password = "iesbelen";
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion establecida con la base de datos");

            //Crear la base de datos
            Statement statement = con.createStatement();
            createDatabase(con,statement);

            //Conectar la base de datos
            con.close();
            statement.close();
            url = "jdbc:postgresql://localhost:5432/institutofp";
            con = DriverManager.getConnection(url, user, password);

            //Insertar la tabla y sus valores
            statement = con.createStatement();
            loadDatabase(con,statement);
            insertValues(statement);

            //Prepara el ResultSet
            ResultSet rs = null;

            //Mostrar el contenido de la tabla Asignaturas
            System.out.println("Mostrando toda la tabla Asignaturas.");
            rs = statement.executeQuery("select * from asignaturas");
            System.out.println("Codigo - Nombre - Anyo");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3));
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

    private static boolean createDatabase(Connection con,Statement statement ) throws SQLException {
        boolean isCreated = false;
        //Crear la base de datos
        String SQLdrop = "DROP DATABASE IF EXISTS institutofp";
        String SQLcreate = "CREATE DATABASE institutofp";
        try {
            statement.execute(SQLdrop);
            isCreated = statement.execute(SQLcreate);
            System.out.println("Base de datos creada con exito");
        } catch (SQLException e) {
            System.out.println("Error: No se pudo crear la base de datos");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return isCreated;
    }

    private static boolean loadDatabase(Connection con,Statement statement ) throws SQLException {
        boolean isCreated = false;
        //Crear dos tablas
        String SQLcreate = """
                    DROP TABLE IF EXISTS asignaturas cascade;
                    CREATE TABLE asignaturas (
                      codigo serial PRIMARY KEY,
                      nombre VARCHAR(50) NOT NULL,
                      anyo int NOT NULL
                    );""";
        try {
            isCreated = statement.execute(SQLcreate);
            System.out.println("Tabla creada con exito");
        } catch (SQLException e) {
            System.out.println("Error: No se pudo crear las tablas");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return isCreated;
    }

    private static int insertValues(Statement statement){
        int editedValues = 0;
        boolean isGenerated = true;
        //Insertar valores
        String[] SQLupdate= {
                "INSERT INTO asignaturas VALUES(DEFAULT, 'ACCESO A DATOS', 2);",
                "INSERT INTO asignaturas VALUES(DEFAULT, 'ENTORNOS DE DESARROLLO', 1);",
                "INSERT INTO asignaturas VALUES(DEFAULT, 'SISTEMAS DE GESTIÓN DE BASES DE DATOS', 1);",
                "INSERT INTO asignaturas VALUES(DEFAULT, 'SERVICIOS Y PROCESOS', 2);"
        };
        for (String sentence : SQLupdate) {
            try {
                editedValues = statement.executeUpdate(sentence);

            } catch (SQLException e) {
                System.out.println("Error: No se pudo insertar los valores");
                System.out.println(e.getMessage());
                isGenerated = false;
                throw new RuntimeException(e);
            }
        }
        System.out.println("Valores generados");
        return editedValues;
    }
}