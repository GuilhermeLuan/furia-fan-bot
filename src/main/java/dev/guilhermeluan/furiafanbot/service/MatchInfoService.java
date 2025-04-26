package dev.guilhermeluan.furiafanbot.service;

import dev.guilhermeluan.furiafanbot.client.PandaScoreClient;
import dev.guilhermeluan.furiafanbot.client.dto.MatchDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchInfoService {
    private final PandaScoreClient pandaScoreClient;

    public MatchInfoService(PandaScoreClient pandaScoreClient) {
        this.pandaScoreClient = pandaScoreClient;
    }

    @Cacheable(value = "matches")
    public List<MatchDTO> getNextMatches() {
        return pandaScoreClient.getUpcomingMatches();
    }

    public List<MatchDTO> getPastMatches() {
        return pandaScoreClient.getPastMatches();
    }
}
