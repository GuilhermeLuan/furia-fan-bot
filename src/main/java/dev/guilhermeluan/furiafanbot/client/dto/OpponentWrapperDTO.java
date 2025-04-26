package dev.guilhermeluan.furiafanbot.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpponentWrapperDTO {

    @JsonProperty("opponent")
    private OpponentDTO opponent; // <<< Referência ao OpponentDTO

    @JsonProperty("type")
    private String type; // Geralmente "Team"

    // Construtor padrão
    public OpponentWrapperDTO() {
    }

    // Getters e Setters
    public OpponentDTO getOpponent() {
        return opponent;
    }

    public void setOpponent(OpponentDTO opponent) {
        this.opponent = opponent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // equals, hashCode, toString (Opcional)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpponentWrapperDTO that = (OpponentWrapperDTO) o;
        return Objects.equals(opponent, that.opponent) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opponent, type);
    }

    @Override
    public String toString() {
        return "OpponentWrapperDTO{" +
               "opponent=" + opponent +
               ", type='" + type + '\'' +
               '}';
    }
}
