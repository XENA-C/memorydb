package com.example.memorydb.db;

import com.example.memorydb.entity.Entity;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.*;
import java.util.stream.Collectors;
//타입에 제한을 둔다--> Entity상속받은 객체만, ID의 타입은 Long타입으로 제한
abstract public class SimpleDataRepository<T extends Entity, ID extends Long> implements DataRepository<T, ID>{
    //추상클래스--> 상속받는 하위클래스가 구현을 해도되고 안해도 되는....
    //Entity를 상속받은 데이터로 T 제한, ID는 Long 타입으로 제한
    //Entity는 Long타입의 변수를 갖는다--> T = Entity

    private List<T> dataList = new ArrayList<T>();
    //저장공간: 심플레파지토리를 받는 제네릭타입의 ArrayList

    private static long index = 0; //아이디의 고유성을 위해 번호 부여

    //데이터 정렬
    private Comparator<T> sort = new Comparator<T>() {
        @Override
        public int compare(T o1, T o2) {
            return Long.compare(o1.getId(), o2.getId());
        }
    };

    //create: save(저장)
    @Override
    public T save(T data){ //T(Entity, Long)타입의 data 선언

        if (Objects.isNull(data)){
            throw new RuntimeException("Data is null");
        }

        //Update Save(create) 동시에: 있으면 Update, 없으면 Save
        //DB에 데이터가 있는지 검색
        var prevData = dataList.stream()
                .filter(it->{
                    return it.getId().equals(data.getId()); //현재 Id와 데이터의 Id가 동일한 경우
                })
                .findFirst(); //findFirst() 조건에 일치하는 요소들 중에 Stream에서 순서가 가장 앞에 있는 요소를 리턴

        //검색 후 DB에 동일한 아이디가 있으면 Update, 없으면 Insert
        if (prevData.isPresent()){ //기존 데이터 있는 경우 업데이트
            dataList.remove(prevData.get()); //이전 데이터를 지우고
            //prevData의 타입은 Optional데이터 = Optional<UserEntity>가 들어있다
            //-->.get() 메소드를 사용해야 삭제 가능
            dataList.add(data); // 새로운 데이터를 입력->검색된 데이터의 고유번호는 유지

        }else { //없는 경우 데이터 저장
            index++;
            data.setId(index); //다음 번호의 아이디 부여
            dataList.add(data); //데이터 저장
        }

        //데이터의 고유아이디 지정--> 제네릭(타입미지정)이므로 setId 불가 --> 타입을 지정
        data.setId(index); //T는 Entity -> data.setId(index)지정 가능
        dataList.add(data);

        index++; //데이터의 고유아이디가 증가하면서 자동생성
        return data; //--> 데이터 저장 후 반환
    }

    //read
    @Override
    public Optional<T> findById(ID id){ // Data가 있을 수도 있고 없을 수도 -->Optional
        return dataList.stream()
                .filter(it->{
                    return (it.getId().equals(id));
                })
                .findFirst();
    }

    @Override
    public List<T> findAll(){
        return dataList.stream()
                .sorted(sort)
                .collect(Collectors.toList());
    }
    
    //delete
    @Override
    public void delete(ID id){
        var deleteEntity = dataList.stream()
               .filter(it->{
                    return (it.getId().equals(id));
                })
                .findFirst();

        if (deleteEntity.isPresent()){
            dataList.remove(deleteEntity.get());
        }
    }

}
