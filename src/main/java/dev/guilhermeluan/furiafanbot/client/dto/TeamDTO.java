package dev.guilhermeluan.furiafanbot.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("location")
    private String location;

    @JsonProperty("players")
    private List<PlayerDTO> players;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("modified_at")
    private String modifiedAt;

    @JsonProperty("acronym")
    private String acronym;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("current_videogame")
    private VideogameDTO currentVideogame;

    // Getters e Setters
    public Long getId() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public VideogameDTO getCurrentVideogame() {
        return currentVideogame;
    }

    public void setCurrentVideogame(VideogameDTO currentVideogame) {
        this.currentVideogame = currentVideogame;
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", location='" + location + '\'' +
               ", players=" + players +
               ", slug='" + slug + '\'' +
               ", modifiedAt='" + modifiedAt + '\'' +
               ", acronym='" + acronym + '\'' +
               ", imageUrl='" + imageUrl + '\'' +
               ", currentVideogame=" + currentVideogame +
               '}';
    }
}