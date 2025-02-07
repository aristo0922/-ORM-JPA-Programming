package com.edu.jpa_programming.JPA.Programming.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
//@Table(name = "MEMBER", uniqueConstraints = {@UniqueConstraint(
//    name = "NAME_AGE_UNIQUE",
//    columnNames = {"NAME", "AGE"}
//)})
public class Member {

  @Id
  @Column
  private String id;
//  @Column(name = "NAME", nullable = false, length = 10)
  private String username;

  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;

  private Integer age;

  @Column(name = "role_type")
  @Enumerated(EnumType.STRING)
  private RoleType roleType;

  @Column(name = "created_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @Column(name = "last_modified_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastModifiedDate;

  @Column
  @Lob
  private String description;

  public Member(String id, String username) {
    this.id = id;
    this.username = username;
  }

  public void setTeam(Team team) {
    if(this.team != null){
      this.team.getMembers().remove(this);
    }
    this.team = team;
    team.getMembers().add(this);
  }
}
