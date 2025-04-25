package dev.guilhermeluan.furiafanbot.infra.client;

import dev.guilhermeluan.furiafanbot.config.PandaScoreClientConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

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
            value = "/matches/upcoming?filter[opponent_id]=124530"
    )
    Object getNextMatches();

    @GetMapping(
            value = "/matches/past?filter[opponent_id]=124530"
    )
    Object getPastMatches();
}
