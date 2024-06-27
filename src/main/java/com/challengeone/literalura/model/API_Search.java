package com.challengeone.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record API_Search(
        Integer count,
        List<API_Book> results
) {
}
