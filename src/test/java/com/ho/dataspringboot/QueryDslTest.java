package com.ho.dataspringboot;

import com.ho.dataspringboot.app.board.BoardRepository;
import com.ho.dataspringboot.app.board.QBoard;
import com.ho.dataspringboot.app.enums.Gender;
import com.ho.dataspringboot.app.member.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@Sql(value = {"classpath:db/h2/member.sql", "classpath:db/h2/board.sql"})
public class QueryDslTest {
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private MemberQueryRepository memberQueryRepository;

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private EntityManager entityManager;

  QMember member = QMember.member;
  QBoard board = QBoard.board;

  //@formatter:off
  @Test
  void queryDslTest() {
    JPAQuery<Void> query = new JPAQuery<Void>(entityManager);
    Member member1 = query.select(member)
        .from(member)
        .where(member.name.eq("김준호"))
        .fetchOne();

    assert member1 != null;
    assertEquals("ho980719", member1.getLoginId());
    assertEquals("1234", member1.getPassword());
    assertEquals("김준호", member1.getName());
  }

  @Test
  void projectionTest() {
    JPAQuery<Void> query = new JPAQuery<>(entityManager);
    MemberDto member1 = query.select(Projections.constructor(MemberDto.class,
            member.id, member.name, member.birth, member.gender))
        .from(member)
        .where(member.name.eq("김준호"))
        .fetchOne();

    assert member1 != null;
    assertEquals(1L, member1.getId());
    assertEquals("김준호", member1.getName());
    assertEquals(LocalDate.of(1998, 7, 19), member1.getBirth());
    assertEquals(Gender.MALE, member1.getGender());
  }

  @Test
  void getBoards() {
    Member member = memberRepository.findById(1L).orElse(null);

    Member member1 = memberRepository.findWithBoardJPQLById(1L);

    Member member2 = memberRepository.findWithBoardEntityGraphById(1L);

    log.debug("hello");
  }

  //@formatter:on
}
