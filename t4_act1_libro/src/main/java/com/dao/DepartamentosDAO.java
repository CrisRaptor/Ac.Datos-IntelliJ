package com.dao;

import com.domain.Departamento;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class DepartamentosDAO implements IDAO{
    static Scanner kin = new Scanner(System.in);
    private SessionFactory sessionFactory;
    @Getter
    private Session session;

    public DepartamentosDAO() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.session = sessionFactory.openSession();
    }

    @Override
    public void list() {
        String hql = "SELECT d FROM Departamento d";
        Query query = session.createQuery(hql);
        List<Departamento> listDirecciones = query.getResultList();

        for (Departamento d : listDirecciones) {
            System.out.println(d.toString());
        }
    }

    @Override
    public void update(int depno) {
        Query<Departamento> miQuery = session.createQuery("from Departamento where id = :depno");
        List<Departamento> listaDepartamentos = miQuery.setParameter("depno", depno).list();

        Transaction transaction = session.beginTransaction();
        Departamento departamento = (Departamento) listaDepartamentos.getFirst();
        departamento.setId(depno);

        session.update(departamento);
        transaction.commit();
    }

    @Override
    public void insert() {
        String nombre;
        String ubicacion;

        System.out.print("Ingrese el nombre del departamento: ");
        nombre = kin.nextLine();
        System.out.print("Ingrese la ubicación del departamento: ");
        ubicacion = kin.nextLine();

        Transaction transaction = session.beginTransaction();
        Departamento departamento = new Departamento();
        departamento.setNombre(nombre);
        departamento.setUbicacion(ubicacion);
        session.save(departamento);
        transaction.commit();
    }

    @Override
    public void remove(int depno) {
        Query<Departamento> miQuery = session.createQuery("from Departamento where id = :depno");
        miQuery.setParameter("depno", depno);
        Departamento departamento = (Departamento) miQuery.uniqueResult();

        Transaction transaction = session.beginTransaction();
        session.delete(departamento);
        transaction.commit();
        System.out.println("Se ha eliminado el departamento con id: " + depno);
    }
}
