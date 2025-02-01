package site.dogether.dailytodo.controller.request;

import java.util.List;

public record CertifyDailyTodoRequest(String content, List<String> mediaUrls) {
}
