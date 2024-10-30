package zona_fit.conexion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class Conexion {

    public static Connection getConnection() {
        Connection con = null;
        String dataBase = "zona_fit_db";

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
            //System.out.println("Connected to database");


        } catch (SQLException e) {
            System.out.println("Connection failed");
        }

        return con;
    }

    public boolean mejorarEstructura() {
        boolean mejorado = false;
        Connection con = getConnection();
        Statement statement;
        String createCuotasSql = "CREATE TABLE cuotas (idCuota SERIAL PRIMARY KEY," +
                "modalidad VARCHAR(20) NOT NULL, cuota DECIMAL(10, 2) NOT NULL," +
                "situacion VARCHAR(10) CHECK(situacion IN ('activo', 'inactivo')) NOT NULL);";
        String quitarMembresiaSql = "ALTER TABLE clientes DROP COLUMN membresia";
        String mejoraClientesSql = "ALTER TABLE clientes ADD COLUMN email varchar(100) UNIQUE NOT NULL, " +
                "telefono varchar(15) NOT NULL," +
                " estado VARCHAR(10) CHECK(situacion IN ('activo', 'inactivo')) NOT NULL, " +
                "idCuota INT REFERENCES cuotas(idCuota) on DELETE SET NULL;";
        try {
            statement = con.createStatement();
            statement.executeUpdate(createCuotasSql);
            statement.executeUpdate(quitarMembresiaSql);
            statement.executeUpdate(mejoraClientesSql);
            mejorado = true;
        } catch (SQLException e) {
            System.out.println("Error: No se pudo mejorar la base de datos: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: No se pudo cerrar la conexion: " + e.getMessage());
            }
        }

        return mejorado;
    }

    public boolean cargaDatos(String sqlFilePath){
        boolean cargado = false;
        Statement statement;
        Connection con = getConnection();
        String SQLline;
        try (BufferedReader in = new BufferedReader(new FileReader("carga_datos.sql"))) {
            statement = con.createStatement();
            SQLline = in.readLine();
            while (SQLline != null){
                statement.executeUpdate(SQLline);
                SQLline = in.readLine();
            };
            cargado = true;
        } catch (IOException ex) {
            System.out.println("Error: File not found - " + ex.getMessage());
        } catch (SQLException e) {
            System.out.println("Error: No se pudo cargas los datos");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: No se pudo cerrar la conexion: " + e.getMessage());
            }
        }

        return cargado;
    }

    public static void main(String[] args) {
        Conexion con = new Conexion();

        if (con.getConnection() != null) {
            System.out.println("Si");
        } else {
            System.out.println("No");
        }

    }
}