package site.dogether.dailytodo.controller.request;

import java.util.List;

public record CreateDailyTodosRequest(List<String> todos) {
}
