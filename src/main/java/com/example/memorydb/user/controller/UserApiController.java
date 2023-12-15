package com.example.memorydb.user.controller;

import com.example.memorydb.user.model.UserEntity;
import com.example.memorydb.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
 //@Controller와의 차이??
@RestController //Http request, response를 처리하는 영역
@RequestMapping("/api/user")
@RequiredArgsConstructor //생성자 메소드로 채워라
public class UserApiController {

    //Service: controller로 부터 요청이 들어오면 비즈니스 로직을 처리하는 파트
    //request: Controller --> Service --> DB(repository)
    //response: DB-->Service-->Controller

    public final UserService userService; //스프링으로부터 주입

    @PutMapping("") //데이터 생성
    public UserEntity create(
            @RequestBody UserEntity userEntity){
       return userService.save(userEntity);
       //userEntity 데이터를 userService에 저장하여 return
    }

    @GetMapping("/all")
    public List<UserEntity> findAll(){
        return userService.findAll();
    }

    @DeleteMapping("/id/{id}")
     public void delete(
             @PathVariable Long id
    ){
        userService.delete(id);
    }

    //findBy id -> pathvariable
     @GetMapping("id/{id}")
     public UserEntity findOne(
             @PathVariable Long id
     ){
        var response = userService.findById(id);
        return response.get(); //null일 경우 null값 return
         //.get()
    }

    @GetMapping("/score")
    public List<UserEntity> filterScore(
            @RequestParam int score){
        return userService.filterScore(score);
    }

     @GetMapping("/min_max")
     public List<UserEntity> filterScore(
             @RequestParam int min,
             @RequestParam int max
     ){
         return userService.filterScore(min, max);
     }


}
