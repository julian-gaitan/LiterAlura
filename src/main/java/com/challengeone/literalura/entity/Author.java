package com.challengeone.literalura.entity;

import com.challengeone.literalura.model.API_Author;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;

    public static Author GenerateFromRecord(API_Author record) {
        Author author = new Author();
        author.name = record.name();
        author.birthYear = record.birth_year();
        author.deathYear = record.death_year();
        return author;
    }

    public String toStringFormated() {
        return "Name:\t" + name + "\n" +
                "Born:\t" + birthYear + "\n" +
                "Died:\t" + deathYear + "\n" +
                "Books:\t" + books.stream().map(Book::getTitle).collect(Collectors.joining(" | "));
    }

    public String toResumedString() {
        return name + (birthYear != null && deathYear != null ? " (" + birthYear + "-" + deathYear + ")" : "");
    }

    public Author() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        books.forEach(b -> b.setAuthor(this));
        this.books = books;
    }
}
