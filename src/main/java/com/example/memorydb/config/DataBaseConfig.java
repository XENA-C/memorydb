//package com.example.memorydb.config;
//
//import com.example.memorydb.user.db.UserRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
////
//@Configuration
////스프링이 실행될 떄 Configuration이 적용되는 객체(다른 프로젝트 파일)를 찾아서 다른 클래스에서 new생성자로 객체 생성할 수 있도록 힘)
////내부클래스는 @Service 어노테이션을 붙임
//public class DataBaseConfig {
//
//    @Bean //스프링에 의해 관리: 다른 클래스에서 호출 가능
//    public UserRepository userRepository(){
//        return new UserRepository();
//        //패키지 내부 클래스 @Service 어노테이션으로 주입 가능
//        //패키지 외부 클래스는 편집(Bean 등록)불가 -->
//        // new 생성자를 통해 생성 후 @Bean 등록 -->
//        // 패키지 내부 클래스에 생성 주입
//    }
//
//
//}
