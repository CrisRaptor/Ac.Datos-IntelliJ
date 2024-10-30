package zona_fit.datos;

import zona_fit.dominio.Cliente;
import zona_fit.dominio.Cuota;

import java.util.List;

public interface IClienteDAO {
    List<Cliente> listarClientes();

    boolean buscarClientePorId(Cliente cliente, boolean bbddMejorada);

    boolean agregarCliente(Cliente cliente, boolean bbddMejorada);

    boolean modificarCliente(Cliente cliente, boolean bbddMejorado);

    boolean eliminarCliente(Cliente cliente);

    List<Cuota> listarCuota();

    boolean buscarCuotaPorId(Cuota cuota);

    boolean agregarCuota(Cuota cuota);

    boolean modificarCuota(Cuota cuota);

    boolean eliminarCuota(Cuota cuota);
}
