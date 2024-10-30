package zona_fit.datos;

import zona_fit.dominio.Cliente;
import zona_fit.dominio.Cuota;

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
    public boolean buscarClientePorId(Cliente cliente, boolean bbddMejorada) {
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
                if (!bbddMejorada){
                    cliente.setMembresia(rs.getInt("membresia"));
                }
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
    public boolean agregarCliente(Cliente cliente, boolean bbddMejorada) {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql;
        if (bbddMejorada){
            sql = "INSERT INTO clientes(nombre, apellido) VALUES (?,?)";
        } else {
            sql = "INSERT INTO clientes(nombre, apellido, membresia) VALUES (?,?,?)";
        }

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            if (!bbddMejorada){
                ps.setInt(3, cliente.getMembresia());
            }
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
    public boolean modificarCliente(Cliente cliente, boolean bbddMejorada) {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql;
        if (bbddMejorada) {
            sql = "UPDATE clientes SET nombre=?, apellido=? WHERE id=?";
        } else {
            sql = "UPDATE clientes SET nombre=?, apellido=?, membresia=? WHERE id=?";
        }

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            if (!bbddMejorada) {
                ps.setInt(4, cliente.getId());
            }
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
            System.out.println("Error: No se pudo eliminar cliente por id: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: No se pudo cerrar la conexion: " + e.getMessage());
            }
        }
        return eliminado;
    }

    @Override
    public List<Cuota> listarCuota() {
        List<Cuota> cuotas = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        String sql = "SELECT * FROM cuotas ORDER BY idCuota";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cuota cuota = new Cuota();
                cuota.setIdCuota(rs.getInt("idCuota"));
                cuota.setModalidad(rs.getString("modalidad"));
                cuota.setCuota(rs.getDouble("cuota"));
                cuota.setSituacion(rs.getString("situacion"));
                cuotas.add(cuota);
            }
        } catch (SQLException e) {
            System.out.println("Error: No se pudo listar las cuotas: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: No se pudo cerrar la conexion: " + e.getMessage());
            }
        }

        return cuotas;
    }

    @Override
    public boolean buscarCuotaPorId(Cuota cuota) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        String sql = "SELECT * FROM cuota WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cuota.getIdCuota());
            rs = ps.executeQuery();
            if (rs.next()) {
                cuota.setModalidad(rs.getString("modalidad"));
                cuota.setCuota(rs.getDouble("cuota"));
                cuota.setSituacion(rs.getString("situacion"));
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
    public boolean agregarCuota(Cuota cuota) {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "INSERT INTO cuota(nombre, apellido, membresia) VALUES (?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cuota.getModalidad());
            ps.setDouble(2, cuota.getCuota());
            ps.setString(3, cuota.getSituacion());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: No se pudo agregar cuota : " + e.getMessage());
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
    public boolean modificarCuota(Cuota cuota) {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "UPDATE cuota SET modalidad=?, cuota=?, situacion=? WHERE idCuota=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cuota.getModalidad());
            ps.setDouble(2, cuota.getCuota());
            ps.setString(3, cuota.getSituacion());
            ps.setInt(4, cuota.getIdCuota());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: No se pudo editar cuota : " + e.getMessage());
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
    public boolean eliminarCuota(Cuota cuota) {
        boolean eliminado = false;
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "DELETE FROM cuota WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cuota.getIdCuota());
            ps.execute();
            eliminado = true;
        } catch (SQLException e) {
            System.out.println("Error: No se pudo eliminar cuota por id: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: No se pudo cerrar la conexion: " + e.getMessage());
            }
        }
        return eliminado;
    }
}
