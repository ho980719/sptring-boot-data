package com.ho.dataspringboot.app.member;

import com.ho.dataspringboot.app.board.Board;
import com.ho.dataspringboot.app.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TBL_MEMBER")
@Getter
@NamedQuery(name = "Member.namedQueryTest", query = "select m from Member m where m.name = ?1")
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String loginId;
  private String password;
  private String name;

  // 부가 정보
  private LocalDate birth;
  @Enumerated(EnumType.STRING)
  private Gender gender;

  // 게시판
  @OneToMany(mappedBy = "member")
  Set<Board> boards = new HashSet<>();
}
