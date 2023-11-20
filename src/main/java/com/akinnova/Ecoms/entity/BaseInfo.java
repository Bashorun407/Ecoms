package com.akinnova.Ecoms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID public_id;
    protected LocalDateTime createdAt = LocalDateTime.now();
    protected LocalDateTime updatedAt;

    @Column(name = "created_by")
    protected UUID createdBy;

    public BaseInfo(UUID createdBy){}
}
