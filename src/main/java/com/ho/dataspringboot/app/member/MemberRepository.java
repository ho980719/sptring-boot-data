package com.ho.dataspringboot.app.member;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Member namedQueryTest(String name);

  @Query(" select m "
      + " from Member m"
      + " inner join Board b on b.member.id = m.id"
      + " where m.id = :id ")
  Member findWithBoardJPQLById(@Param("id") Long id);

  @EntityGraph(attributePaths = {"boards"})
  Member findWithBoardEntityGraphById(long l);
}
