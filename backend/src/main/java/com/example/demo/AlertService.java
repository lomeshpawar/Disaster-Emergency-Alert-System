package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlertService {
    private final AlertRepository alertRepository;
    public AlertService(AlertRepository alertRepository) { this.alertRepository = alertRepository; }
    public List<Alert> getAllAlerts() { return alertRepository.findAll(); }
    public Alert createAlert(Alert alert) { return alertRepository.save(alert); }
    public void deleteAlert(Long id) {
        if (!alertRepository.existsById(id)) throw new ResourceNotFoundException("Alert not found with id: " + id);
        alertRepository.deleteById(id);
    }
}
