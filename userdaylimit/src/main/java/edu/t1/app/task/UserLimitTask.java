package edu.t1.app.task;

import edu.t1.app.service.UserDayLimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLimitTask {
    private final UserDayLimitService userDayLimitService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void resetDailyLimits() {
        userDayLimitService.resetLimits();
    }
}
