package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public static void main(String[] args) {
        Conexion con = new Conexion();

        if (con.getConnection() != null) {
            System.out.println("Si");
        } else {
            System.out.println("No");
        }

    }
}