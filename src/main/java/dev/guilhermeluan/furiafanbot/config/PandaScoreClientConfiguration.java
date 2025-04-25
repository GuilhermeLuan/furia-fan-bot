package dev.guilhermeluan.furiafanbot.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class PandaScoreClientConfiguration {

    @Value("${panda_score.api.token}")
    private String PANDA_SCORE_API_KEY;

    @Bean
    public RequestInterceptor pandaScoreRequestInterceptor() {
        return template ->
                template.header("Authorization", "Bearer " + PANDA_SCORE_API_KEY);
    }
}
