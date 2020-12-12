package checkers.bot.impl;

import checkers.bot.api.ICheckersBotAi;
import checkers.bot.dto.ConnectToTheGameResponse;
import checkers.bot.dto.GetGameInfoResponse;
import checkers.bot.dto.MakeMoveRequest;
import checkers.bot.dto.MakeMoveResponse;
import checkers.bot.util.ConvertUtils;
import checkers.bot.util.Move;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckersBot {
    public final String name;
    private ICheckersBotAi checkersBotAi;
    private String serverUrl;
    private String token;
    private String color;
    private volatile boolean isFinished;

    public CheckersBot(String name, ICheckersBotAi checkersBotAi, String serverUrl) {
        this.name = name;
        this.checkersBotAi = checkersBotAi;
        this.serverUrl = serverUrl;
    }

    public void play(RestTemplate restTemplate) {
        connectToTheGame(restTemplate);
        checkersBotAi.setColor(color);
        while (!isFinished) {
            GetGameInfoResponse getGameInfoResponse = getGameInfo(restTemplate);

            if (color.equals(getGameInfoResponse.getData().getWhose_turn()))
                move(restTemplate, getGameInfoResponse.getData().getBoard());

            try {
                TimeUnit.MILLISECONDS.sleep(600l);
            } catch (InterruptedException e) {
            }
        }

    }

    public void connectToTheGame(RestTemplate restTemplate) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(serverUrl + "/game")
                // Add query parameter
                .queryParam("team_name", name);

        ResponseEntity<ConnectToTheGameResponse> connectToTheGameResponseResponseEntity
                = restTemplate.postForEntity(builder.build().toUri(), null, ConnectToTheGameResponse.class);


        token = connectToTheGameResponseResponseEntity.getBody().getData().getToken();
        color = connectToTheGameResponseResponseEntity.getBody().getData().getColor();
    }

    public GetGameInfoResponse getGameInfo(RestTemplate restTemplate) {
        ResponseEntity<GetGameInfoResponse> getGameInfoResponseResponseEntity
                = restTemplate.getForEntity(serverUrl + "/game", GetGameInfoResponse.class);
        isFinished = getGameInfoResponseResponseEntity.getBody().getData().isIs_finished();
        return getGameInfoResponseResponseEntity.getBody();
    }

    public void move(RestTemplate restTemplate, List<GetGameInfoResponse.Data.BoardItem> board) {
        System.out.println(color + " team turn: ");
        Move step = checkersBotAi.getNextStep(ConvertUtils.convertBoard(board, color));
        System.out.println(step);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Token " + token);

            HttpEntity<MakeMoveRequest> request = new HttpEntity<>(new MakeMoveRequest(step), headers);
            ResponseEntity<MakeMoveResponse> response = restTemplate
                    .exchange(serverUrl + "/move", HttpMethod.POST, request, MakeMoveResponse.class);
            System.out.println("Response: " + response.getBody().getData());
        } catch (Exception e) {
            e.printStackTrace();
            isFinished = true;
        }
    }


}
