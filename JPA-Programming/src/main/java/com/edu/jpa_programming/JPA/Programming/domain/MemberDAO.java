package com.edu.jpa_programming.JPA.Programming.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MemberDAO {

  public void setDataSource(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  private JdbcTemplate jdbcTemplate;

  private RowMapper<Member> memberMapper = new RowMapper<Member>() {
    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
      Member member = new Member();
      member.setId(rs.getString("MEMBER_ID"));
      member.setUsername(rs.getString("NAME"));
      return member;
    }
  };

  public Member find(String memberId) {
    return this.jdbcTemplate.queryForObject("SELECT * FROM MEMBER WHERE MEMBER_ID = ?",
        new Object[]{memberId}, this.memberMapper);
  }

  public void save(Member member) {
    String sql = "INSERT INTO MEMBER(MEMBER_ID, NAME) VALUES(?, ?)";
    this.jdbcTemplate.update(sql, member.getId(), member.getUsername());
  }

}
