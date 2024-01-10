package com.ho.dataspringboot;

import com.ho.dataspringboot.app.member.Member;
import com.ho.dataspringboot.app.member.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Slf4j
@DataJpaTest
class DataSpringBootApplicationTests {
  @Autowired
  MemberRepository memberRepository;

  @Autowired
  EntityManager entityManager;
  @Test
  void namedQueryAnnotationTest() {
    Member member = memberRepository.namedQueryTest("김준호");
    log.debug("member => {}", member.getName());
  }
}
