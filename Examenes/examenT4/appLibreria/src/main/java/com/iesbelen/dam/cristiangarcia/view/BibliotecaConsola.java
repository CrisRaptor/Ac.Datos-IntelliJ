package com.iesbelen.dam.cristiangarcia.view;

import java.util.Scanner;
import java.util.List;

import com.iesbelen.dam.cristiangarcia.dao.AutorDAO;
import com.iesbelen.dam.cristiangarcia.dao.LibroDAO;
import com.iesbelen.dam.cristiangarcia.model.Autor;
import com.iesbelen.dam.cristiangarcia.model.Libro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class BibliotecaConsola {

    public static void main(String[] args) {
        // Configuración de Hibernate
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        AutorDAO autorDAO = new AutorDAO(sessionFactory);
        LibroDAO libroDAO = new LibroDAO(sessionFactory);

        escenario(sessionFactory.openSession());

        Scanner scanner = new Scanner(System.in);
        int opcion;
        boolean salir = false;
        while (!salir) {
            do {
                mostrarMenu();
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
            } while (opcion < 0 || opcion > 10);

            switch (opcion) {
                //MENU AUTORES
                case 1 -> crearAutor(autorDAO, scanner);
                case 2 -> obtenerAutorPorId(autorDAO, scanner);
                case 3 -> actualizarAutor(autorDAO, scanner);
                case 4 -> eliminarAutor(autorDAO, scanner);
                case 5 -> listarAutores(autorDAO);
                //MENU LIBROS
                case 6 -> crearLibro(libroDAO, scanner);
                case 7 -> obtenerLibroPorId(libroDAO, scanner);
                case 8 -> actualizarLibro(libroDAO, scanner);
                case 9 -> eliminarLibro(libroDAO, scanner);
                case 10 -> buscarLibrosPorTitulo(libroDAO, scanner);
                //Opcion Salir
                default -> salir = true;
            }
        }
        sessionFactory.close();
    }

    private static void mostrarMenu() {
        System.out.println("""
                ---    Menu Principal    ---
                --         AUTORES        --
                1. Crear autor
                2. Obtener autor por ID
                3. Actualizar autor
                4. Eliminar autor
                5. Listar todos los autores
                --         LIBROS         --
                6. Crear libro
                7. Obtener libro por ID
                8. Actualizar libro
                9. Eliminar libro
                10. Buscar libros por titulo
                --        0. Salir        --
                """);
        System.out.print("Indique una opción: ");
    }

    //ESCENARIO
    public static void escenario(Session session) {
        // Obtener una sesiónSession session = sessionFactory.openSession();
        session.beginTransaction();

        // Crear un nuevo autor
        Autor autor = new Autor();
        autor.setNombre("J.R.R. Tolkien");
        session.save(autor);
        // Crear un nuevo libro
        Libro libro = new Libro();
        libro.setTitulo("El hobbit");
        libro.setAutor(autor);
        session.save(libro);
        // Crear un nuevo libro
        Libro libro1 = new Libro();
        libro1.setTitulo("El Señor de los Anillos");
        libro1.setAutor(autor);
        session.save(libro1);
        // Crear un nuevo libro
        Libro libro2 = new Libro();
        libro2.setTitulo("El Silmarillion");
        libro2.setAutor(autor);
        session.save(libro2);

        // Crear un nuevo libro
        Libro libro3 = new Libro();
        libro3.setTitulo("La Comunidad del Anillo");
        libro3.setAutor(autor);
        session.save(libro3);
        System.out.println("Obras del autor: ");
        System.out.println(autor.toString());
        // Commit de la transacción
        session.getTransaction().commit();
        session.close();
    }

    //METODOS AUTORES
    private static void crearAutor(AutorDAO autorDao, Scanner scanner) {
        System.out.print("Ingrese el nombre del autor: ");
        String nombre = scanner.nextLine();
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autorDao.guardarAutor(autor);
        System.out.println("Autor creado exitosamente.");
    }

    private static void obtenerAutorPorId(AutorDAO autorDao, Scanner scanner) {
        System.out.print("Ingrese el ID del autor: ");
        Long id = scanner.nextLong();
        Autor autor = autorDao.obtenerAutorPorId(id);
        if (autor != null) {
            System.out.println("Autor encontrado:");
            System.out.println("ID: " + autor.getId());
            System.out.println("Nombre: " + autor.getNombre());
        } else {
            System.out.println("Autor no encontrado.");
        }
    }

    private static void actualizarAutor(AutorDAO autorDao, Scanner scanner) {
        System.out.print("Ingrese el ID del autor a actualizar: ");
        Long id = scanner.nextLong();
        Autor autor = autorDao.obtenerAutorPorId(id);
        if (autor != null) {
            System.out.print("Ingrese el nuevo nombre: ");
            String nuevoNombre = scanner.nextLine();
            autor.setNombre(nuevoNombre);
            autorDao.actualizarAutor(autor);
            System.out.println("Autor actualizado exitosamente.");
        } else {
            System.out.println("Autor no encontrado.");
        }
    }

    private static void eliminarAutor(AutorDAO autorDao, Scanner scanner) {
        System.out.print("Ingrese el ID del autor a eliminar: ");
        Long id = scanner.nextLong();
        autorDao.eliminarAutor(id);
        System.out.println("Autor eliminado exitosamente.");
    }

    private static void listarAutores(AutorDAO autorDao) {
        List<Autor> autores = autorDao.obtenerTodosLosAutores();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            System.out.println("Lista de autores:");
            for (Autor autor : autores) {
                System.out.println(autor.toString());
            }
        }
    }

    //METODOS LIBROS
    private static void crearLibro(LibroDAO libroDAO, Scanner scanner) {
        System.out.print("Ingrese el titlo del libro: ");
        String titulo = scanner.nextLine();
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libroDAO.guardarLibro(libro);
        System.out.println("Libro creado exitosamente.");
    }

    private static void obtenerLibroPorId(LibroDAO libroDAO, Scanner scanner) {
        System.out.print("Ingrese el ID del libro: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Libro libro = libroDAO.obtenerLibroPorId(id);
        if (libro != null) {
            System.out.println("Libro encontrado:");
            System.out.println("ID: " + libro.getId());
            System.out.println("Titulo: " + libro.getTitulo());
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private static void actualizarLibro(LibroDAO libroDAO, Scanner scanner) {
        System.out.print("Ingrese el ID del libro a actualizar: ");
        Long id = scanner.nextLong();
        Libro libro = libroDAO.obtenerLibroPorId(id);
        if (libro != null) {
            System.out.print("Ingrese el nuevo titulo: ");
            String nuevoTitulo = scanner.nextLine();
            libro.setTitulo(nuevoTitulo);
            libroDAO.actualizarLibro(libro);
            System.out.println("Libro actualizado exitosamente.");
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private static void eliminarLibro(LibroDAO libroDAO, Scanner scanner) {
        System.out.print("Ingrese el ID del libro a eliminar: ");
        Long id = scanner.nextLong();
        libroDAO.eliminarLibro(id);
        System.out.println("Libro eliminado exitosamente.");
    }

    private static void buscarLibrosPorTitulo(LibroDAO libroDAO, Scanner scanner) {
        System.out.print("Ingrese el tirulo del libro: ");
        String titulo = scanner.nextLine();
        List<Libro> libros = libroDAO.buscarLibrosPorTitulo(titulo);
        if (libros.isEmpty()) {
            System.out.println("Libro no encontrado.");
        } else {
            System.out.println("Lista de libros:");
            for (Libro libro : libros) {
                System.out.println(libro.toString());
            }
        }
    }
}
