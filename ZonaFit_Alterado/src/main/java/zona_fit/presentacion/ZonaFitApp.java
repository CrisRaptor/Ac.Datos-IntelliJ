package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.List;
import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp() {
        boolean salir = false;
        Scanner entrada = new Scanner(System.in);
        int opcion;
        //Creamos un objeto de la clase ClienteDAO
        IClienteDAO clienteDAO = new ClienteDAO();
        while (!salir) {
            opcion = mostrarMenu(entrada);
            salir = ejecutarOpciones(entrada, opcion, clienteDAO);
            System.out.println();
        }

    }

    private static int mostrarMenu(Scanner entrada) {
        int opcion;
        do {
            System.out.println("""
                    --- MENU CLIENTES ---
                    1. Listar clientes
                    2. Buscar cliente
                    3. Agregar cliente
                    4. Eliminar cliente
                    5. Modificar cliente
                    6. Salir""");
            opcion = Integer.parseInt(entrada.nextLine());
        } while (opcion < 0 || opcion > 6);
        return opcion;
    }

    private static boolean ejecutarOpciones(Scanner entrada, int opcion, IClienteDAO clienteDAO) {
        boolean salir = false;
        switch (opcion) {
            case 1 -> {
                System.out.println("--- Lista de Clientes ---");
                List<Cliente> clientes = clienteDAO.listarClientes();
                for (Cliente client : clientes) {
                    System.out.println(client.toString());
                }
            }
            case 2 -> {
                System.out.println("--- Buscar cliente ---");
                System.out.println("Introduce el id del Cliente a buscar: ");
                int id = Integer.parseInt(entrada.nextLine());
                Cliente cliente = new Cliente(id);
                boolean encontrado = clienteDAO.buscarClientePorId(cliente);
                if (encontrado) {
                    System.out.println("Cliente encontrado " + cliente);
                } else {
                    System.out.println("Cliente no encontrado " + cliente);
                }
            }
            case 3 -> {
                System.out.println("--- Agregar cliente ---");
                System.out.println("Nombre: ");
                String nombre = entrada.nextLine();
                System.out.println("Apellido: ");
                String apellido = entrada.nextLine();
                System.out.println("Membresia: ");
                int membresia = Integer.parseInt(entrada.nextLine());
                Cliente cliente = new Cliente(nombre, apellido, membresia);
                boolean agregado = clienteDAO.agregarCliente(cliente);
                if (agregado) {
                    System.out.println("Cliente agregado " + cliente);
                } else {
                    System.out.println("Cliente no agregado " + cliente);
                }
            }
            case 4 -> {
                System.out.println("--- Eliminar cliente ---");
                System.out.println("ID Cliente: ");
                int id = Integer.parseInt(entrada.nextLine());
                Cliente cliente = new Cliente(id);
                boolean eliminado = clienteDAO.eliminarCliente(cliente);
                if (eliminado) {
                    System.out.println("Cliente eliminado " + cliente);
                } else {
                    System.out.println("Cliente no eliminado " + cliente);
                }
            }
            case 5 -> {
                System.out.println("--- Modificar cliente ---");
                System.out.println("ID Cliente: ");
                int id = Integer.parseInt(entrada.nextLine());
                System.out.println("Nombre: ");
                String nombre = entrada.nextLine();
                System.out.println("Apellido: ");
                String apellido = entrada.nextLine();
                System.out.println("Membresia: ");
                int membresia = Integer.parseInt(entrada.nextLine());
                Cliente cliente = new Cliente(id, nombre, apellido, membresia);
                boolean modificado = clienteDAO.modificarCliente(cliente);
                if (modificado) {
                    System.out.println("Cliente modificado " + cliente);
                } else {
                    System.out.println("Cliente no Modificado " + cliente);
                }
            }
            case 6 -> {
                System.out.println("Saliendo del programa...");
                salir = true;
            }
        }
        return salir;
    }
}

