package com.challengeone.literalura.repository;

import com.challengeone.literalura.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
