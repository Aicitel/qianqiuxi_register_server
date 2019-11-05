package net.qianqiuxi.register.service;

import net.qianqiuxi.register.model.dto.ServiceResponse;
import net.qianqiuxi.register.model.request.BaseRequest;
import net.qianqiuxi.register.model.request.UpdateRequest;

public interface InfoService {

    /**
     * Info response code begins with 2350
     */
    int SUCCEED = 235000;
    int AUTH_FAIL = 235001;
    int SERVER_ERROR = 235002;
    int INVALID_TOKEN = 235003;

    String INIT = "init";

    /**
     * Init game token
     * @param gameToken the token created by middleware
     * @return Init result, including game token if succeed
     */
    ServiceResponse init(String gameToken);

    /**
     * Update game result
     * @param updateRequest request including opponents and winner and game token
     * @return Update result
     */
    ServiceResponse update(UpdateRequest updateRequest);

    /**
     * Get win lose title info
     * @param baseRequest request including username
     * @return Enclosure infos
     */
    ServiceResponse getInfo(BaseRequest baseRequest);
}
