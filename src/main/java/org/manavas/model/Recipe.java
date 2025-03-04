package org.manavas.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "recipes")
public class Recipe extends PanacheEntity {
    @Column(name = "title", nullable = false)
    public String title;
    @Column(name = "making_time", nullable = false)
    public String makingTime;
    @Column(name = "serves", nullable = false)
    public String serves;
    @Column(name = "ingredients", nullable = false)
    public String ingredients;
    @Column(name = "cost", nullable = false)
    public Integer cost;
    @Column(name = "created_at", nullable = false)
    public LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "updated_at", nullable = false)
    public LocalDateTime updatedAt = LocalDateTime.now();
}
