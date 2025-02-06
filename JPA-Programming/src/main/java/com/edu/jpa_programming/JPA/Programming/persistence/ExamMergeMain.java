package com.edu.jpa_programming.JPA.Programming.persistence;

import com.edu.jpa_programming.JPA.Programming.domain.Member;
import com.edu.jpa_programming.JPA.Programming.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ExamMergeMain {
  static EntityManagerFactory emf = Persistence.createEntityManagerFactory("testdb");

  public static void main(String args[]){
    Member member = createMember("memberA", "회원1");
    member.setUsername("changedName");
    mergeMember(member);
  }
  static void testSave(EntityManagerFactory emf){
    EntityManager em = emf.createEntityManager();
    Team team1 = new Team("team1", "팀1");
    em.persist(team1);

    Member member1 = new Member("member1", "회원1");
    member1.setTeam(team1);
    em.persist(member1);

    Member member2 = new Member("member2", "회원2");
    member2.setTeam(team1);
    em.persist(member2);
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
