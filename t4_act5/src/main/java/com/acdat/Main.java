package com.acdat;

import com.acdat.domain.CuentaCredito;
import com.acdat.domain.CuentaDebito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();

            CuentaCredito cuentaCredito = new CuentaCredito();
            cuentaCredito.setTitular("Luismi");
            cuentaCredito.setBalance(500.0);
            cuentaCredito.setTipoInteres(0.20);
            cuentaCredito.setLimiteCredito(600.0);


            CuentaDebito cuentaDebito = new CuentaDebito();
            cuentaDebito.setTitular("Luismi");
            cuentaDebito.setBalance(200.0);
            cuentaCredito.setTipoInteres(0.10);
            cuentaDebito.setCargoPorDescubierto(6.5);

            entityManager.persist(cuentaCredito);
            entityManager.persist(cuentaDebito);

            transaction.commit();
        }finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}