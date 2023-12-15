package com.example.memorydb.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
public abstract class Entity implements PrimaryKey{ //Primary KEy를 구현하는 추상클래스
    //primary Key를 구현: 각각의 Entity(데이터)가 구현
    //저장소(Repository)-> 인터페이스(어떠한 데이터를 입력하는 기능)->데이터가 입력되는 저장공간

    @Getter @Setter
    private Long id;

}
