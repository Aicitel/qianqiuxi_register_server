package net.qianqiuxi.register.service;

import net.qianqiuxi.register.model.dto.ServiceResponse;
import net.qianqiuxi.register.model.request.UpdateRequest;

public interface UpdateService {

    /**
     * Update response code begins with 2350
     */
    int SUCCEED = 235000;
    int AUTH_FAIL = 235001;
    int SERVER_ERROR = 235002;

    ServiceResponse update(UpdateRequest updateRequest);
}
