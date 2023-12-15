package com.example.memorydb.book.entity;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="Book") //"Book"테이블과 연결
public class BookEntity { //상속받지 않음

    @Id //프라이머리 키
    @GeneratedValue(strategy = GenerationType.IDENTITY)//데이터베이스 내에서 작동
    private Long id;

    private String name;
    private String category;
    private BigDecimal amount;

}
