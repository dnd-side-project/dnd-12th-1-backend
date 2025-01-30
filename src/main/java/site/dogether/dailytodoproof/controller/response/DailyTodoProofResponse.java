package site.dogether.dailytodoproof.controller.response;

import java.util.List;

public record DailyTodoProofResponse(
    Long id,
    String content,
    List<String> proofMediaUrls,
    String todoContent
) {
}
