package com.challengeone.literalura.service;

import com.challengeone.literalura.entity.Author;
import com.challengeone.literalura.entity.Book;
import com.challengeone.literalura.repository.AuthorRepository;
import com.challengeone.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DatabaseService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findBooksByLanguage(String language) {
        return bookRepository.findByLanguagesContaining(language);
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> findAuthorsAliveForThatYear(Integer year) {
        return authorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(year, year);
    }

    public Set<String> findAllLanguages() {
        return findAllBooks().stream()
                .map(b -> b.getLanguages().substring(1, b.getLanguages().length()-1))
                .flatMap(l -> Stream.of(l.split(",")))
                .collect(Collectors.toSet());
    }
}
