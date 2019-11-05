package net.qianqiuxi.register.model.dao;

import java.sql.Timestamp;

public class Game {
    private Integer id;
    private Integer player1Id;
    private Integer player2Id;
    private Integer winner;
    private Timestamp matchDate;
    private String detail;
    private String token;

    public Game(){}

    public Game(Integer player1Id, Integer player2Id, Integer winner, String token) {
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.winner = winner;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(Integer player1Id) {
        this.player1Id = player1Id;
    }

    public Integer getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(Integer player2Id) {
        this.player2Id = player2Id;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public Timestamp getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Timestamp matchDate) {
        this.matchDate = matchDate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
