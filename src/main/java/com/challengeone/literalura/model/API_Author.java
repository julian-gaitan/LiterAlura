package com.challengeone.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record API_Author(
        String name,
        Integer birth_year,
        Integer death_year
) {
    public String toStringFormated() {
        return name + (birth_year != null && death_year != null ? " (" + birth_year + "-" + death_year + ")" : "");
    }
}
