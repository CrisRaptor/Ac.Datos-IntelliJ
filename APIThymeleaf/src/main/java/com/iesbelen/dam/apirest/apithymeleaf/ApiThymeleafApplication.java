package com.iesbelen.dam.apirest.apithymeleaf;

import com.iesbelen.dam.apirest.apithymeleaf.vista.GestorEmpleados;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ApiThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiThymeleafApplication.class, args);
//        Scanner sc = new Scanner(System.in);
//        GestorEmpleados gestor = new GestorEmpleados();
//        int opcion = mostrarMenu(sc);
//
//        switch (opcion) {
//            case 1 ->
//                    gestor.getRequest();
//            case 2 ->
//                    gestor.postRequest();
//            case 3 -> {
//                String id = "";
//                do {
//                    System.out.print("Ingrese el id del cliente a eliminar: ");
//                    id = sc.nextLine();
//                } while (id.isBlank());
//                gestor.deleteRequest(id);
//            }
//            case 4 ->
//                    gestor.updateRequest();
//        }
    }

    private static int mostrarMenu(Scanner entrada) {
        int opcion;
        String menu;

        menu = """
                --- MENU EMPLEADOS ---
                1. Listar empleados
                2. Agregar empleado
                3. Eliminar empleado
                4. Actualizar empleado""";

        do {
            System.out.println(menu + "\n0. Salir");
            opcion = Integer.parseInt(entrada.nextLine());
        } while (opcion < 0 || opcion > 6);

        return opcion;
    }
}
