package com.challengeone.literalura.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "testingT")
public class TestEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String abc;

    public TestEnt() {
    }

    public String getAbc() {
        return abc;
    }

    public void setAbc(String abc) {
        this.abc = abc;
    }
}
