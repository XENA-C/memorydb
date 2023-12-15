package com.example.memorydb.entity;

import lombok.Data;


public interface PrimaryKey {

    void setId(Long id);//각 데이터의 고유한 키: 프라이머리 키
    Long getId();

}
