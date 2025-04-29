package dev.guilhermeluan.furiafanbot.service;

import dev.guilhermeluan.furiafanbot.client.PandaScoreClient;
import dev.guilhermeluan.furiafanbot.client.dto.MatchDTO;
import dev.guilhermeluan.furiafanbot.client.dto.TeamDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchInfoService {
    private final PandaScoreClient pandaScoreClient;

    public MatchInfoService(PandaScoreClient pandaScoreClient) {
        this.pandaScoreClient = pandaScoreClient;
    }

    @Cacheable(value = "teamInfo")
    public List<TeamDTO> getFuriaTeamInfo() {
        return pandaScoreClient.getFuriaTeamInfo();
    }

    @Cacheable(value = "matches")
    public List<MatchDTO> getNextMatches() {
        return pandaScoreClient.getUpcomingMatches();
    }

    @Cacheable(value = "pastMatches")
    public List<MatchDTO> getPastMatches() {
        return pandaScoreClient.getPastMatches();
    }
}
