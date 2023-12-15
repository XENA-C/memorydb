package com.example.memorydb.user.db;

import com.example.memorydb.db.SimpleDataRepository;
import com.example.memorydb.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//다른 패키지나 외부 프로젝트의 클래스는 어노테이션 붙일 수 없다
//--> Configuration + Bean 을 통해서 new 생성자 선언
//내부 클래스는 @Service를 사용
@Service
public interface UserRepository extends SimpleDataRepository<UserEntity, Long> {
    //UserEntity 타입 DataList 생성 (각각의 데이터 ID는 Long 타입)

    //Sql형식의 Java문법(메소드 형식)으로 쿼리 명령 실행
    public List<UserEntity> findAllByScoreGreaterThan(int score);

    //select *from user where score >= ??? and score <= ???
    List<UserEntity> findAllByScoreGreaterThanEqualAndScoreLessThanEqual(int min, int max);

    @Query( //쿼리를 직접 입력하여 실행하는 방법
          value = "select * from user as u where u.score >= :min AND u.score <= :max",
          nativeQuery = true //(default: false)//메소드 내 변수명을 매치
           //쿼리 메소드X, jpQLX

          // value = "select u from user u where u.score >= ?1 AND score <= ?2"
          //?1 : 첫번째 파라미터, ?2 : 두번째 파라미터
    )    List<UserEntity> score(//int min, int max);
            @Param(value = "min")int min, //네임드 파라미터--> 쿼리문에 value 입력
            @Param(value = "max")int max);


}
