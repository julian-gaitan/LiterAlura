package com.challengeone.literalura.repository;

import com.challengeone.literalura.entity.TestEnt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEnt, Long> {
}
