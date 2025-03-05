package com.presentation;

import com.dao.DepartamentosDAO;

import java.util.Scanner;

public class Main {
    static Scanner kin = new Scanner(System.in);
    public static void main(String[] args) {
        runMenu();
    }

    public static int menu() {
        int opcion;

        System.out.println("""
                --- Menu Principal ---
                1. Listar departamentos.
                2. Actualizar departamento.
                3. Insertar departamento.
                4. Eliminar departamento.
                0. Salir.
                """);
        System.out.print("Indique una opción: ");
        opcion = kin.nextInt();

        return opcion;
    }

    public static void runMenu() {
        int opcion;

        do {
            opcion = menu();
            DepartamentosDAO departamentoDAO = new DepartamentosDAO();

            switch (opcion) {
                case 1 -> departamentoDAO.list();
                case 2 -> {
                    int depno;
                    System.out.print("Indique el departamento: ");
                    depno = kin.nextInt();
                    departamentoDAO.update(depno);
                }
                case 3 -> departamentoDAO.insert();
                case 4 -> {
                    int depno;
                    System.out.print("Indique el departamento: ");
                    depno = kin.nextInt();
                    departamentoDAO.remove(depno);
                }
                case 0 -> System.out.println("Hasta luego");
            }
        } while (opcion != 0);
    }
}