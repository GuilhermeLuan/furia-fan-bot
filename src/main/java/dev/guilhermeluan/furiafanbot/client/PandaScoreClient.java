package dev.guilhermeluan.furiafanbot.client;

import dev.guilhermeluan.furiafanbot.client.dto.MatchDTO;
import dev.guilhermeluan.furiafanbot.config.PandaScoreClientConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        value = "pandascore", url = "https://api.pandascore.co/csgo",
        configuration = PandaScoreClientConfiguration.class
)
public interface PandaScoreClient {

    @Value("${panda_score.api.token}")
    String PANDA_SCORE_API_KEY = "";

    @GetMapping(
            value = "/teams?filter[name]=FURIA"
    )
    Object getFuriaTeamInfo();

    @GetMapping(
            value = "/matches/upcoming?filter[opponent_id]=3455"
    )
    List<MatchDTO> getUpcomingMatches();

    @GetMapping(
            value = "/matches/past?filter[opponent_id]=124530"
    )
    List<MatchDTO> getPastMatches();
}
