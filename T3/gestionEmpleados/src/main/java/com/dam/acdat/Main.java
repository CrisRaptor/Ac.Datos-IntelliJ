package com.dam.acdat;

import java.sql.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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
        try {
            //Cadena de conexion a BD
            String url = "jdbc:postgresql://localhost:5432/empleados";
            String user = "postgres";
            String password = "iesbelen";
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion establecida con la base de datos \"Empleados\"");

            Statement statement = con.createStatement();
            loadDatabase(con,statement);
            insertValues(statement);

            //Prepara el ResultSet
            ResultSet rs = null;

            //Menu de consultas
            int opcion;
            do {
                opcion = menu(kin);

                switch (opcion) {
                    case 0 -> {
                        System.out.println("Saliendo del programa...");
                    }
                    case 1 -> {
                        System.out.println("Mostrando todos los primeros apellidos de los empleados.");
                        rs = statement.executeQuery("select apellido1 from empleado");
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }

                    }
                    case 2 -> {
                        System.out.println("Mostrando todos los primeros apellidos distintos de los empleados.");
                        rs = statement.executeQuery("select apellido1 from empleado group by apellido1");
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }
                    }
                    case 3 -> {
                        System.out.println("Mostrando los 2 departamentos con menor gasto.");
                        rs = statement.executeQuery("select nombre,gastos from departamento order by gastos asc limit 2");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
                        }
                    }
                    case 4 -> {
                        System.out.println("Mostrando departamentos con presupuesto minimo de 150000 euros.");
                        rs = statement.executeQuery("select nombre,presupuesto from departamento where presupuesto >= 150000");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2)+"€");
                        }
                    }
                    case 5 -> {
                        System.out.println("Mostrando los datos de departamento de cada empleado.");
                        rs = statement.executeQuery("select empleado.nombre,departamento.* from empleado inner join departamento on empleado.id_departamento = departamento.id");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "€\t" + rs.getString(5));
                        }
                    }
                    case 6 -> {
                        System.out.println("Mostrando los datos de departamento de cada empleado por orden de departamento, apellidos y nombre.");
                        rs = statement.executeQuery("select empleado.nombre,departamento.* from empleado inner join departamento on empleado.id_departamento = departamento.id order by departamento.nombre, empleado.apellido1, empleado.nombre");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "€\t" + rs.getString(5));
                        }
                    }
                    case 7 -> {
                        System.out.println("Mostrando departamentos con empleados.");
                        rs = statement.executeQuery("select departamento.id, departamento.nombre from departamento inner join empleado on empleado.id_departamento = departamento.id group by departamento.id order by departamento.id");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
                        }
                    }
                    case 8 -> {
                        System.out.println("Mostrando el departamento del empleado con nif 38382980M.");
                        rs = statement.executeQuery("select departamento.nombre from empleado inner join departamento on empleado.id_departamento = departamento.id where empleado.nif = '38382980M'");
                        while (rs.next()) {
                            System.out.println(rs.getString(1));
                        }
                    }
                    case 9 -> {
                        System.out.println("Mostrando la suma del presupuesto de todos los departamentos.");
                        rs = statement.executeQuery("select sum(presupuesto) from departamento");
                        while (rs.next()) {
                            System.out.println(rs.getString(1)+"€");
                        }
                    }
                }
            } while (opcion != 0);

            //Cerrar los metodos
            rs.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: No se puede conectar con la base de datos");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static boolean loadDatabase(Connection con,Statement statement ) throws SQLException {
        boolean isCreated = false;
        //Crear dos tablas
        String SQLcreate = """
                    DROP TABLE IF EXISTS departamento cascade;
                    CREATE TABLE departamento (
                      id serial PRIMARY KEY,
                      nombre VARCHAR(100) NOT NULL,
                      presupuesto double precision  NOT NULL,
                      gastos double precision  NOT NULL
                    );
                    DROP TABLE IF EXISTS empleado cascade;
                    CREATE TABLE empleado (
                      id serial PRIMARY KEY,
                      nif VARCHAR(9) NOT NULL UNIQUE,
                      nombre VARCHAR(100) NOT NULL,
                      apellido1 VARCHAR(100) NOT NULL,
                      apellido2 VARCHAR(100),
                      id_departamento INT,
                      FOREIGN KEY (id_departamento) REFERENCES departamento(id)
                    );""";
        try {
            isCreated = statement.execute(SQLcreate);
            System.out.println("Tablas creadas con exito");
        } catch (SQLException e) {
            System.out.println("Error: No se pudo crear las tablas");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return isCreated;
    }

    private static int insertValues(Statement statement){
        int editedValues = 0;
        //Insertar valores
        String[] SQLupdate= {
                "INSERT INTO departamento VALUES(DEFAULT, 'Desarrollo', 120000, 6000);",
                "INSERT INTO departamento VALUES(DEFAULT, 'Sistemas', 150000, 21000);",
                "INSERT INTO departamento VALUES(DEFAULT, 'Recursos Humanos', 280000, 25000);",
                "INSERT INTO departamento VALUES(DEFAULT, 'Contabilidad', 110000, 3000);",
                "INSERT INTO departamento VALUES(DEFAULT, 'I+D', 375000, 380000);",
                "INSERT INTO departamento VALUES(DEFAULT, 'Proyectos', 0, 0);",
                "INSERT INTO departamento VALUES(DEFAULT, 'Publicidad', 0, 1000);",
                "INSERT INTO empleado VALUES(DEFAULT, '32481596F', 'Aarón', 'Rivero', 'Gómez', 1);",
                "INSERT INTO empleado VALUES(DEFAULT, 'Y5575632D', 'Adela', 'Salas', 'Díaz', 2);",
                "INSERT INTO empleado VALUES(DEFAULT, 'R6970642B', 'Adolfo', 'Rubio', 'Flores', 3);",
                "INSERT INTO empleado VALUES(DEFAULT, '77705545E', 'Adrián', 'Suárez', NULL, 4);",
                "INSERT INTO empleado VALUES(DEFAULT, '17087203C', 'Marcos', 'Loyola', 'Méndez', 5);",
                "INSERT INTO empleado VALUES(DEFAULT, '38382980M', 'María', 'Santana', 'Moreno', 1);",
                "INSERT INTO empleado VALUES(DEFAULT, '80576669X', 'Pilar', 'Ruiz', NULL, 2);",
                "INSERT INTO empleado VALUES(DEFAULT, '71651431Z', 'Pepe', 'Ruiz', 'Santana', 3);",
                "INSERT INTO empleado VALUES(DEFAULT, '56399183D', 'Juan', 'Gómez', 'López', 2);",
                "INSERT INTO empleado VALUES(DEFAULT, '46384486H', 'Diego','Flores', 'Salas', 5);",
                "INSERT INTO empleado VALUES(DEFAULT, '67389283A', 'Marta','Herrera', 'Gil', 1);",
                "INSERT INTO empleado VALUES(DEFAULT, '41234836R', 'Irene','Salas', 'Flores', NULL);",
                "INSERT INTO empleado VALUES(DEFAULT, '82635162B', 'Juan Antonio','S\u00E1ez', 'Guerrero', NULL);"};
        for (String sentence : SQLupdate) {
            try {
                editedValues = statement.executeUpdate(sentence);
                System.out.println("Valores generados");
            } catch (SQLException e) {
                System.out.println("Error: No se pudo insertar los valores");
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }

        return editedValues;
    }

    private static int menu(Scanner kin){
        int opcion;
        do {
            System.out.println("""
                            
                            ___Consultas de empleados disponibles___
                           1.Lista de todos los primeros apellidos.
                           2.Lista de todos los primeros apellidos, sin repeticiones.
                           3.Lista de nombre y gasto de los 2 departamentos con menor gasto.
                           4.Lista del nombre de los departamentos y el presupuesto, donde presupuesto sea mayor o igual a 150000 euros.
                           5.Lista de empleados y datos de los departamentos donde trabaja cada uno.
                           6.Lista de empleados y datos de los departamentos donde trabaja cada uno. 
                             Por orden alfabetico del departamento, apellidos y nombre respectivamente.
                           7.Listado del identificador y el nombre de los departamentos con empleados.
                           8.Nombre del departamento donde trabaja el empleado con nif 38382980M.
                           9.Suma del presupuesto de todos los departamentos.
                           0.Salir""");
            opcion = kin.nextInt();
        } while ((opcion < 0) || (opcion > 9));
        return opcion;
    }
}