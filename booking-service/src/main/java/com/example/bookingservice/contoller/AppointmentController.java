package com.example.bookingservice.contoller;

import com.example.bookingservice.dto.AppointmentDTO;
import com.example.bookingservice.entitty.Appointment;
import com.example.bookingservice.service.AppointmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentServiceImpl appointmentService;

    // Получение всех записей на обслуживание
    // @return список всех записей
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    // Получение конкретной записи по ID
    // @param id уникальный идентификатор записи
    // @return информация о записи
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    // Создание новой записи на обслуживание
    // @param appointmentDTO данные для новой записи
    // @return созданная запись
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment createdAppointment = appointmentService.createAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
    }

    // Обновление существующей записи на обслуживание
    // @param id идентификатор записи для обновления
    // @param appointmentDTO новые данные для обновления
    // @return обновленная запись
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable Long id, @RequestBody Appointment appointment) {
        Appointment updatedAppointment = appointmentService.updateAppointment(id, appointment);
        return ResponseEntity.ok(updatedAppointment);
    }

    // Удаление записи на обслуживание
    // @param id идентификатор записи для удаления
    // @return сообщение об успешном удалении
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    // Проверка доступности мастеров и оборудования для записи
    // @param appointmentDTO данные для проверки доступности
    // @return результат проверки
    @PostMapping("/check-availability")
    public ResponseEntity<Boolean> checkAvailability(@RequestBody Appointment appointment) {
        boolean available = appointmentService.checkAvailability(appointment);
        return ResponseEntity.ok(available);
    }

    // Обновление статуса записи (например, подтверждено, отменено, завершено)
    // @param id идентификатор записи
    // @param status новый статус записи
    @PatchMapping("/{id}/status")
    public ResponseEntity<Appointment> updateAppointmentStatus(
            @PathVariable Long id, @RequestParam String status) {
        Appointment updatedAppointment = appointmentService.updateAppointmentStatus(id, status);
        return ResponseEntity.ok(updatedAppointment);
    }
}
