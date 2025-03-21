package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestLogController {
    private static final Logger logger = LoggerFactory.getLogger(TestLogController.class);

    @GetMapping("/test")
    public String testLogs() {
        logger.warn("This is a warning message.");
        logger.info("This is an info message.");
        logger.error("This is an error message.");

        return "Logs have been generated! Check your Sentry dashboard.";
    }
}
