package com.devfabiosimones.beutique.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customer")
public class CustomerEntity extends BaseEntity{

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String phone;

    @Column(nullable = false, length = 100)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<AppointmentsEntity> appointments;
}
