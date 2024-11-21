package com.dao;

import com.domain.Departamento;
import com.domain.Empleado;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class EmpleadosDAO implements IDAO{
    static Scanner kin = new Scanner(System.in);
    private SessionFactory sessionFactory;
    @Getter
    private Session session;

    public EmpleadosDAO() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.session = sessionFactory.openSession();
    }

    @Override
    public void list() {
        String hql = "SELECT d FROM Empleado d";
        Query query = session.createQuery(hql);
        List<Empleado> list = query.getResultList();

        for (Empleado d : list) {
            System.out.println(d.toString());
        }
    }

    @Override
    public void update(int empno) {
        Query<Empleado> miQuery = session.createQuery("from Empleado where id = :empno");
        List<Empleado> listaEmpleados = miQuery.setParameter("empno", empno).list();

        Transaction transaction = session.beginTransaction();
        Empleado empleado = (Empleado) listaEmpleados.getFirst();
        empleado.setId(empno);

        session.update(empleado);
        transaction.commit();
    }

    @Override
    public void insert() {
        String nombre;
        String puesto;
        String depno;

        System.out.print("Ingrese el nombre del empleado: ");
        nombre = kin.nextLine();
        System.out.print("Ingrese el puesto del empleado: ");
        puesto = kin.nextLine();
        System.out.print("Ingrese el ID del departamento del empleado: ");
        depno = kin.nextLine();

        Departamento departamento;
        departamento = session.load(Departamento.class, depno);

        Transaction transaction = session.beginTransaction();
        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setPuesto(puesto);
        empleado.setDepno(departamento);
        session.save(empleado);
        transaction.commit();
    }

    @Override
    public void remove(int empno) {
        Query<Empleado> miQuery = session.createQuery("from Empleado where id = :empno");
        miQuery.setParameter("empno", empno);
        Empleado empleado = (Empleado) miQuery.uniqueResult();

        Transaction transaction = session.beginTransaction();
        session.delete(empleado);
        transaction.commit();
        System.out.println("Se ha eliminado el departamento con id: " + empno);
    }
}
