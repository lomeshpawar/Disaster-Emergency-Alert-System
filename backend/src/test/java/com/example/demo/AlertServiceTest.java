package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AlertServiceTest {

    @Mock
    private AlertRepository alertRepository;

    private AlertService alertService;

    @BeforeEach
    void setUp() {
        alertService = new AlertService(alertRepository);
    }

    @Test
    void getAllAlertsReturnsRepositoryResults() {
        Alert first = new Alert();
        Alert second = new Alert();
        when(alertRepository.findAll()).thenReturn(List.of(first, second));

        List<Alert> result = alertService.getAllAlerts();

        assertEquals(2, result.size());
        assertEquals(List.of(first, second), result);
        verify(alertRepository).findAll();
    }

    @Test
    void createAlertDelegatesToRepository() {
        Alert alert = new Alert();
        when(alertRepository.save(alert)).thenReturn(alert);

        Alert result = alertService.createAlert(alert);

        assertEquals(alert, result);
        verify(alertRepository).save(alert);
    }

    @Test
    void deleteAlertDeletesExistingAlert() {
        when(alertRepository.existsById(7L)).thenReturn(true);

        alertService.deleteAlert(7L);

        verify(alertRepository).existsById(7L);
        verify(alertRepository).deleteById(7L);
    }

    @Test
    void deleteAlertRejectsMissingAlert() {
        when(alertRepository.existsById(7L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> alertService.deleteAlert(7L));

        verify(alertRepository).existsById(7L);
        verify(alertRepository, never()).deleteById(7L);
    }
}
