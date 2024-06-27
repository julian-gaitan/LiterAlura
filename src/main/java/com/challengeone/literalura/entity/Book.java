package com.challengeone.literalura.entity;

import com.challengeone.literalura.model.API_Book;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    private Long id;
    private String title;
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Author author;
    private String languages;
    private Boolean copyright;
    private String media_type;
    private Integer download_count;

    public static Book GenerateFromRecord(API_Book record) {
        Book book = new Book();
        book.id = record.id();
        book.title = record.title();
        book.author = !record.authors().isEmpty() ? Author.GenerateFromRecord(record.authors().get(0)) : null;
        book.languages = record.languages().toString();
        book.copyright = record.copyright();
        book.media_type = record.media_type();
        book.download_count = record.download_count();
        return book;
    }
    public String toStringFormated() {
        return "Title:\t\t" + title + "\n" +
                "Author:\t\t" + (author != null ? author.toResumedString() : "N/A") + "\n" +
                "Language:\t" + languages + "\n" +
                "Copyright:\t" + copyright + "\n" +
                "Media:\t\t" + media_type + "\n" +
                "Downloads:\t" + download_count;
    }

    public Book() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public Integer getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Integer download_count) {
        this.download_count = download_count;
    }
}
