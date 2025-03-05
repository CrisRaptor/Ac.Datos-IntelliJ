package com.presentation;

import com.dao.DepartamentosDAO;
import com.dao.EmpleadosDAO;

import java.util.Scanner;

public class Main {
    static Scanner kin = new Scanner(System.in);
    public static void main(String[] args) {
        runMenu();
    }

    public static int menu() {
        int opcion;

        System.out.println("""
                --- Menu  Principal ---
                --   Departamentos   --
                1. Listar departamentos.
                2. Actualizar departamento.
                3. Insertar departamento.
                4. Eliminar departamento.
                --     Empleados     --
                5. Listar empleados
                6. Actualizar empleado.
                7. Insertar empleado.
                8. Eliminar empleado.
                --     0. Salir      --
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
            EmpleadosDAO empleadoDAO = new EmpleadosDAO();

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
                    String seguro;
                    System.out.print("Indique el departamento: ");
                    depno = kin.nextInt();
                    System.out.println("¿Estas seguro?(S/N)");
                    if (kin.next().equalsIgnoreCase("S")) {
                        departamentoDAO.remove(depno);
                    }

                }
                case 5 -> empleadoDAO.list();
                case 6 -> {
                    int empno;
                    System.out.print("Indique el empleado: ");
                    empno = kin.nextInt();
                    empleadoDAO.update(empno);
                }
                case 7 -> empleadoDAO.insert();
                case 8 -> {
                    int empno;
                    String seguro;
                    System.out.print("Indique el empleado: ");
                    empno = kin.nextInt();
                    empleadoDAO.remove(empno);
                    System.out.println("¿Estas seguro?(S/N)");
                    if (kin.next().equalsIgnoreCase("S")) {
                        departamentoDAO.remove(empno);
                    }
                }
                case 0 -> System.out.println("Hasta luego");
            }
        } while (opcion != 0);
    }
}