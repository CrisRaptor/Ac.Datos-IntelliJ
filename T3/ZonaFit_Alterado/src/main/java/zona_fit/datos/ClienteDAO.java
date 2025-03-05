package zona_fit.datos;

import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static zona_fit.conexion.Conexion.getConnection;

public class ClienteDAO implements IClienteDAO {
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        String sql = "SELECT * FROM clientes ORDER BY id";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error: No se pudo listar los clientes: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: No se pudo cerrar la conexion: " + e.getMessage());
            }
        }

        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error: No se pudo recuperar cliente por id: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: No se pudo cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "INSERT INTO clientes(nombre, apellido, membresia) VALUES (?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: No se pudo agregar cliente : " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: No se pudo cerrar la conexion: " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "UPDATE clientes SET nombre=?, apellido=?, membresia=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: No se pudo editar cliente : " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: No se pudo cerrar la conexion: " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        boolean eliminado = false;
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "DELETE FROM clientes WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();
            eliminado = true;
        } catch (SQLException e) {
            System.out.println("Error: No se pudo recuperar cliente por id: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: No se pudo cerrar la conexion: " + e.getMessage());
            }
        }
        return eliminado;
    }

    public static void main(String[] args) {
        //Listar clientes
        System.out.println("--- Listar Clientes ---");
        IClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.listarClientes();
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }

        //Buscar por id
        Cliente cliente1 = new Cliente(2);
        System.out.println("Cliente antes de la busqueda: " + cliente1);
        boolean encontrado = clienteDAO.buscarClientePorId(cliente1);
        if (encontrado) {
            System.out.println("Cliente encontrado: " + cliente1);
        } else {
            System.out.println("Cliente no encontrado: " + cliente1.getId());
        }

        // Agregar cliente
        Cliente newCliente = new Cliente();
        boolean agregado = clienteDAO.agregarCliente(newCliente);
        if (agregado) {
            System.out.println("Cliente agregado: " + newCliente);
        } else {
            System.out.println("Cliente no agregado: " + newCliente);
        }

        // Modificar cliente
        Cliente cliente2 = new Cliente(2, "Carlos daniel", "Ortiz", 300);
        boolean modificado = clienteDAO.modificarCliente(cliente2);
        if (modificado) {
            System.out.println("Cliente modificado: " + cliente2);
        } else {
            System.out.println("Cliente no modificado: " + cliente2);
        }

        // Eliminar cliente
        Cliente cliente3 = new Cliente(3);
        boolean eliminado = clienteDAO.eliminarCliente(cliente2);
        if (eliminado) {
            System.out.println("Cliente eliminado: " + cliente2);
        } else {
            System.out.println("Cliente no eliminado: " + cliente2);
        }

        //Re-Listar clientes
        System.out.println("--- Listar Clientes ---");
        clienteDAO = new ClienteDAO();
        clientes = clienteDAO.listarClientes();
        for (Cliente client : clientes) {
            System.out.println(client.toString());
        }


    }
}
