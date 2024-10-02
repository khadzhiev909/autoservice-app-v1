package com.example.bookingservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    private Long id;                     // Уникальный идентификатор записи
    private Long customerId;             // Идентификатор клиента
    private Long serviceCenterId;        // Идентификатор автосервиса
    private Long mechanicId;             // Идентификатор механика, назначенного на запись
    private LocalDateTime appointmentTime; // Время записи
    private String status;               // Статус записи (подтверждено, отменено, завершено)
    private String description;          // Описание услуги или проблема, на которую клиент записался
}
