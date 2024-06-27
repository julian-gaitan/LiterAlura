package com.challengeone.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record API_Book(
    Integer id,
    String title
) {
}
