package org.acdat.jdbc;

import org.acdat.vista.VistaVuelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MiViajeFindeCurso {
    public static void crearViaje(String[] args) {
        MiJDBC jdbc = new MiJDBC();
        try {
            jdbc.IniciarTransaccion();
            jdbc.abrirConexion();
            cargarEstructuras(jdbc.connection);
            importarAgencias(jdbc.connection);
            importarVuelos(jdbc.connection);
            importarClientes(jdbc.connection);
            importarDestinos(jdbc.connection);

            new VistaVuelo().crudVuelo();
            jdbc.commitTransaccion();
        } catch (SQLException e) {
            e.printStackTrace();
            jdbc.rollbackTransaccion();
        } finally {
            jdbc.cerrarConexion();
        }
    }

    private static void cargarEstructuras(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        //Agencias
        String sql = "CREATE TABLE agencias (\n" +
                "agencia_id SERIAL PRIMARY KEY,\n" +
                "nombre_agencia VARCHAR(100) NOT NULL,\n" +
                "direccion_agencia TEXT,\n" +
                "telefono_agencia VARCHAR(20)\n" +
                ");";
        stmt.execute(sql);

        //Clientes
        sql = "CREATE TABLE clientes (\n" +
                "cliente_id SERIAL PRIMARY KEY,\n" +
                "nombre_cliente VARCHAR(100) NOT NULL,\n" +
                "correo_cliente VARCHAR(100) UNIQUE,\n" +
                "telefono_cliente VARCHAR(20)\n" +
                ");";
        stmt.execute(sql);

        //Destinos
        sql = "CREATE TABLE destinos (\n" +
                "destino_id SERIAL PRIMARY KEY,\n" +
                "nombre_destino VARCHAR(100) NOT NULL,\n" +
                "descripcion TEXT,\n" +
                "costo_estadia NUMERIC\n" +
                ");";
        stmt.execute(sql);

        //Vuelos
        sql = "CREATE TABLE vuelos (\n" +
                "vuelo_id SERIAL PRIMARY KEY,\n" +
                "origen VARCHAR(50),\n" +
                "destino VARCHAR(50),\n" +
                "fecha_salida DATE,\n" +
                "fecha_llegada DATE,\n" +
                "costo NUMERIC(10, 2)\n" +
                ");";
        stmt.execute(sql);
    }

    private static void importarAgencias(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        //Agencias
        String sql = "INSERT INTO agencias (nombre_agencia, direccion_agencia, telefono_agencia)\n" +
                "VALUES ('Viajes Fantásticos', 'Calle Principal 123', '+999888777'),\n" +
                "       ('Aventuras Globales', 'Avenida Central 456', '+333222111'),\n" +
                "       ('Destinos Soñados', 'Plaza de la Libertad 789', '+555444333'),\n" +
                "       ('Mundo Viajero', 'Calle Viajera 101', '+777666555'),\n" +
                "       ('Turismo Excelente', 'Avenida de los Sueños 202', '+888777666'),\n" +
                "       ('Rutas Inolvidables', 'Paseo del Descanso 303', '+111000999'),\n" +
                "       ('Viajes y Más', 'Carrera Aventurera 505', '+222111000'),\n" +
                "       ('Explora el Mundo', 'Rincón del Viajero 606', '+444333222'),\n" +
                "       ('Destinos Únicos', 'Calle de la Aventura 707', '+666555444'),\n" +
                "       ('Aventuras Extremas', 'Plaza del Viajero 808', '+000999888');\n";
        stmt.executeUpdate(sql);
    }

    private static void importarClientes(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        //Clientes
        String sql = "INSERT INTO clientes (nombre_cliente, correo_cliente, telefono_cliente) VALUES\n" +
                "('Luisa Martínez', 'luisa@email.com', '+1234567890'),\n" +
                "('Pedro González', 'pedro@email.com', '+2345678901'),\n" +
                "('María Rodríguez', 'maria@email.com', '+3456789012'),\n" +
                "('Alejandro López', 'alejandro@email.com', '+4567890123'),\n" +
                "('Ana Sánchez', 'ana@email.com', '+5678901234'),\n" +
                "('Javier Ramírez', 'javier@email.com', '+6789012345'),\n" +
                "('Isabel Fernández', 'isabel@email.com', '+7890123456'),\n" +
                "('Carlos Pérez', 'carlos@email.com', '+8901234567'),\n" +
                "('Elena Gómez', 'elena@email.com', '+9012345678'),\n" +
                "('Andrés Díaz', 'andres@email.com', '+0123456789'),\n" +
                "('Laura Torres', 'laura@email.com', '+9876543210'),\n" +
                "('Roberto Herrera', 'roberto@email.com', '+8765432109'),\n" +
                "('Silvia Castro', 'silvia@email.com', '+7654321098'),\n" +
                "('Juan García', 'juan@email.com', '+6543210987'),\n" +
                "('Carmen Ruiz', 'carmen@email.com', '+5432109876'),\n" +
                "('Fernando Méndez', 'fernando@email.com', '+4321098765'),\n" +
                "('Sara Vargas', 'sara@email.com', '+3210987654'),\n" +
                "('Héctor Navarro', 'hector@email.com', '+2109876543'),\n" +
                "('Lucía Medina', 'lucia@email.com', '+1098765432'),\n" +
                "('Diego Ríos', 'diego@email.com', '+9876543210'),\n" +
                "('Patricia Silva', 'patricia@email.com', '+8765432109'),\n" +
                "('Adrián Mendoza', 'adrian@email.com', '+7654321098'),\n" +
                "('Natalia Romero', 'natalia@email.com', '+6543210987'),\n" +
                "('Martín Castro', 'martin@email.com', '+5432109876'),\n" +
                "('Eva Morales', 'eva@email.com', '+4321098765'),\n" +
                "('Raúl Guzmán', 'raul@email.com', '+3210987654'),\n" +
                "('Lorena Ortega', 'lorena@email.com', '+2109876543'),\n" +
                "('Pablo Roca', 'pablo@email.com', '+1098765432'),\n" +
                "('Beatriz León', 'beatriz@email.com', '+9876543210'),\n" +
                "('Andrea Díaz', 'andrea@email.com', '+8765432109'),\n" +
                "('Óscar Muñoz', 'oscar@email.com', '+7654321098'),\n" +
                "('Marta Cordero', 'marta@email.com', '+6543210987'),\n" +
                "('Ricardo Soto', 'ricardo@email.com', '+5432109876'),\n" +
                "('Cristina Paredes', 'cristina@email.com', '+4321098765'),\n" +
                "('Jorge Gallego', 'jorge@email.com', '+3210987654'),\n" +
                "('Natalie Rojas', 'natalie@email.com', '+2109876543'),\n" +
                "('Gabriel Gil', 'gabriel@email.com', '+1098765432'),\n" +
                "('Ana Rosa', 'anarosa@email.com', '+9876543210'),\n" +
                "('Manuel Mora', 'manuel@email.com', '+8765432109'),\n" +
                "('Rosa Serrano', 'rosa@email.com', '+7654321098'),\n" +
                "('Hugo Guerra', 'hugo@email.com', '+6543210987'),\n" +
                "('Julia Torres', 'julia@email.com', '+5432109876'),\n" +
                "('Eduardo Alvarado', 'eduardo@email.com', '+4321098765'),\n" +
                "('Mónica Pinto', 'monica@email.com', '+3210987654'),\n" +
                "('Gustavo Ramos', 'gustavo@email.com', '+2109876543'),\n" +
                "('Paola Jiménez', 'paola@email.com', '+1098765432'),\n" +
                "('Daniel Salgado', 'daniel@email.com', '+9876543210');\n";
        stmt.executeUpdate(sql);
    }

    private static void importarDestinos(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        //Destinos
        String sql = "INSERT INTO destinos (nombre_destino, descripcion, costo_estadia)\n" +
                "VALUES ('París', 'Ciudad del amor y la moda', 1500),\n" +
                "       ('Tokio', 'Capital de Japón con una mezcla única de tradición y tecnología', 2000),\n" +
                "       ('Nueva York', 'La Gran Manzana con rascacielos icónicos', 1800),\n" +
                "       ('Roma', 'Cuna de la civilización romana y arte renacentista', 1600),\n" +
                "       ('Sídney', 'Puente del puerto y la Ópera, destino emblemático en Australia', 2200),\n" +
                "       ('Barcelona', 'Arquitectura modernista y playas mediterráneas', 1700),\n" +
                "       ('Kioto', 'Antigua capital de Japón con templos y jardines zen', 1900),\n" +
                "       ('Marrakech', 'Ciudad imperial de Marruecos con zocos y palacios', 1400),\n" +
                "       ('Estambul', 'Puente entre Europa y Asia con la Mezquita Azul y Hagia Sophia', 2000),\n" +
                "       ('Ciudad del Cabo', 'Ciudad costera con la Montaña de la Mesa', 2100),\n" +
                "       ('Praga', 'Ciudad de las cien torres con un encanto medieval', 1600),\n" +
                "       ('Bali', 'Isla indonesia famosa por sus playas y arrozales', 1800),\n" +
                "       ('Machu Picchu', 'Ciudadela inca en lo alto de los Andes', 2500),\n" +
                "       ('Toronto', 'Ciudad cosmopolita con la Torre CN y las Cataratas del Niágara', 1900),\n" +
                "       ('Dubái', 'Ciudad del lujo y la modernidad en medio del desierto', 2300);\n";
        stmt.executeUpdate(sql);
    }

    private static void importarVuelos(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        //Vuelos
        String sql = "INSERT INTO vuelos (origen, destino, fecha_salida, fecha_llegada, costo) VALUES\n" +
                "('Ciudad A', 'Ciudad B', '2023-12-01', '2023-12-05', 500.00),\n" +
                "('Ciudad C', 'Ciudad D', '2023-12-10', '2023-12-15', 700.00),\n" +
                "('Ciudad E', 'Ciudad F', '2023-12-20', '2023-12-25', 900.00);\n";
        stmt.executeUpdate(sql);
    }
}
