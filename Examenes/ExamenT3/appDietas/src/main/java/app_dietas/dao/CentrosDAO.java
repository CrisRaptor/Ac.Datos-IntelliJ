package app_dietas.dao;

import app_dietas.model.Centro;
import app_dietas.model.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CentrosDAO {

    public List<Centro> ListarCentro(Conexion conexion) {
        List<Centro> centros = new ArrayList<>();
        Connection con = conexion.CrearConexion();
        conexion.startTransaction(con);
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM CentrosDieteticos ORDER BY id";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Centro centro = new Centro(rs.getInt("id"));
                centro.setNombre(rs.getString("nombre"));
                centro.setDireccion(rs.getString("direccion"));
                centro.setTelefono(rs.getString("telefono"));
                centro.setEmail(rs.getString("email"));
                centro.setHorario(rs.getString("horario"));
                centros.add(centro);
            }
        } catch (SQLException e) {
            System.out.println("Error: No se pudo listar los centros: " + e.getMessage());
            conexion.rollbackTransaction(con);
        } finally {
            conexion.commitTransaction(con);
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: No se pudo cerrar la conexion: " + e.getMessage());
            }
        }

        return centros;
    }

    public boolean BuscarCentro(Centro centro, Conexion conexion) {
        PreparedStatement ps;
        Connection con = conexion.CrearConexion();
        conexion.startTransaction(con);
        ResultSet rs;
        String sql = "SELECT * FROM CentrosDieteticos WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, centro.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                centro.setNombre(rs.getString("nombre"));
                centro.setDireccion(rs.getString("direccion"));
                centro.setTelefono(rs.getString("telefono"));
                centro.setEmail(rs.getString("email"));
                centro.setHorario(rs.getString("horario"));
                conexion.commitTransaction(con);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error: No se pudo recuperar el centro por id: " + e.getMessage());
        } finally {
            conexion.rollbackTransaction(con);
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: No se pudo cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean AgregarCentro(Centro centro, Conexion conexion) {
        PreparedStatement ps;
        Connection con = conexion.CrearConexion();
        conexion.startTransaction(con);
        String sql = "INSERT INTO CentrosDieteticos(nombre, direccion, telefono, email, horario) VALUES (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, centro.getNombre());
            ps.setString(2, centro.getDireccion());
            ps.setString(3, centro.getTelefono());
            ps.setString(4, centro.getEmail());
            ps.setString(5, centro.getHorario());
            ps.execute();
            conexion.commitTransaction(con);
            return true;
        } catch (SQLException e) {
            System.out.println("Error: No se pudo agregar centro : " + e.getMessage());
        } finally {
            conexion.rollbackTransaction(con);
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: No se pudo cerrar la conexion: " + e.getMessage());
            }
        }

        return false;
    }

    public boolean ModificarCentro(Centro centro, Conexion conexion) {
        PreparedStatement ps;
        Connection con = conexion.CrearConexion();
        conexion.startTransaction(con);
        String sql = "UPDATE CentrosDieteticos SET nombre=?, direccion=?, telefono=?, email=?, horario=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, centro.getNombre());
            ps.setString(2, centro.getDireccion());
            ps.setString(3, centro.getTelefono());
            ps.setString(4, centro.getEmail());
            ps.setString(5, centro.getHorario());
            ps.setInt(6, centro.getId());
            ps.executeUpdate();
            conexion.commitTransaction(con);
            return true;
        } catch (SQLException e) {
            System.out.println("Error: No se pudo editar el centro : " + e.getMessage());
        } finally {
            conexion.rollbackTransaction(con);
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: No se pudo cerrar la conexion: " + e.getMessage());
            }
        }

        return false;
    }

    public boolean EliminarCentro(Centro centro, Conexion conexion) {
            boolean eliminado = false;
            PreparedStatement ps;
            Connection con = conexion.CrearConexion();
            conexion.startTransaction(con);
            String sql = "DELETE FROM CentrosDieteticos WHERE id = ?";
            try {
                ps = con.prepareStatement(sql);
                ps.setInt(1, centro.getId());
                ps.execute();
                conexion.commitTransaction(con);
                eliminado = true;
            } catch (SQLException e) {
                System.out.println("Error: No se pudo recuperar centro por id: " + e.getMessage());
            } finally {
                conexion.rollbackTransaction(con);
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Error: No se pudo cerrar la conexion: " + e.getMessage());
                }
            }
            return eliminado;
        }
}
