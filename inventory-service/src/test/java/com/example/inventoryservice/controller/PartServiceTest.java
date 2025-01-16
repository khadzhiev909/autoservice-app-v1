package com.example.inventoryservice.controller;

import com.example.inventoryservice.entity.Part;
import com.example.inventoryservice.repository.PartRepository;
import com.example.inventoryservice.service.PartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)//Аннотация для поддержки мокирования
public class PartServiceTest {
    @Mock// сам заменяемый объект, который позволяет заменить ответы от реального репозитория
    private PartRepository repository;

    @InjectMocks//тестируемый класс
    private PartService partService;

    @Test
    void getAllParts() {
        //Data for test
        Pageable pageable = PageRequest.of(0, 2);
        List<Part> partList = List.of(new Part("имя1", "Категоря бамперы", "BMW", new BigDecimal(550.43), 10, "Бампер из германии для BMW", "9981209312", LocalDate.now()),
                new Part("имя2", "Категория бамперы", "Мерседес", new BigDecimal(9900.43), 10, "Бампер из германии для Мерседеса", "00009123123", LocalDate.now()));
        Page<Part> parts = new PageImpl<>(partList, pageable, 2);
        //Mock the repository
        when(repository.getAllParts(pageable)).thenReturn(parts);

        //Testing part service
        Page<Part> result = partService.getAllParts(pageable);
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
//        verify(repository, times(1).getAllParts(pageable));
    }
}
