package dev.guilhermeluan.furiafanbot.client;

import dev.guilhermeluan.furiafanbot.client.dto.MatchDTO;
import dev.guilhermeluan.furiafanbot.config.OpenFeignConfig;
import dev.guilhermeluan.furiafanbot.config.PandaScoreClientConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        value = "pandascore", url = "https://api.pandascore.co/csgo",
        configuration = {PandaScoreClientConfiguration.class, OpenFeignConfig.class}
)
public interface PandaScoreClient {

    @Value("${panda_score.api.token}")
    String PANDA_SCORE_API_KEY = "";
    String FURIA_TEAM_ID = "124530";
    String FURIA_TEAM_NAME = "FURIA";

    @GetMapping(
            value = "/teams?filter[name]=" + FURIA_TEAM_NAME
    )
    Object getFuriaTeamInfo();

    @GetMapping(
            value = "/matches/upcoming?filter[opponent_id]=" + FURIA_TEAM_ID
    )
    List<MatchDTO> getUpcomingMatches();

    @GetMapping(
            value = "/matches/past?page[size]=3&filter[opponent_id]=" + FURIA_TEAM_ID
    )
    List<MatchDTO> getPastMatches();
}
