package com.challengeone.literalura.repository;

import com.challengeone.literalura.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {

}
