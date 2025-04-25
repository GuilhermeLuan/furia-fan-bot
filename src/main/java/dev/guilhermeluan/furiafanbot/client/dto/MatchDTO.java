package dev.guilhermeluan.furiafanbot.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("tournament")
    private TournamentDTO tournament;

    @JsonProperty("league")
    private LeagueDTO league;

    @JsonProperty("opponents")
    private List<OpponentWrapperDTO> opponents;

    @JsonProperty("begin_at")
    private OffsetDateTime beginAt;

    @JsonProperty("number_of_games")
    private int numberOfGames;

    @JsonProperty("streams_list")
    private List<StreamDTO> streamsList;

    public MatchDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TournamentDTO getTournament() {
        return tournament;
    }

    public void setTournament(TournamentDTO tournament) {
        this.tournament = tournament;
    }

    public LeagueDTO getLeague() {
        return league;
    }

    public void setLeague(LeagueDTO league) {
        this.league = league;
    }

    public List<OpponentWrapperDTO> getOpponents() {
        return opponents == null ? Collections.emptyList() : opponents;
    }

    public void setOpponents(List<OpponentWrapperDTO> opponents) {
        this.opponents = opponents;
    }

    public OffsetDateTime getBeginAt() {
        return beginAt;
    }

    public void setBeginAt(OffsetDateTime beginAt) {
        this.beginAt = beginAt;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public List<StreamDTO> getStreamsList() {
        return streamsList == null ? Collections.emptyList() : streamsList;
    }

    public void setStreamsList(List<StreamDTO> streamsList) {
        this.streamsList = streamsList;
    }

    public String getLeagueName() {
        return (this.league != null) ? this.league.getName() : "N/A";
    }

    public String getTournamentStageName() {
        return (this.tournament != null) ? this.tournament.getName() : "N/A";
    }


    public String getTeam1Name() {
        if (this.opponents != null && !this.opponents.isEmpty() && this.opponents.get(0).getOpponent() != null) {
            return this.opponents.get(0).getOpponent().getName();
        }
        return "Time 1 N/A";
    }

    public String getTeam2Name() {
        if (this.opponents != null && this.opponents.size() > 1 && this.opponents.get(1).getOpponent() != null) {
            return this.opponents.get(1).getOpponent().getName();
        }
        return "Time 2 N/A";
    }

    public String getMatchType() {
        return "MD" + this.numberOfGames;
    }

    public List<String> getStreamUrls() {
        if (this.streamsList == null || this.streamsList.isEmpty()) {
            return Collections.emptyList();
        }
        return this.streamsList.stream()
                .map(StreamDTO::getRawUrl)
                .filter(url -> url != null && !url.isBlank())
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchDTO matchDTO = (MatchDTO) o;
        return numberOfGames == matchDTO.numberOfGames &&
               Objects.equals(name, matchDTO.name) &&
               Objects.equals(tournament, matchDTO.tournament) &&
               Objects.equals(league, matchDTO.league) &&
               Objects.equals(opponents, matchDTO.opponents) &&
               Objects.equals(beginAt, matchDTO.beginAt) &&
               Objects.equals(streamsList, matchDTO.streamsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tournament, league, opponents, beginAt, numberOfGames, streamsList);
    }

    @Override
    public String toString() {
        return "MatchDTO{" +
               "name='" + name + '\'' +
               ", tournament=" + tournament +
               ", league=" + league +
               ", opponents=" + opponents +
               ", beginAt=" + beginAt +
               ", numberOfGames=" + numberOfGames +
               ", streamsList=" + streamsList +
               '}';
    }
}