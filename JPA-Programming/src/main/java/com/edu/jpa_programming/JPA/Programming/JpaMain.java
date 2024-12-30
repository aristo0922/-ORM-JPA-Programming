package com.edu.jpa_programming.JPA.Programming;

import com.edu.jpa_programming.JPA.Programming.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("testdb");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      logic(em);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }
    emf.close();
  }

  private static void logic(EntityManager em) {
    String id = "id1";
    Member member = new Member();
    member.setId(id);
    member.setUsername("hey");
    member.setAge(2);

    em.persist(member);
    member.setAge(20);

    Member findMember = em.find(Member.class, id);
    System.out.println("findMember = " + findMember.getUsername()
        + ", age = " + findMember.getAge());

    List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
    System.out.println("members.size = " + members.size());
    em.remove(member);
  }

}
