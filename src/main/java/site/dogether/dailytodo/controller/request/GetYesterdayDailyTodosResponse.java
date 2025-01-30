package site.dogether.dailytodo.controller.request;

import java.util.List;

public record GetYesterdayDailyTodosResponse(List<String> todos) {
}
