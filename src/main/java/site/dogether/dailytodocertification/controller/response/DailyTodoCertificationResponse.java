package site.dogether.dailytodocertification.controller.response;

import java.util.List;

public record DailyTodoCertificationResponse(
    Long id,
    String content,
    List<String> mediaUrls,
    String todoContent
) {
}
