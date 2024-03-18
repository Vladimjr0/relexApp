package com.vladimir.relexApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;


@Entity
@Table(name = "change_list")
@Data
public class ChangeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    //TODO created_at -> createdAt
    private LocalDate createdAt;

    private Integer quantity;


}
