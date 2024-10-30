package zona_fit.presentacion;

import zona_fit.conexion.Conexion;
import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;
import zona_fit.dominio.Cuota;

import java.util.List;
import java.util.Scanner;

public class ZonaFitApp {
    private static boolean bbddMejorada = false;
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
        String menu;

        if (bbddMejorada) {
            menu = """
                    --- MENU ZONA FIT ---
                    1. Listar clientes
                    2. Buscar cliente
                    3. Agregar cliente
                    4. Eliminar cliente
                    5. Modificar cliente
                    6. Listar Cuotas
                    7. Agregar Cuotas
                    8. Buscar cuotas
                    9. Eliminar cuotas
                    10. Modificar cuotas
                    11. Cargar Base de Datos""";
        } else {
            menu = """
                    --- MENU CLIENTES ---
                    1. Listar clientes
                    2. Buscar cliente
                    3. Agregar cliente
                    4. Eliminar cliente
                    5. Modificar cliente
                    6. Mejorar Base de Datos
                    (Crear tabla de cuotas y modificar la tabla clientes)""";
        }
        do {
            System.out.println(menu + "\n0. Salir");
            opcion = Integer.parseInt(entrada.nextLine());
        } while (opcion < 0 || opcion > ((bbddMejorada)?11:6));

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
                boolean encontrado = clienteDAO.buscarClientePorId(cliente, bbddMejorada);
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
                boolean agregado = clienteDAO.agregarCliente(cliente, bbddMejorada);
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
                boolean modificado = clienteDAO.modificarCliente(cliente, bbddMejorada);
                if (modificado) {
                    System.out.println("Cliente modificado " + cliente);
                } else {
                    System.out.println("Cliente no Modificado " + cliente);
                }
            }
            case 6 -> {//Mejora base de datos, si ya esta mejorada esta opcion lista cuotas
                if (bbddMejorada) {
                    System.out.println("--- Lista de Cuotas ---");
                    List<Cuota> cuotas = clienteDAO.listarCuota();
                    for (Cuota cuota : cuotas) {
                        System.out.println(cuota.toString());
                    }

                } else {
                    boolean mejorado;
                    Conexion con = new Conexion();
                    mejorado = con.mejorarEstructura();
                    if (mejorado) {
                        System.out.println("Base de datos mejorada");
                        ejecutarOpciones(entrada, 1, clienteDAO);
                        bbddMejorada = true;
                    } else {
                        System.out.println("Base de datos no mejorada");
                    }
                }

            }
            case 7 -> {
                System.out.println("--- Buscar cuota ---");
                System.out.println("Introduce el id de la Cuota a buscar: ");
                int id = Integer.parseInt(entrada.nextLine());
                Cuota cuota = new Cuota(id);
                boolean encontrado = clienteDAO.buscarCuotaPorId(cuota);
                if (encontrado) {
                    System.out.println("Cuota encontrada " + cuota);
                } else {
                    System.out.println("Cuota no encontrada " + cuota);
                }
            }
            case 8 -> {
                System.out.println("--- Agregar cuota ---");
                System.out.println("Modalidad: ");
                String modalidad = entrada.nextLine();
                System.out.println("Valor de cuota: ");
                double cuotaValor = Double.parseDouble(entrada.nextLine());
                int eleccion;
                do {
                    System.out.println("Situacion (1-'Inactivo' / 2-'Activo'): ");
                    eleccion = Integer.parseInt(entrada.nextLine());
                } while (eleccion < 1 || eleccion > 2);
                String situacion = (eleccion == 1)?"inactivo":"activo";
                Cuota cuota = new Cuota(modalidad, cuotaValor, situacion);
                boolean agregado = clienteDAO.agregarCuota(cuota);
                if (agregado) {
                    System.out.println("Cuota agregada " + cuota);
                } else {
                    System.out.println("Cuota no agregada " + cuota);
                }
            }
            case 9 -> {
                System.out.println("--- Eliminar cuota ---");
                System.out.println("ID Cuota: ");
                int id = Integer.parseInt(entrada.nextLine());
                Cuota cuota = new Cuota(id);
                boolean eliminado = clienteDAO.eliminarCuota(cuota);
                if (eliminado) {
                    System.out.println("Cuota eliminado " + cuota);
                } else {
                    System.out.println("Cuota no eliminado " + cuota);
                }
            }
            case 10 -> {
                System.out.println("--- Modificar cuota ---");
                System.out.println("ID Cuota: ");
                int id = Integer.parseInt(entrada.nextLine());
                System.out.println("Modalidad: ");
                String modalidad = entrada.nextLine();
                System.out.println("Valor de cuota: ");
                double cuotaValor = Double.parseDouble(entrada.nextLine());
                int eleccion;
                do {
                    System.out.println("Situacion (1-'Inactivo' / 2-'Activo'): ");
                    eleccion = Integer.parseInt(entrada.nextLine());
                } while (eleccion < 1 || eleccion > 2);
                String situacion = (eleccion == 1)?"inactivo":"activo";
                Cuota cuota = new Cuota(id, modalidad, cuotaValor, situacion);
                boolean modificado = clienteDAO.modificarCuota(cuota);
                if (modificado) {
                    System.out.println("Cuota modificada " + cuota);
                } else {
                    System.out.println("Cuota no Modificada " + cuota);
                }
            }
            case 11 -> {
                boolean cargado;
                Conexion con = new Conexion();
                cargado = con.cargaDatos("carga_datos.sql");
                if (cargado) {
                    System.out.println("Base de datos cargada");
                    ejecutarOpciones(entrada, 1, clienteDAO);
                } else {
                    System.out.println("Base de datos no cargada");
                }
            }
            default -> {
                System.out.println("Saliendo del programa...");
                salir = true;
            }
        }
        return salir;
    }
}

