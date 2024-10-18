package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.List;
import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {

    }

    public void zonaFitApp(){
        boolean salir = false;
        Scanner entrada = new Scanner(System.in);
        //Creamos un objeto de la clase ClienteDAO
        ClienteDAO clienteDAO = new ClienteDAO();
        while(!salir){
            salir = ejecutarOpciones(entrada,mostrarMenu(entrada),clienteDAO);
        }
    }

    private static int mostrarMenu(Scanner entrada){
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
            opcion = entrada.nextInt();
        } while (opcion<0 || opcion > 6);
        return opcion;
    }

    private static boolean ejecutarOpciones(Scanner entrada, int opcion, IClienteDAO clienteDAO){
        boolean salir = false;
        switch(opcion){
            case 1->{
                System.out.println("--- Lista de Clientes ---");
                List<Cliente> clientes = clienteDAO.listarClientes();
                for (Cliente client : clientes){
                    System.out.println(client.toString());
                }
            }
            case 2->{
                System.out.println("--- Buscar cliente ---");
            }
            case 3->{
                System.out.println("--- Agregar cliente ---");
            }
            case 4->{
                System.out.println("--- Eliminar cliente ---");
            }
            case 5->{
                System.out.println("--- Modificar cliente ---");
            }
            case 6->{
                salir = true;
            }
        }
        return salir;
    }
}

