package dev.guilhermeluan.furiafanbot.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDTO {

    @JsonProperty("id") // Adicionado para referência, se necessário
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("status") // Adicionado
    private String status;

    @JsonProperty("tournament")
    private TournamentDTO tournament;

    @JsonProperty("league")
    private LeagueDTO league;

    @JsonProperty("opponents")
    private List<OpponentWrapperDTO> opponents;

    @JsonProperty("begin_at")
    private OffsetDateTime beginAt;

    @JsonProperty("end_at") // Adicionado, pode ser útil para partidas finalizadas
    private OffsetDateTime endAt;

    @JsonProperty("number_of_games")
    private int numberOfGames;

    @JsonProperty("streams_list")
    private List<StreamDTO> streamsList;

    @JsonProperty("winner_id") // Adicionado
    private Long winnerId;

    // Opcional: Mapear o objeto winner completo se precisar de mais detalhes dele diretamente
    // @JsonProperty("winner")
    // private OpponentDTO winner;

    @JsonProperty("results") // Adicionado
    private List<ResultDTO> results;

    @JsonProperty("streamUrls")
    private List<String> streamUrls;

    // --- Construtores, Getters e Setters ---
    public MatchDTO() {
    }

    // Adicione getters e setters para os novos campos:
    // id, status, endAt, winnerId, results

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public TournamentDTO getTournament() { return tournament; }
    public void setTournament(TournamentDTO tournament) { this.tournament = tournament; }
    public LeagueDTO getLeague() { return league; }
    public void setLeague(LeagueDTO league) { this.league = league; }
    public List<OpponentWrapperDTO> getOpponents() { return opponents == null ? Collections.emptyList() : opponents; }
    public void setOpponents(List<OpponentWrapperDTO> opponents) { this.opponents = opponents; }
    public OffsetDateTime getBeginAt() { return beginAt; }
    public void setBeginAt(OffsetDateTime beginAt) { this.beginAt = beginAt; }
    public OffsetDateTime getEndAt() { return endAt; }
    public void setEndAt(OffsetDateTime endAt) { this.endAt = endAt; }
    public int getNumberOfGames() { return numberOfGames; }
    public void setNumberOfGames(int numberOfGames) { this.numberOfGames = numberOfGames; }
    public List<StreamDTO> getStreamsList() { return streamsList == null ? Collections.emptyList() : streamsList; }
    public void setStreamsList(List<StreamDTO> streamsList) { this.streamsList = streamsList; }
    public Long getWinnerId() { return winnerId; }
    public void setWinnerId(Long winnerId) { this.winnerId = winnerId; }
    public List<ResultDTO> getResults() { return results == null ? Collections.emptyList() : results; }
    public void setResults(List<ResultDTO> results) { this.results = results; }
    public void setStreamUrls(List<String> streamUrls) { this.streamUrls = streamUrls; }


    // --- Métodos Auxiliares (mantidos e novos) ---

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

    public Optional<OpponentDTO> getTeam1() {
        if (this.opponents != null && !this.opponents.isEmpty() && this.opponents.get(0).getOpponent() != null) {
            return Optional.of(this.opponents.get(0).getOpponent());
        }
        return Optional.empty();
    }

    public String getTeam2Name() {
        if (this.opponents != null && this.opponents.size() > 1 && this.opponents.get(1).getOpponent() != null) {
            return this.opponents.get(1).getOpponent().getName();
        }
        return "Time 2 N/A";
    }

    public Optional<OpponentDTO> getTeam2() {
        if (this.opponents != null && this.opponents.size() > 1 && this.opponents.get(1).getOpponent() != null) {
            return Optional.of(this.opponents.get(1).getOpponent());
        }
        return Optional.empty();
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

    // Novo método auxiliar para obter o nome do vencedor
    public Optional<String> getWinnerName() {
        if (this.winnerId == null || this.opponents == null || this.opponents.isEmpty()) {
            return Optional.empty();
        }
        return this.opponents.stream()
                .map(OpponentWrapperDTO::getOpponent)
                .filter(Objects::nonNull)
                .filter(opponent -> this.winnerId.equals(opponent.getId()))
                .map(OpponentDTO::getName)
                .findFirst();
    }

    // Novo método auxiliar para formatar o placar
    public String getFormattedScore() {
        if (results == null || results.size() != 2 || opponents == null || opponents.size() != 2) {
            return "Placar indisponível";
        }

        // Tenta obter os oponentes e seus IDs
        Optional<OpponentDTO> team1Opt = getTeam1();
        Optional<OpponentDTO> team2Opt = getTeam2();

        if (team1Opt.isEmpty() || team2Opt.isEmpty()) {
            return "Placar indisponível (Times não encontrados)";
        }

        OpponentDTO team1 = team1Opt.get();
        OpponentDTO team2 = team2Opt.get();

        // Encontra o placar para cada time
        Optional<Integer> score1Opt = results.stream()
                .filter(r -> team1.getId().equals(r.getTeamId()))
                .map(ResultDTO::getScore)
                .findFirst();
        Optional<Integer> score2Opt = results.stream()
                .filter(r -> team2.getId().equals(r.getTeamId()))
                .map(ResultDTO::getScore)
                .findFirst();

        if (score1Opt.isEmpty() || score2Opt.isEmpty()) {
            return "Placar indisponível (Resultados não encontrados)";
        }

        // Formata a string (ex: "FURIA 2 x 0 TheMongolz")
        // Poderia inverter a ordem se quisesse sempre o vencedor primeiro, mas assim é mais simples
        return String.format("%s %d x %d %s", team1.getName(), score1Opt.get(), score2Opt.get(), team2.getName());
    }


    // --- equals, hashCode, toString (Atualizados) ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchDTO matchDTO = (MatchDTO) o;
        return numberOfGames == matchDTO.numberOfGames &&
               Objects.equals(id, matchDTO.id) &&
               Objects.equals(name, matchDTO.name) &&
               Objects.equals(status, matchDTO.status) &&
               Objects.equals(tournament, matchDTO.tournament) &&
               Objects.equals(league, matchDTO.league) &&
               Objects.equals(opponents, matchDTO.opponents) &&
               Objects.equals(beginAt, matchDTO.beginAt) &&
               Objects.equals(endAt, matchDTO.endAt) &&
               Objects.equals(streamsList, matchDTO.streamsList) &&
               Objects.equals(winnerId, matchDTO.winnerId) &&
               Objects.equals(results, matchDTO.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, tournament, league, opponents, beginAt, endAt, numberOfGames, streamsList, winnerId, results);
    }

    @Override
    public String toString() {
        return "MatchDTO{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", status='" + status + '\'' +
               ", tournament=" + tournament +
               ", league=" + league +
               ", opponents=" + opponents +
               ", beginAt=" + beginAt +
               ", endAt=" + endAt +
               ", numberOfGames=" + numberOfGames +
               ", streamsList=" + streamsList +
               ", winnerId=" + winnerId +
               ", results=" + results +
               '}';
    }
}
