package com.example.security.service;

import com.example.security.exception.NotFoundException;
import com.example.security.model.Book;
import com.example.security.model.DTO.BookDTO;
import com.example.security.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    public void deleteBook(int id){
        Book book = bookRepository.findById(id).get();
        bookRepository.delete(book);
    }
    @Transactional
    public Book createBook(BookDTO newBook){
        if(newBook.getDescription()==null){
            throw new NotFoundException("Description is null");
        }
        if(newBook.getTitle()==null){
            throw new NotFoundException("Title is null");
        }
        ModelMapper mapper = new ModelMapper();
        Book book = mapper.map(newBook,Book.class);
        bookRepository.save(book);
        return book;
    }
}
