package app_dietas.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    public Connection CrearConexion() {
        Connection con = null;
        String dataBase = "appDietas";

        final String url = "jdbc:postgresql://localhost:5432/" + dataBase;
        final String user = "postgres";
        final String password = "iesbelen";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        }

        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }

        return con;
    }

    //Funcion adicional para borrar la estructura de la base de datos
    public boolean BorrarEstructura() {
        boolean borrado = false;
        Connection con = CrearConexion();
        startTransaction(con);
        try{
            Statement statement = con.createStatement();

            String SQLcentro = "DROP TABLE IF EXISTS CentrosDieteticos cascade;";
            String SQLclientes = "DROP TABLE IF EXISTS Clientes cascade;";
            //Crear la base de datos
            statement.execute(SQLcentro);
            statement.execute(SQLclientes);
            borrado = true;
        } catch (SQLException e) {
            System.out.println("Error: No se pudo borrar la estructura: " + e.getMessage());
        } finally {
            if (borrado) {
                commitTransaction(con);
            } else {
                rollbackTransaction(con);
            }
            return borrado;
        }
    }

    public boolean CrearEstructura() {
        boolean creado = false;
        Connection con = CrearConexion();
        startTransaction(con);
        try{
            Statement statement = con.createStatement();

            String SQLcentro = """
                    CREATE TABLE CentrosDieteticos (
                    id SERIAL PRIMARY KEY,
                    nombre VARCHAR(100) NOT NULL,
                    direccion VARCHAR(255) NOT NULL,
                    telefono VARCHAR(20),
                    email VARCHAR(100),
                    horario TEXT
                    );""";
            String SQLclientes = """
                    CREATE TABLE Clientes (
                    id SERIAL PRIMARY KEY,
                    nombre VARCHAR(100) NOT NULL,
                    fecha_nacimiento VARCHAR(20),
                    telefono VARCHAR(20),
                    email VARCHAR(100),
                    centro_dietetico_id INTEGER REFERENCES CentrosDieteticos(id)
                                       );""";
            //Crear la base de datos
            statement.execute(SQLcentro);
            statement.execute(SQLclientes);
            creado = true;
        } catch (SQLException e) {
            System.out.println("Error: No se pudo crear la estructura: " + e.getMessage());
        } finally {
            if (creado) {
                commitTransaction(con);
            } else {
                rollbackTransaction(con);
            }
            return creado;
        }
    }

    public boolean CargaDeDatos() {
        boolean cargado = false;
        Connection con = CrearConexion();
        startTransaction(con);
        try{
            Statement statement = con.createStatement();

            String SQLcentro = """
                    INSERT INTO CentrosDieteticos (nombre, direccion, telefono, email, horario)
                    VALUES
                    ('Nutrición Integral', 'Av. Siempreviva, 123', '555-1234567', 'nutricion@integral.com', 'Lun-Vie: 9am-2pm y 4pm-
                    8pm'),
                    ('Bienestar y Salud', 'Calle del Sol, 78', '555-8765432', 'bienestar@salud.com', 'Mar-Jue: 10am-1pm y 3pm-
                    7pm');""";
            //En la linea 94 (el cuarto value a insertar tiene un "centro_dietetico_id" no existente (3), le asigno 1 para evitar el problema),
            String SQLclientes = """
                    INSERT INTO Clientes (nombre, fecha_nacimiento, telefono, email, centro_dietetico_id)
                                VALUES  ('Juan Pérez', '1980-01-15', '555-1111111', 'juanperez@example.com', 1),
                                        ('María López', '1992-04-20', '555-2222222', 'marialopez@example.com', 2),
                                        ('Carlos García', '1975-11-05', '555-3333333', 'carlosgarcia@example.com', 1),
                                        ('Ana Rodríguez', '1998-07-12', '555-4444444', 'anarodriguez@example.com', 1),
                                        ('Sofía Martínez', '1995-11-28', '555-5555555', 'sofiamartinez@example.com', 2),
                                        ('David Hernández', '1982-03-10', '555-6666666', 'davidhernandez@example.com', 1),
                                        ('Laura Jiménez', '1999-09-25', '555-7777777', 'laurajimenez@example.com', 2),
                                        ('Pablo Álvarez', '1988-05-18', '555-8888888', 'pabloalvarez@example.com', 1),
                                        ('Andrea Ruiz', '1991-12-03', '555-9999999', 'andrearuiz@example.com', 2),
                                        ('Víctor Gómez', '1985-08-22', '555-1234567', 'victorgomez@example.com', 1),
                                        ('Sara Fernández', '1997-04-15', '555-2345678', 'sarafernandez@example.com', 2),
                                        ('Miguel Santos', '1983-10-30', '555-3456789', 'miguelsantos@example.com', 1),
                                        ('Lucía Díaz', '1994-06-17', '555-4567890', 'luciadaz@example.com', 2),
                                        ('Marcos López', '1987-02-04', '555-5678901', 'marcoslopez@example.com', 1),
                                        ('Irene García', '1993-08-29', '555-6789012', 'ireneg@example.com', 2);""";
            //Crear la base de datos
            statement.execute(SQLcentro);
            statement.execute(SQLclientes);
            cargado = true;
        } catch (SQLException e) {
            System.out.println("Error: No se pudo cargar los datos: " + e.getMessage());
        } finally {
            if (cargado) {
                commitTransaction(con);
            } else {
                rollbackTransaction(con);
            }
            return cargado;
        }
    }

    //Consultas no finalizadas
//    public void Consulta1() {
//
//    }
//
//    public void Consulta2() {
//
//    }
//
//    public void Consulta3() {
//        //CONCAT(FLOOR(YEAR(fecha_nacimiento) / 10) * 10, 's') AS decada
//    }

    public void startTransaction(Connection con) {
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void commitTransaction(Connection con) {
        try {
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rollbackTransaction(Connection con) {
        try {
            con.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}