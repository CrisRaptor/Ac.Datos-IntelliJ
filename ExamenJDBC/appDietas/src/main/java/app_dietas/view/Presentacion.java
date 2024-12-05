package app_dietas.view;

import app_dietas.dao.CentrosDAO;
import app_dietas.model.Centro;
import app_dietas.model.Conexion;

import java.util.List;
import java.util.Scanner;

public class Presentacion {

    public static void main(String[] args) {
        boolean salir = false;
        Scanner entrada = new Scanner(System.in);
        int opcion;
        CentrosDAO centrosDAO = new CentrosDAO();
        Conexion conexion = new Conexion();
        //Esta linea borra la estructura para reiniciar en cada ejecucion
//        conexion.BorrarEstructura();
        while (!salir) {
            opcion = mostrarMenu(entrada);
            salir = ejecutarOpcion(entrada, opcion, centrosDAO, conexion);
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner entrada) {
        int opcion;
        String menu;

        menu = """
                --- APP DIETAS ---
                1. Crear la estructura de Centros Dietéticos
                2. Realizar la carga inicial de datos.
                3. Listar Centros
                4. Buscar Centros
                5. Agregar Centros
                6. Modificar Centros
                7. Eliminar Centros""";

        do {
            System.out.print(menu + "\n0. Salir\nIndica una opcion: ");
            opcion = Integer.parseInt(entrada.nextLine());
        } while (opcion < 0 || opcion > 7);

        return opcion;
    }

    private static boolean ejecutarOpcion(Scanner entrada, int opcion, CentrosDAO centrosDAO, Conexion conexion) {
        boolean salir = false;
        switch (opcion) {
            case 1 -> { //1. Crear la estructura de Centros Dietéticos
                if (conexion.CrearEstructura()) {
                    System.out.println("La estructura ha sido creada");
                }
            }
            case 2 -> { //2. Realizar la carga inicial de datos.
                if (conexion.CargaDeDatos()) {
                    System.out.println("Los datos han sido creados");
                }
            }
            case 3 -> { //3. Listar Centros
                System.out.println("--- Lista de Centros ---");
                List<Centro> centros = centrosDAO.ListarCentro(conexion);
                for (Centro centro : centros) {
                    System.out.println(centro.toString());
                }
            }
            case 4 -> { // 4. Buscar Centros
                System.out.println("--- Buscar Centro ---");
                System.out.println("Introduce el id del Centro a buscar: ");
                int id = Integer.parseInt(entrada.nextLine());
                Centro centro = new Centro(id);
                boolean encontrado = centrosDAO.BuscarCentro(centro, conexion);
                if (encontrado) {
                    System.out.println("Centro encontrado " + centro);
                } else {
                    System.out.println("Centro no encontrado " + centro);
                }
            }
            case 5 -> { //5. Agregar Centros
                System.out.println("--- Agregar Centro ---");
                System.out.println("Nombre: ");
                String nombre = entrada.nextLine();
                System.out.println("Direccion: ");
                String direccion = entrada.nextLine();
                System.out.println("Telefono: ");
                String telefono = entrada.nextLine();
                System.out.println("Email: ");
                String email = entrada.nextLine();
                System.out.println("Horario: ");
                String horario = entrada.nextLine();
                Centro centro = new Centro(nombre, direccion, telefono, email, horario);
                boolean agregado = centrosDAO.AgregarCentro(centro, conexion);
                if (agregado) {
                    System.out.println("Centro agregado " + centro);
                } else {
                    System.out.println("Centro no agregado " + centro);
                }
            }
            case 6 -> { //6. Modificar Centros
                System.out.println("--- Modificar Centro ---");
                System.out.println("ID Centro: ");
                int id = Integer.parseInt(entrada.nextLine());
                System.out.println("Nombre: ");
                String nombre = entrada.nextLine();
                System.out.println("Direccion: ");
                String direccion = entrada.nextLine();
                System.out.println("Telefono: ");
                String telefono = entrada.nextLine();
                System.out.println("Email: ");
                String email = entrada.nextLine();
                System.out.println("Horario: ");
                String horario = entrada.nextLine();
                Centro centro = new Centro(id, nombre, direccion, telefono, email, horario);
                boolean modificado = centrosDAO.AgregarCentro(centro, conexion);
                if (modificado) {
                    System.out.println("Centro modificado " + centro);
                } else {
                    System.out.println("Centro no modificado " + centro);
                }
            }
            case 7 -> { //7. Eliminar Centros
                System.out.println("--- Eliminar Centro ---");
                System.out.println("ID Centro: ");
                int id = Integer.parseInt(entrada.nextLine());
                Centro centro = new Centro(id);
                boolean eliminado = centrosDAO.EliminarCentro(centro, conexion);
                if (eliminado) {
                    System.out.println("Centro eliminado " + centro);
                } else {
                    System.out.println("Centro no eliminado " + centro);
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
