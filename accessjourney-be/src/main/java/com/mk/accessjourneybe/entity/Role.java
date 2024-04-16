package com.mk.accessjourneybe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @NotBlank(message = "El nombre del rol no puede estar vacío.")
    @Size(max = 127, message = "El nombre del rol no puede exceder los 127 caracteres.")
    @Column(name = "name", length = 64, nullable = false, unique = true)
    private String name;

    @NotBlank(message = "La descripción del rol no puede estar vacía.")
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "role",
            cascade = CascadeType.REMOVE
    )
    private List<User> users = new ArrayList<>();
}
