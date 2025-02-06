package com.edu.jpa_programming.JPA.Programming.persistence;

import com.edu.jpa_programming.JPA.Programming.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ExamMergeMain {
  static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_mysql");

  public static void main(String args[]){
    Member member = createMember("memberA", "회원1");
    member.setUsername("changedName");
    mergeMember(member);
  }

  static Member createMember(String id, String username){
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx1 = em.getTransaction();
    tx1.begin();

    Member member = new Member();
    member.setId(id);
    member.setUsername(username);
    member.setAge(2);

    em.persist(member);
    tx1.commit();
    em.close();

    return member;
  }

  static void mergeMember(Member member){
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    tx.begin();
    Member mergedMember = em.merge(member);
    tx.commit();

    System.out.println("member = " + member.getUsername());
    System.out.println("mergedMember = " + mergedMember.getUsername());

    System.out.println("merged member em contains member = " + em.contains(member));
    System.out.println("merged member em contains mergeMember = " + em.contains(mergedMember));
    em.close();
  }

}
