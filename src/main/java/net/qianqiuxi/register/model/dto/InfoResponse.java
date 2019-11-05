package net.qianqiuxi.register.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.qianqiuxi.register.model.dao.UserDetail;

public class InfoResponse extends ServiceResponse {

    @JsonProperty(value = "info")
    final private WinLoseTitleWrapper winLoseTitleWrapper;

    /**
     * Login response constructor.
     * @param code login result code
     * @param status login result status
     * @param message login message
     * @param winLoseTitleWrapper user token if success
     */
    public InfoResponse(Integer code, Status status, String message, WinLoseTitleWrapper winLoseTitleWrapper) {
        super(code, status, message);
        this.winLoseTitleWrapper = winLoseTitleWrapper;
    }

    public WinLoseTitleWrapper getWinLoseTitleWrapper() {
        return winLoseTitleWrapper;
    }

    public static class WinLoseTitleWrapper{

        @JsonProperty(value = "win")
        private Integer win;

        @JsonProperty(value = "lose")
        private Integer lose;

        @JsonProperty(value = "title")
        private String title;

        public WinLoseTitleWrapper(Integer win, Integer lose, String title) {
            this.win = win;
            this.lose = lose;
            this.title = title;
        }

        public WinLoseTitleWrapper(UserDetail userDetail, String title){
            this.win = userDetail.getWin();
            this.lose = userDetail.getLose();
            this.title = title;
        }

        public Integer getWin() {
            return win;
        }

        public Integer getLose() {
            return lose;
        }

        public String getTitle() {
            return title;
        }
    }
}
