package dev.guilhermeluan.furiafanbot.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpponentDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("acronym")
    private String acronym;

    // Construtor padrão
    public OpponentDTO() {
    }

    // Getters e Setters
    public Long getId() { // <<< O método que estava faltando
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    // equals, hashCode, toString (Opcional, mas recomendado)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpponentDTO that = (OpponentDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(slug, that.slug) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(acronym, that.acronym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, slug, imageUrl, acronym);
    }

    @Override
    public String toString() {
        return "OpponentDTO{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", slug='" + slug + '\'' +
               ", imageUrl='" + imageUrl + '\'' +
               ", acronym='" + acronym + '\'' +
               '}';
    }
}