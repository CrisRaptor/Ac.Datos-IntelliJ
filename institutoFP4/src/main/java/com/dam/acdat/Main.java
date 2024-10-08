package com.dam.acdat;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

//Ejercicio 3.6 -> Linea 79-104, 131-156
public class Main {
    public static void main(String[] args) {
        Scanner kin = new Scanner(System.in);
        try {
            //Registrar el driver
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver cargado");
        }  catch (ClassNotFoundException ex) {
            System.out.println("Error: No se puede cargar el controlador");
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        //Ejercicio anterior (Actividad 3.3)
        try {
            //Cadena de conexion a la base ded datos
            String url = "jdbc:postgresql://localhost:5432/";
            String user = "postgres";
            String password = "iesbelen";
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion establecida con la base de datos");

            //Apartado 1, parte 1/2 - Crear la base de datos "institutofp"
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
            //Apartado 1, parte 2/2 - Crear la tabla "Asignaturas
            loadDatabase(con,statement);
            //Apartado 2 - Insertar datos en la tabla
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
        }  catch (SQLException e) {
            System.out.println("Error: No se puede conectar con la base de datos");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        //Actividad 3.4
        try {
            String url = "jdbc:postgresql://localhost:5432/institutofp";
            String user = "postgres";
            String password = "iesbelen";
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion reestablecida con la base de datos");
            Statement statement = con.createStatement();

            //(Anulado)Apartado 2 - Insertar un nuevo tema y mostrar el valor devuelto
            //Actividad 3.6
            /*Apartado 1 - Reescribir el apartado 2 de actividad 3.4
            para usar PreparedStatement y permitir al usuario insertar varias asignaturas*/
            String nombre;
            Integer anyo;
            int altered;
            try {
                PreparedStatement preparedStatement = con.prepareStatement("insert into asignaturas values(DEFAULT,?,?)");
                System.out.println("Listo para insertar temas");
                do {
                    try {
                        System.out.println("Indica el nombre de la Asignatura");
                        nombre = kin.nextLine();
                        System.out.println("Indica el anyo de la Asignatura");
                        anyo = kin.nextInt();
                        kin.nextLine();
                        preparedStatement.setString(1,nombre);
                        preparedStatement.setInt(2,anyo);
                        altered = preparedStatement.executeUpdate();
                        System.out.println("Insertando nuevo tema...");
                        System.out.println("Valores alterados: " + altered);
                    } catch (InputMismatchException e) {
                        nombre = null;
                        anyo = null;
                    }
                } while(nombre!=null && anyo != null);

            } catch (SQLException e) {
                System.out.println("Error: No se pudo insertar los valores");
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }

            //Apartado 3 - Agregar el campo "Horas" a la tabla Asignaturas
            try {
                String sentence = "ALTER TABLE asignaturas ADD horas integer;";
                System.out.println("Insertando nueva columna \"horas\"...");
                System.out.println("Valores alterados: " + statement.executeUpdate(sentence));
            } catch (SQLException e) {
                System.out.println("Error: No se pudo insertar la columna");
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }

            //Mostrar el contenido de la tabla Asignaturas
            ResultSet rs;
            System.out.println("Mostrando toda la tabla Asignaturas.");
            rs = statement.executeQuery("select * from asignaturas");
            System.out.println("Codigo - Nombre - Anyo - Horas");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3) + " - " + rs.getString(4));
            }

            //Actividad 3.6
            //Apartado 2 - Usar un PreparedStatement para crear la tabla "cursos", con las columnas "codigo" (serial) y "nombre" (varchar 90)
            try {
                System.out.println("Creando cursos...");
                PreparedStatement preparedStatement = con.prepareStatement("drop table if exists cursos;");
                preparedStatement.executeUpdate();
                //Insertar la tabla
                preparedStatement = con.prepareStatement("""
                        create table cursos(
                        codigo serial PRIMARY KEY,
                        nombre VARCHAR(90) NOT NULL
                        );""");
                preparedStatement.executeUpdate();
                //Insertar valores en la tabla
                preparedStatement = con.prepareStatement("insert into cursos values(DEFAULT,?)");
                preparedStatement.setString(1,"Desarrollo de aplicaciones multiplataforma");
                preparedStatement.executeUpdate();
                preparedStatement.setString(1,"Desarrollo web");
                preparedStatement.executeUpdate();
                //Mostrar los valores
                System.out.println("Mostrando toda la tabla Cursos.");
                rs = statement.executeQuery("select * from cursos");
                System.out.println("Codigo - Nombre");
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " - " + rs.getString(2));
                }
            } catch (SQLException e){
                System.out.println("Error: No se puede crear el curso");
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
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