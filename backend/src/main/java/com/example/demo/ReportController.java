package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reports")
@CrossOrigin("*")
public class ReportController {

    private final ReportRepository reportRepository;

    // Inject ReportRepository using constructor injection
    public ReportController(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    // GET /reports - Fetch all submitted reports (for Admin panel)
    @GetMapping
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    // POST /reports - Submit a new emergency report (for Citizens)
    @PostMapping
    public Report submitReport(@RequestBody Report report) {
        // Save the submitted emergency report to the MySQL database
        return reportRepository.save(report);
    }

    // DELETE /reports/{id} - Delete a report (for Admin)
    @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable Long id) {
        reportRepository.deleteById(id);
    }
}