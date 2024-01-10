package com.ho.dataspringboot.app.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberQueryRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {

}
