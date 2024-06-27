package com.challengeone.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record API_Book(
        Long id,
        String title,
        List<API_Author> authors,
        List<String> languages,
        Boolean copyright,
        String media_type,
        Integer download_count
) {
    public String toStringFormated() {
        return "Title:\t\t" + title + "\n" +
                "Author:\t\t" + (!authors.isEmpty() ? authors().get(0).toStringFormated() : "N/A") + "\n" +
                "Language:\t" + languages + "\n" +
                "Copyright:\t" + copyright + "\n" +
                "Media:\t\t" + media_type + "\n" +
                "Downloads:\t" + download_count;
    }
}
