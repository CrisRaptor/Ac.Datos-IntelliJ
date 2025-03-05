package com.jetbrains.dao;

import com.acdat.domain.Direccion;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DAO {
    private SessionFactory sessionFactory;
    private Session session;

    public DAO() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.session = sessionFactory.openSession();
    }

//    public void listar(){
//        String hql = "SELECT d from Direccion d";
//        Query query = session.createQuery(hql);
//        List<Direccion> listDirecciones = query.getResultList();
//        for (Direccion d : listDirecciones) {
//            System.out.println("Direccion: " + d);
//        }
//    }
}
