package com.edu.jpa_programming.JPA.Programming.domain;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberTest {
  static EntityManager em;
  static EntityTransaction tx;

  @BeforeEach
  void init(){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_mysql");
    em = emf.createEntityManager();
    tx = em.getTransaction();
  }

  @Test
  void testSave(){
    Team team1 = new Team("team1", "팀1");
    em.persist(team1);

    Member member1 = new Member("member1", "회원1");
    member1.setTeam(team1);
    em.persist(member1);

    Member member2 = new Member("member2", "회원2");
    member2.setTeam(team1);
    em.persist(member2);
  }

  @Test
  void testJoin(){
    queryLogicJoin(em);
    updateRelation(em);
  }
  private static void queryLogicJoin(EntityManager em){
    String jpql = "select m from Member m join m.team t where "+"t.name =:teamName";
    List<Member> resultList = em.createQuery(jpql, Member.class)
        .setParameter("teamName", "팀1")
        .getResultList();

    for (Member member:resultList){
      System.out.println("[query] member.username = "+ member.getUsername());
    }
  }

  private static void updateRelation(EntityManager em){
    Team team2 = new Team("team2", "팀2");
    em.persist(team2);

    Member member = em.find(Member.class, "member1");
    System.out.println(member);
    member.setTeam(team2);
  }
}