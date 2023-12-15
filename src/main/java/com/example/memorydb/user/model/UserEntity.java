package com.example.memorydb.user.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Data @EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder @Entity (name ="user") //Entity는 반드시 프라이머리 키에 해당하는 Id를 갖는다 //user테이블과 연결
public class UserEntity extends com.example.memorydb.entity.Entity {//UserEntiry: 데이터베이스에 저장할 데이터

    @Id //프라이머리키
    @GeneratedValue(strategy= GenerationType.IDENTITY) //Id값 생성방식 지정(autoIncrement)
    private Long id;

    private String name;
    private int score;

}
