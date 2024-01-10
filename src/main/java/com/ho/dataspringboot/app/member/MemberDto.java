package com.ho.dataspringboot.app.member;

import com.ho.dataspringboot.app.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberDto {
  private Long id;
  private String name;
  private LocalDate birth;
  private Gender gender;

  public MemberDto(Long id, String name, LocalDate birth, Gender gender) {
    this.id = id;
    this.name = name;
    this.birth = birth;
    this.gender = gender;
  }
}
