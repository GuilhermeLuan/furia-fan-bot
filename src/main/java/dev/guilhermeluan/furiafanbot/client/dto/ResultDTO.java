package dev.guilhermeluan.furiafanbot.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDTO {

    @JsonProperty("team_id")
    private Long teamId;

    @JsonProperty("score")
    private int score;

    // Construtor padrão
    public ResultDTO() {
    }

    // Getters e Setters
    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultDTO resultDTO = (ResultDTO) o;
        return score == resultDTO.score && Objects.equals(teamId, resultDTO.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, score);
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
               "teamId=" + teamId +
               ", score=" + score +
               '}';
    }
}