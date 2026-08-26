package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class VulnerableController {

    // VULNERABILITY: Hardcoded credentials
    private static final String DB_PASSWORD = "Admin123!";
    private static final String API_KEY = "sk-1234567890abcdef";

    // VULNERABILITY: SQL Injection
    @GetMapping("/search")
    public String search(@RequestParam String query) {
        String sql = "SELECT * FROM products WHERE name LIKE '%" + query + "%'";
        return "Executing: " + sql;
    }

    // VULNERABILITY: XSS
    @GetMapping("/profile")
    public String profile(@RequestParam String username) {
        return "<h1>Welcome, " + username + "!</h1>";
    }

    // VULNERABILITY: Command Injection
    @PostMapping("/execute")
    public String execute(@RequestParam String cmd) {
        try {
            ProcessBuilder pb = new ProcessBuilder("sh", "-c", cmd);
            Process process = pb.start();
            return "Command executed";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // VULNERABILITY: Path Traversal
    @GetMapping("/files")
    public String files(@RequestParam String filename) {
        String path = "/var/www/files/" + filename;
        return "Reading: " + path;
    }

    // Health check
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
