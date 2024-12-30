package com.edu.jpa_programming.JPA.Programming;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence. createEntityManagerFactory("testdb");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    try{
      tx.begin();
      logic(em);
      tx.commit();
    }catch (Exception e){
      tx.rollback();
    } finally {
      em.close();
    }
    emf.close();
  }
  private static void logic(EntityManager em){

  }

}
