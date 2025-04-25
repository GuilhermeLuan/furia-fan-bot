package dev.guilhermeluan.furiafanbot.client.dto; // Adapte o pacote

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamDTO {

    @JsonProperty("raw_url")
    private String rawUrl;

    @JsonProperty("language")
    private String language;

    // Construtor Padrão
    public StreamDTO() {
    }

    // Getters
    public String getRawUrl() {
        return rawUrl;
    }

    public String getLanguage() {
        return language;
    }

    // Setters
    public void setRawUrl(String rawUrl) {
        this.rawUrl = rawUrl;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    // equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreamDTO streamDTO = (StreamDTO) o;
        return Objects.equals(rawUrl, streamDTO.rawUrl) &&
               Objects.equals(language, streamDTO.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawUrl, language);
    }

    @Override
    public String toString() {
        return "StreamDTO{" +
               "rawUrl='" + rawUrl + '\'' +
               ", language='" + language + '\'' +
               '}';
    }
}