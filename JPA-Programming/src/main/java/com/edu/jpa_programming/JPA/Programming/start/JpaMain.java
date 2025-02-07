package com.edu.jpa_programming.JPA.Programming.start;

import com.edu.jpa_programming.JPA.Programming.domain.Member;
import com.edu.jpa_programming.JPA.Programming.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_mysql");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      logic(em);
      testSave(em);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }
    emf.close();
  }

  static void testSave(EntityManager em){
    Team team1 = new Team("team1", "팀1");
    em.persist(team1);

    Member member1 = new Member("member1", "회원1");
    member1.setTeam(team1);
    em.persist(member1);

    Member member2 = new Member("member2", "회원2");
    member2.setTeam(team1);
    em.persist(member2);
  }

  public void closeEntityManager(){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_mysql");
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    transaction.begin();

    Member memberA = em.find(Member.class, "memberA");
    Member memberB = em.find(Member.class, "memberB");

    transaction.commit();
    em.close();
  }

  public void testClearContext(EntityManagerFactory emf){
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    Member member = em.find(Member.class, "memberA");
    em.clear();
    member.setUsername("changedName");
  }

  public void testDetached(EntityManagerFactory emf){
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    Member member = new Member();
    member.setId("memberA");
    member.setUsername("회원A");

    em.persist(member);
    em.detach(member);

    transaction.commit();
  }

  private static void persist(EntityManagerFactory emf){
    Member memberA = new Member();
    Member memberB = new Member();
    Member memberC = new Member();
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();

    em.persist(memberA);
    em.persist(memberB);
    em.persist(memberC);
    Query query = em.createQuery("select m from Member m", Member.class);
    List<Member> members = query.getResultList();

    transaction.commit();
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
