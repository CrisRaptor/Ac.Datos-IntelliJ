package com.acdat.dao;

import com.acdat.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO {
    SessionFactory sessionFactory;
    Session session;

    public UserDAO() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.session = sessionFactory.openSession();
    }

    public void listar() {
        String hql ="SELECT u FROM User u";
        Query<User> query = session.createQuery(hql, User.class);
        List<User> listaUsuarios = query.getResultList();

        for (User user : listaUsuarios) {
            System.out.println("Usuario: " + user.toString());
        }
    }
}
