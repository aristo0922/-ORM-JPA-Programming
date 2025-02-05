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

@Data
@Entity
@Table(name = "MEMBER", uniqueConstraints = {@UniqueConstraint(
    name = "NAME_AGE_UNIQUE",
    columnNames = {"NAME", "AGE"}
)})
public class Member {

  @Id
  @Column(name = "MEMBER_ID")
  private String id;
  @Column(name = "NAME", nullable = false, length = 10)
  private String username;

  @ManyToOne
  @JoinColumn(name="TEAM_ID")
  private Team team;

  private Integer age;

  @Enumerated(EnumType.STRING)
  private RoleType roleType;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @Temporal(TemporalType.TIMESTAMP)
  private Date lastModifiedDate;

  @Lob
  private String description;

  public void setTeam(Team team) {
    this.team = team;
  }
}
