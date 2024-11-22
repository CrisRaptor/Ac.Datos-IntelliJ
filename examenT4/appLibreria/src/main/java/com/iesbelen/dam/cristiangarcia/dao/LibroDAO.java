package com.iesbelen.dam.cristiangarcia.dao;

import com.iesbelen.dam.cristiangarcia.model.Libro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class LibroDAO {
    private SessionFactory sessionFactory;

    public LibroDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void guardarLibro(Libro libro) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(libro);
        session.getTransaction().commit();
        session.close();
    }

    public Libro obtenerLibroPorId(Long id) {
        Session session = sessionFactory.openSession();
        Libro libro = session.get(Libro.class, id);
        session.close();
        return libro;
    }

    public void actualizarLibro(Libro libro) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(libro);
        session.getTransaction().commit();
        session.close();
    }

    public void eliminarLibro(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Libro libro = session.get(Libro.class, id);
        session.delete(libro);
        session.getTransaction().commit();
        session.close();
    }

    // Metodo adicional para buscar libros por título
    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Libro l where l.titulo like :titulo");
        query.setParameter("titulo", "%" + titulo + "%");
        List<Libro> libros = query.list();
        session.close();
        return libros;
    }
}
