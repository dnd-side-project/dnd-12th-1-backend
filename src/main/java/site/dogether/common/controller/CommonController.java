package site.dogether.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @GetMapping("/api/health-check")
    public String healthCheck() {
        return "Dogether Backend Service is OK 🔥";
    }
}
