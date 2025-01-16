package com.example.reportservice.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class /**/Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reportType;

    private LocalDateTime generatedAt;

    private String status;

    @Lob
    private byte[] content; // Содержимое отчета (например, PDF)
}
