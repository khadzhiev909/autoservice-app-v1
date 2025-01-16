//package com.example.reportservice.mapper;
//
//import com.example.reportservice.dto.AppointmentDTO;
//import com.example.reportservice.entity.Appointment;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//public class ToAppointmentDTO {
//
//    public List<AppointmentDTO> mapToAppointmentDTO(List<Appointment> appointments) {
//        List<AppointmentDTO> appointmentDTOS = appointments.stream()
//                .map(appointment -> new AppointmentDTO(
//                        appointment.getCustomerName(),
//                        appointment.getServiceNames(),
//                        appointment.getAppointmentDate(),
//                        appointment.getTotalServicePrice(),
//                        appointment.getCustomerName()
//                )).collect(Collectors.toList());
//
//        appointmentDTOS.stream().peek(System.out::println).limit(5);
//        return appointmentDTOS;
//    }
//
//
//}
