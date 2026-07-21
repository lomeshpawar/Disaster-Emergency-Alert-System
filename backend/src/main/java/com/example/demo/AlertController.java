package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")

@CrossOrigin("*")
public class AlertController {

    private final AlertRepository repository;

    public AlertController(AlertRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Alert> getAlerts(){

        return repository.findAll();

    }

    @PostMapping
    public Alert addAlert(@RequestBody Alert alert){

        return repository.save(alert);

    }

    @DeleteMapping("/{id}")
    public void deleteAlert(@PathVariable Long id){

        repository.deleteById(id);

    }
}