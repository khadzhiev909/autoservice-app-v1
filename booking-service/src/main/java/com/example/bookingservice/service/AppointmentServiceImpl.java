package com.example.bookingservice.service;

import com.example.bookingservice.entitty.Appointment;
import com.example.bookingservice.entitty.Customer;
import com.example.bookingservice.exception.AppointmentNotFoundException;
import com.example.bookingservice.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl {

    private final AppointmentRepository appointmentRepository;
    private final RestTemplate restTemplate;

    // Получение всех записей на обслуживание
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Получение конкретной записи по ID
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Запись не найдена с id: " + id));
    }

    // Создание новой записи на обслуживание
    public Appointment createAppointment(Appointment appointment) {
        ResponseEntity<Customer> response = restTemplate.getForEntity("http://client-service/api/customers/" + appointment.getCustomer().getId(), Customer.class);
        Customer customer = response.getBody();

        appointment.setCustomer(customer);
        return appointmentRepository.save(appointment);
    }

    // Обновление существующей записи
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Запись не найдена с id: " + id));

        existingAppointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
        existingAppointment.setDescription(updatedAppointment.getDescription());
        existingAppointment.setStatus(updatedAppointment.getStatus());
        existingAppointment.setCustomer(updatedAppointment.getCustomer());
        existingAppointment.setServiceCenter(updatedAppointment.getServiceCenter());
        existingAppointment.setService(updatedAppointment.getService());
        existingAppointment.setMaster(updatedAppointment.getMaster());

        return appointmentRepository.save(existingAppointment);
    }

    // Удаление записи
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new AppointmentNotFoundException("Запись не найдена с id: " + id);
        }
        appointmentRepository.deleteById(id);
    }

    // Проверка доступности ресурсов (мастера и оборудования) для записи
    public boolean checkAvailability(Appointment appointment) {
        // Пример простой проверки, что в это время нет других записей
        return !appointmentRepository.existsByAppointmentDateAndServiceCenter(appointment.getAppointmentDate(), appointment.getServiceCenter());
    }

    // Обновление статуса записи (подтверждено, отменено, завершено)
    public Appointment updateAppointmentStatus(Long id, String status) {
        Appointment appointment = getAppointmentById(id);
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }
}
