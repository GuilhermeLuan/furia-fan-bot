package dev.guilhermeluan.furiafanbot.service;

import dev.guilhermeluan.furiafanbot.infra.client.PandaScoreClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Object testNextMatches() {
        return pandaScoreClient.getNextMatches();
    }

    @GetMapping("/past")
    public Object testPastMatches() {
        return pandaScoreClient.getPastMatches();
    }
}
