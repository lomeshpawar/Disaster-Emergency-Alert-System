package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/alerts")
@CrossOrigin("*")
public class AlertController {
    private final AlertService alertService;
    public AlertController(AlertService alertService) { this.alertService = alertService; }
    @GetMapping
    public List<Alert> getAlerts() { return alertService.getAllAlerts(); }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Alert addAlert(@Valid @RequestBody Alert alert) { return alertService.createAlert(alert); }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlert(@PathVariable Long id) { alertService.deleteAlert(id); }
}
