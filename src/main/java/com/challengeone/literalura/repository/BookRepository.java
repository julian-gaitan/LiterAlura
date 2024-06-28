package com.challengeone.literalura.repository;

import com.challengeone.literalura.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByLanguagesContaining(String language);
}
