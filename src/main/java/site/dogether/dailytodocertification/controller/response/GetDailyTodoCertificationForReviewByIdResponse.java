package site.dogether.dailytodocertification.controller.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public record GetDailyTodoCertificationForReviewByIdResponse(@JsonUnwrapped DailyTodoCertificationResponse dailyTodoCertification) {
}
