package edu.t1.app.service;

import edu.t1.app.model.UserDayLimitOperation;
import edu.t1.app.model.UserDayLimitRequest;
import edu.t1.app.model.dto.UserDayLimitDto;

public interface UserDayLimitService {
    UserDayLimitDto create(UserDayLimitRequest userDayLimitRequest);
    void update(UserDayLimitDto userDayLimitDto);
    UserDayLimitDto changeLimit(UserDayLimitOperation userDayLimitOperation);
    void resetLimits();
}
