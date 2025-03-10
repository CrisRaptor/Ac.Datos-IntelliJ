package com.dam.acdat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

//Ejercicio 3.7 -> Main 69-96, Metodos 148-158
public class Main {
    public static void main(String[] args) {

        try {
            //Registrar el driver
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver cargado");
        }  catch (ClassNotFoundException ex) {
            System.out.println("Error: No se puede cargar el controlador");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        //(Ejecicio anterior) Actividad 3.3 (Apartado 3)
        try {
            //Cadena de conexion a la base de datos
            String url = "jdbc:postgresql://localhost:5432/";
            String user = "postgres";
            String password = "iesbelen";
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion establecida con la base de datos");

            //(Ejecicio anterior)Apartado 3, parte 1/3 - Crear la base de datos "empleados"
                //Crear la base de datos
                Statement statement = con.createStatement();
                createDatabase(con,statement);

            //Conectar la base de datos
            con.close();
            statement.close();
            url = "jdbc:postgresql://localhost:5432/empleados";
            con = DriverManager.getConnection(url, user, password);

            //(Ejecicio anterior) Apartado 3, parte 2/3 - Ejecutar el script "empleado-dpto.sql"
                //Insertar la tabla y sus valores
                statement = con.createStatement();
                loadDatabase(con,statement);

            //Prepara el ResultSet
            ResultSet rs = null;

            //(Ejecicio anterior)Apartado 3, parte 3/3 - Comprobar ambas tablas
            //Mostrar el contenido de la tabla Departamentos
            System.out.println("Mostrando la base de datos Empleados.");
            System.out.println("\nMostrando la tabla departamentos");
            rs = statement.executeQuery("select * from departamentos");
            System.out.println("Depno - Nombre - Ubicacion");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3));
            }
            //Mostrar el contenido de la tabla Empleados
            System.out.println("\nMostrando la tabla empleados");
            rs = statement.executeQuery("select * from empleados");
            System.out.println("Empno - Nombre - Puesto - Depno");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3) + " - " + rs.getString(4));
            }

            //Cerrar el result set
            rs.close();

            // Actividad 3.3
            // Apartado 1
            ResultSet resultSet = statement.executeQuery(enumerarEmpleadoPorPuesto(("Dependiente").toString()));

            System.out.println("Enumeración de los empleados en un puesto específico");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("empno"));
            }

            System.out.println("\n");

            // Apartado 2
            resultSet = statement.executeQuery(empleadoPos(("Contabilidad").toString()));

            System.out.println("Empleados pertenecientes a un departamento específico");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("nombre"));
            }

            System.out.println("\n");

            // Apartado 3
            resultSet = statement.executeQuery(nombreEmpleadoPorPatron());

            System.out.println("Nombres de empleados que coinciden con patrón");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("nombre"));
            }

            //Cerrar los metodos
            resultSet.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: No se puede conectar con la base de datos");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static boolean createDatabase(Connection con,Statement statement ) throws SQLException {
        boolean isCreated = false;
        //Crear la base de datos
        String SQLdrop = "DROP DATABASE IF EXISTS empleados";
        String SQLcreate = "CREATE DATABASE empleados";
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
        String SQLline;
        try (BufferedReader in = new BufferedReader(new FileReader("empleado-dpto.sql"))) {
            SQLline = in.readLine();
            while (SQLline != null) {
                isCreated = statement.execute(SQLline);
                SQLline = in.readLine();
            }
            System.out.println("Tabla creada con exito");
        } catch (IOException ex) {
            System.out.println("Error: File not found - " + ex.getMessage());
        } catch (SQLException e) {
            System.out.println("Error: No se pudo crear las tablas");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return isCreated;
    }

    //Metodos Actividad 3.7
    private static String enumerarEmpleadoPorPuesto(String puesto) {
        return "SELECT empno FROM empleados WHERE puesto='" + puesto + "'";
    }

    private static String empleadoPos(String departamento) {
        return "SELECT e.nombre FROM empleados AS e JOIN departamentos AS d ON e.depno = d.depno WHERE d.nombre = '" + departamento + "'";
    }
    private static String nombreEmpleadoPorPatron() {
        return "SELECT nombre FROM empleados WHERE nombre like 'P%'";
    }


}