package checkers.bot.impl;

import checkers.bot.api.ICheckersBotAi;
import checkers.bot.dto.ConnectToTheGameResponse;
import checkers.bot.dto.GetGameInfoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class CheckersBot {
    public final String name;
    private ICheckersBotAi checkersBotAi;
    private String serverUrl;

    public CheckersBot(String name, ICheckersBotAi checkersBotAi, String serverUrl) {
        this.name = name;
        this.checkersBotAi = checkersBotAi;
        this.serverUrl = serverUrl;
    }

    public void connectToTheGame(RestTemplate restTemplate) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(serverUrl + "/game")
                // Add query parameter
                .queryParam("team_name", "Loom");

        ResponseEntity<ConnectToTheGameResponse> connectToTheGameResponseResponseEntity
                = restTemplate.postForEntity(builder.build().toUri(), null, ConnectToTheGameResponse.class);

        System.out.println(connectToTheGameResponseResponseEntity.getBody());

        ResponseEntity<GetGameInfoResponse> getGameInfoResponseResponseEntity
                = restTemplate.getForEntity(serverUrl + "/game", GetGameInfoResponse.class);

        System.out.println(getGameInfoResponseResponseEntity.getBody());
    }

    public void getGameInfo(RestTemplate restTemplate) {

    }

    public void move(RestTemplate restTemplate) {

    }


}
