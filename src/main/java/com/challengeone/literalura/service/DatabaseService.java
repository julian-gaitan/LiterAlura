package com.challengeone.literalura.service;

import com.challengeone.literalura.entity.Author;
import com.challengeone.literalura.entity.Book;
import com.challengeone.literalura.repository.AuthorRepository;
import com.challengeone.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatabaseService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public  List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }
}
