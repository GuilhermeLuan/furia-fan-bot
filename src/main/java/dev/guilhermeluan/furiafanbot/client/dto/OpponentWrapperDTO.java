package dev.guilhermeluan.furiafanbot.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpponentWrapperDTO {

    @JsonProperty("opponent")
    private OpponentDTO opponent;

    public OpponentWrapperDTO() {
    }

    public OpponentDTO getOpponent() {
        return opponent;
    }

    public void setOpponent(OpponentDTO opponent) {
        this.opponent = opponent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpponentWrapperDTO that = (OpponentWrapperDTO) o;
        return Objects.equals(opponent, that.opponent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opponent);
    }

    @Override
    public String toString() {
        return "OpponentWrapperDTO{" +
               "opponent=" + opponent +
               '}';
    }
}