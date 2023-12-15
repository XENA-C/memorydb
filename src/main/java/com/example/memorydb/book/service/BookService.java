package com.example.memorydb.book.service;

import com.example.memorydb.book.entity.BookEntity;
import com.example.memorydb.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor //생성자 메소드로 입력받겠다(lombok): 생성자 메소드 작성하지 않아도 자동생성
public class BookService {

    // @Autowired //final 없이 생성 가능 :
    private final BookRepository bookRepository;//초기화 시키기 위해
    // @Service를 통해 등록된 bookrepository(SimpleDataRepository를 상속)를 찾아서 주입

    public BookService(BookRepository bookRepository) { //스프링으로부터 생성자 주입
        //BookService 클래스가 생성될 때 BookService메소드를 통해 생성
        this.bookRepository = bookRepository;
    }

    // create, update
    public BookEntity create(BookEntity book){

        return bookRepository.save(book);
    }

    // all
    public List<BookEntity> findAll(){
        return bookRepository.findAll();
    }

    // delete


    // find one

}
