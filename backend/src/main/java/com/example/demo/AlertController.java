package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
@CrossOrigin("*")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping
    public List<Alert> getAlerts() {
        return alertService.getAllAlerts();
    }

    @PostMapping
    public Alert addAlert(@RequestBody Alert alert) {
        return alertService.createAlert(alert);
    }

    @DeleteMapping("/{id}")
    public void deleteAlert(@PathVariable Long id) {
        alertService.deleteAlert(id);
    }
}
