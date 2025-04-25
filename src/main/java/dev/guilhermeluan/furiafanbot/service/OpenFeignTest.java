package dev.guilhermeluan.furiafanbot.service;

import dev.guilhermeluan.furiafanbot.client.PandaScoreClient;
import dev.guilhermeluan.furiafanbot.client.dto.MatchDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class OpenFeignTest {
    private final PandaScoreClient pandaScoreClient;

    public OpenFeignTest(PandaScoreClient pandaScoreClient) {
        this.pandaScoreClient = pandaScoreClient;
    }

    @GetMapping()
    public Object test() {
        return pandaScoreClient.getFuriaTeamInfo();
    }

    @GetMapping("/next")
    public List<MatchDTO> testNextMatches() {
        return pandaScoreClient.getUpcomingMatches();
    }

    @GetMapping("/past")
    public Object testPastMatches() {
        return pandaScoreClient.getPastMatches();
    }
}
