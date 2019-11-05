package net.qianqiuxi.register.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;


public class UpdateRequest extends BaseRequest{

    @JsonProperty(value = "opponent")
    private String opponent;

    @JsonProperty(value = "win")
    private Boolean win;

    @JsonProperty(value = "gameToken")
    private String gameToken;

    public Boolean isWin() {
        return win;
    }

    public String getGameToken() {
        return gameToken;
    }

    public String getOpponent() {
        return opponent;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return String.format("Game:%s %s vs %s , %s", gameToken, username, opponent, win);
    }
}
