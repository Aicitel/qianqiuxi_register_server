package net.qianqiuxi.register.model.dao;

public class UserDetail {
    private Integer id;
    private Integer win;
    private Integer lose;
    private String description;

    public UserDetail(){}

    public UserDetail(Integer id) {
        this.id = id;
    }

    public UserDetail(Integer id, Integer win, Integer lose, String description) {
        this.id = id;
        this.win = win;
        this.lose = lose;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getLose() {
        return lose;
    }

    public void setLose(Integer lose) {
        this.lose = lose;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
